from google.cloud import storage
import os

key_path = './serviceaccountkey.json'

if not os.path.exists(key_path):
    print (f"Error: File {key_path} not found.")
else:
    os.environ["GOOGLE_APPLICATION_CREDENTIALS"] = key_path

bucket_name = 'posted-image-kultura'

def upload_bucket_file(source_file_path, destination_file_name):

    try:
        storage_client = storage.Client()

        bucket = storage_client.bucket(bucket_name)

        blob = bucket.blob(destination_file_name)
        blob.upload_from_file(source_file_path)
        return "File has uploaded to Bucket"
    except Exception as e:
        print(str(e))
        return
