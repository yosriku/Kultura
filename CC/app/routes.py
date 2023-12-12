from flask import request,jsonify,Blueprint
from keras.preprocessing import image
from PIL import Image
import numpy as np
from .utils import preprocess_input_data,topeng_bali_array,products
from keras.models import load_model


routes = Blueprint('routes',__name__)
model = None

def load_keras_model():
  global model
  model
  try:
    model= load_model('./kultura_model.h5')
  except Exception:
    print ("Error Loading model")

load_keras_model()

@routes.route('/post',methods=['POST'])
def predict():
  if model is None:
    return jsonify({'Error':'Model not loaded'})
  
  try:
    if 'file' not in request.files:
      return jsonify({'Error':'No file'})
    
    file = request.files['file']

    if file.filename == '':
      return jsonify({'error':'No Selected File'})
    
    img = Image.open(file).resize((150,150)).convert('RGB')
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
  product = products.get(product_id)
  if product:
    return jsonify(product)
  else:
    return jsonify({'Error':'Topeng tidak ada'}),404


