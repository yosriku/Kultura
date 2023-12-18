from flask import request,jsonify,Blueprint
from keras.preprocessing import image
from PIL import Image
import numpy as np
from app.utils import preprocess_input_data,topeng_bali_array,products ##nanti tambahin jadi app.utils
import tensorflow_hub as hub
import tensorflow as tf
import os


routes = Blueprint('routes',__name__)
model = None

def load_keras_model():
  global model
  try:
    script_dir = os.path.dirname(os.path.abspath(__file__))
    model_path = os.path.join(script_dir, 'vgg16_neural_network.h5')
    model =tf.keras.models.load_model(
      model_path,
      custom_objects={'KerasLayer':hub.KerasLayer})
    print("Model loaded")
  except Exception as e:
        print("Loading model Error:", str(e))
        model = None
        return 500


load_keras_model()

@routes.route('/post',methods=['POST'])
def predict():
  if model is None:
    return jsonify({'Error':'Model not loaded'}),500
  
  try:
    if 'file' not in request.files:
      return jsonify({'Error':'No file'}),400
    
    file = request.files['file']

    if file.filename == '':
      return jsonify({'error':'No Selected File'}),400
    
    
    
    img = Image.open(file).resize((224,224)).convert('RGB')
    img_array = image.img_to_array(img)
    img_array = np.expand_dims(img_array, axis=0)

    input_data = preprocess_input_data(img_array)

    prediction = model.predict(input_data)
    prediction_array = prediction.tolist()
    predicted_index = np.argmax(prediction_array)
    predicted_class = topeng_bali_array[predicted_index]

    
    return jsonify({'Hasil':predicted_class})
  
  except Exception as e:
    return jsonify({'error':str(e)})
  

@routes.route('/topeng')
def get_all_topeng():
  return jsonify(products)

@routes.route('/topeng/<int:product_id>',methods=['GET'])
def get_product(product_id):
  product = next((mask for mask in products["topeng"] if mask["id"] == product_id), None)
  if product:
    return jsonify(product)
  else:
    return jsonify({'Error':'Topeng tidak ada'}),404


