from flask import request,jsonify,Blueprint
from keras.preprocessing import image
from PIL import Image
import numpy as np
from app.utils import topeng_bali_array,products ##nanti tambahin jadi app.utils
from app.upload import upload_bucket_file
from keras.models import load_model
from datetime import datetime
from keras.applications.vgg16 import preprocess_input
import tensorflow as tf
import os


routes = Blueprint('routes',__name__)
model = None

def load_keras_model():
  global model
  try:
    model = load_model('kultura_model.h5')
  except Exception as e:
        print("Loading model Error:", str(e))
        model = None
        return 500


load_keras_model()

@routes.route('/post',methods=['POST'])
def predict():
  if model is None:
    return jsonify({'Error':'Model not loaded'}),500

  if 'file' not in request.files:
    return jsonify({'Error':'No file'}),400
  
  file = request.files['file']

  if file.filename == '':
    return jsonify({'error':'No Selected File'}),400
  
  try:
    # timestamped_name = datetime.now().strftime("%Y%m%d%H%M%S") + '_' + file.filename
    # upload_bucket_file(file.stream, timestamped_name)
    img_path= file
    img = Image.open(img_path).resize((150,150)).convert('RGB')
    img_array = image.img_to_array(img)
    img_array = np.expand_dims(img_array, axis=0)
    input_data = preprocess_input(img_array)
    prediction = model.predict(input_data)
    prediction_array = prediction.tolist()
    predicted_index = int(np.argmax(prediction_array))
    predicted_class = topeng_bali_array[predicted_index]
    return jsonify({'Hasil':[{'id':predicted_index+1,'nama':predicted_class,}]})
  except Exception as e:
    return jsonify({'error':str(e)}),500
  

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


