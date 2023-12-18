def preprocess_input_data(input_data):
    return input_data / 255.0

topeng_bali_array =['Topeng Bujuh','Topeng Dalem','Topeng Keras','Topeng Penasar','Topeng Sidakarya','Topeng Tua','Topeng Wijil']

products = {
    "error":"false",
    "message":"success",
    "topeng":[
        {
        "id":1,
        "Kisaran Harga":"Rp. 100.000 - Rp. 130.000",
        "informasi":"Topeng spesialis lucu di antara topeng Bali lainnya seperti topeng tua dan topeng keras",
        "name":"Topeng Bujuh",
        "image-url":'https://storage.googleapis.com/kultura-image/topeng-bujuh.png'
        },
        {
        "id":2,
        "Kisaran Harga":"Rp. 135.000 - Rp. 150.000",
        "informasi":"Topeng Dalem Arsa Wijaya berwajah manis pada Topeng Wali, melambangkan pemimpin yang membawa harapan (arsa) dan kemenangan (wijaya) bagi rakyatnya. Topeng Arsa Wijaya Secara visual, rupa topeng ini berwajah tampan, bermata sipit, memakai urna, dominan berwarna putih.",
        "name":"Topeng Dalem Arsa Wijaya",
        "image-url":'https://storage.googleapis.com/kultura-image/topeng-dalem.jpeg'
        },
        {
        "id":3,
        "Kisaran Harga":"Rp. 150.000 - Rp. 180.000",
        "informasi":"Topeng keras biasa lebih dikenal sebagai sosok petarung.",
        "name":"Topeng Keras",
        "image-url":'https://storage.googleapis.com/kultura-image/topeng-keras.jpg'
        },
        {
        "id":4,
        "Kisaran Harga":"Rp.100.000 - Rp. 120.000",
        "informasi":"tokoh penasar (penutur cerita)",
        "name":"Topeng Penasar",
        "image-url":'https://storage.googleapis.com/kultura-image/topeng-penasar.jpg'
        },
        {
        "id":5,
        "Kisaran Harga":"Rp.115.000 - Rp.150.000",
        "informasi":"Topeng berasal dari kata tup yang artinya tutup. Sidakarya berasal dari kata \"sida\" yang artinya mencapai, dan \"karya\" yang artinya tujuan atau pekerjaan. Sidakarya memiliki makna mencapai tujuan atau menyelesaikan pekerjaan",
        "name":"Topeng Sidakarya",
        "image-url":['https://storage.googleapis.com/kultura-image/topeng-sidakarya-1.jpeg','https://storage.googleapis.com/kultura-image/topeng-sidakarya-2.jpg']
        },
        {
        "id":6,
        "Kisaran Harga":"Rp.145.000 - Rp.210.000",
        "informasi":"Topeng tua biasanya digunakan dalam pentas seni dalam ritual peringatan piodalan.",
        "name":"Topeng Tua",
        "image-url":['https://storage.googleapis.com/kultura-image/topeng-tua-1.jpg','https://storage.googleapis.com/kultura-image/topeng-tua-2.jpeg']
        },
        {
        "id":7,
        "Kisaran Harga":"Rp.250.000",
        "informasi":"Karkater wijil merupakan karakter punakawan yang perpasangan dengan Penasar. Mereka adalah pendamping atau abdi raja dalam kesenian topeng wali",
        "name":"Topeng Wijil",
        "image-url":'https://storage.googleapis.com/kultura-image/topeng-wijil.jpeg'
    }
    ]
    }