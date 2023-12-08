import os
import tensorflow as tf
from keras.preprocessing.image import ImageDataGenerator
from PIL import Image
import scipy


def kultura_model():
    #path to directories
    train_dir = "Balinese Masks Dataset\Training"
    val_dir = "Balinese Masks Dataset\Validation"
    test_dir = "Balinese Masks Dataset\Test"

    #parameter for the data
    batch_size = 8
    image_size = (150, 150)

    #data generator
    train_datagen =ImageDataGenerator(
        rescale = 1./255.,
        rotation_range = 40,
        shear_range = 0.2,
        zoom_range = 0.2,
        fill_mode = 'nearest',
        horizontal_flip = True
    )

    val_datagen = ImageDataGenerator(
        rescale = 1./255.
    )

    test_datagen = ImageDataGenerator(
        rescale = 1./255.
    )

    #resize image
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

    #machine learning model
    model = tf.keras.models.Sequential([
        tf.keras.layers.Conv2D(8, (3, 3), activation='relu', input_shape=(150, 150, 3)),
        tf.keras.layers.BatchNormalization(),  # Add normalization layer
        tf.keras.layers.MaxPooling2D(2, 2),
        tf.keras.layers.Conv2D(16, (3, 3), activation='relu'),
        tf.keras.layers.BatchNormalization(),  # Add normalization layer
        tf.keras.layers.MaxPooling2D(2, 2),
        tf.keras.layers.Flatten(),
        tf.keras.layers.Dense(32, activation='relu'),
        tf.keras.layers.Dense(7, activation='softmax')
    ])

    model.compile(
        optimizer='adam',
        loss='categorical_crossentropy',
        metrics=['accuracy']
    )

    model.fit(train_generator, validation_data=val_generator, epochs=25)

    model.summary()
    return model

if __name__ == '__main__':
    # DO NOT CHANGE THIS CODE
    model=kultura_model()
    model.save("kultura_model.h5")