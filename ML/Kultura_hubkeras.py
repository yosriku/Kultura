import os
import tensorflow as tf
import tensorflow_hub as hub
from tensorflow.keras import layers
from keras.preprocessing.image import ImageDataGenerator
from PIL import Image

def kultura_model():
    # path to directories
    train_dir = "splitted/train"
    val_dir = "splitted/val"
    test_dir = "splitted/test"

    # parameters for the data
    batch_size = 8
    image_size = (224, 224)  # MobileNetV2 default input size

    # Data generators
    train_datagen = ImageDataGenerator(
        rescale=1./255,
        rotation_range=40,
        shear_range=0.2,
        zoom_range=0.2,
        fill_mode='nearest',
        horizontal_flip=True
    )

    val_datagen = ImageDataGenerator(rescale=1./255)
    test_datagen = ImageDataGenerator(rescale=1./255)

    train_generator = train_datagen.flow_from_directory(
        train_dir,
        target_size=image_size,
        batch_size=batch_size,
        class_mode='categorical'
    )

    val_generator = val_datagen.flow_from_directory(
        val_dir,
        target_size=image_size,
        batch_size=batch_size,
        class_mode='categorical'
    )

    test_generator = test_datagen.flow_from_directory(
        test_dir,
        target_size=image_size,
        batch_size=batch_size,
        class_mode='categorical',
        shuffle=False
    )

    # Load MobileNetV2 from TensorFlow Hub
    base_model_url = "https://tfhub.dev/google/tf2-preview/mobilenet_v2/classification/4"
    base_model = hub.KerasLayer(base_model_url, input_shape=(224, 224, 3))

    # Freeze the pre-trained layers
    base_model.trainable = False

    # Create a new model on top of the pre-trained model
    model = tf.keras.models.Sequential([
        base_model,
        tf.keras.layers.Flatten(),  # Flatten the output to a 1D tensor
        tf.keras.layers.BatchNormalization(),  # Add normalization layer
        tf.keras.layers.Dense(16, activation='relu'),
        tf.keras.layers.BatchNormalization(),  # Add normalization layer
        tf.keras.layers.Dense(7, activation='softmax')
    ])

    # Compile the model
    model.compile(
        optimizer='adam',
        loss='categorical_crossentropy',
        metrics=['accuracy']
    )

    # Train the model
    model.fit(train_generator, validation_data=val_generator, epochs=10)
    model.evaluate(test_generator)
    model.summary()
    return model


if __name__ == '__main__':
    # DO NOT CHANGE THIS CODE
    model = kultura_model()

    # Save the full model in HDF5 format
    model.save("kultura_model_with_hub_keras_layer.h5")

    # Convert the model to TensorFlow Lite format
    converter = tf.lite.TFLiteConverter.from_keras_model(model)
    tflite_model = converter.convert()

    # Save the TFLite model
    with open("kultura_model.tflite", "wb") as f:
        f.write(tflite_model)
