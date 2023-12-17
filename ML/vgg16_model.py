import os
import numpy as np
from sklearn.model_selection import train_test_split
from keras.applications import VGG16
from keras.preprocessing import image
from keras.applications.vgg16 import preprocess_input
from keras.models import Sequential
from keras.layers import Dense, Flatten
from keras.utils import to_categorical
from sklearn.preprocessing import LabelEncoder

# Define constants
DATA_DIR = 'dataset'
IMG_SIZE = (224, 224, 3)

# Load VGG16 model with pre-trained weights on ImageNet
base_model = VGG16(weights='imagenet', include_top=False, input_shape=( 224, 224, 3))
model = Sequential()
model.add(base_model)
# Change the input shape to match your data shape
model.add(Dense(256, activation='relu', input_shape=(7, 7, 512)))  # assuming 7x7 feature map from VGG16
model.add(Flatten())
model.add(Dense(256, activation='relu'))
model.add(Dense(128, activation='relu'))
model.add(Dense(7, activation='softmax'))  # num_classes should be the number of classes in your dataset

# Freeze the convolutional layers
for layer in base_model.layers:
    layer.trainable = False

# Compile the model
model.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['accuracy'])

# Function to extract features using VGG16
def extract_features(img_path):
    img = image.load_img(img_path, target_size=IMG_SIZE)
    x = image.img_to_array(img)
    x = np.expand_dims(x, axis=0)
    x = preprocess_input(x)
    return x

def pre_process_data(dataset_path):
  data = []
  labels = []
  for class_folder in os.listdir(dataset_path):
    class_path = os.path.join(dataset_path, class_folder)
    for img_file in os.listdir(class_path):
      img_path = os.path.join(class_path, img_file)
      img = image.load_img(img_path, target_size=IMG_SIZE)
      x = image.img_to_array(img)
      x = preprocess_input(x)
      data.append(x)
      labels.append(class_folder)
  return np.array(data), np.array(labels)

def load_data(dataset_path):
    data = []
    labels = []
    for class_folder in os.listdir(dataset_path):
        class_path = os.path.join(dataset_path, class_folder)
        for img_file in os.listdir(class_path):
            img_path = os.path.join(class_path, img_file)
            print(img_path)
            features = extract_features(img_path)
            data.append(features)
            labels.append(class_folder)
    return np.array(data), np.array(labels)

# Load dataset
X, y = pre_process_data(os.path.join(DATA_DIR, 'train'))


# Convert labels to one-hot encoding
label_encoder = LabelEncoder()
y_encoded = label_encoder.fit_transform(y)
num_classes = len(np.unique(y_encoded))
y_one_hot = to_categorical(y_encoded, num_classes=num_classes)

# Split the data into training and validation sets
X_train, X_valid, y_train, y_valid = train_test_split(X, y_one_hot, test_size=0.2, random_state=42)

model.summary()

# Train the neural network
model.fit(X_train, y_train, epochs=10, batch_size=32, validation_data=(X_valid, y_valid))

# Save the trained model
model.save('vgg16_neural_network.h5')
