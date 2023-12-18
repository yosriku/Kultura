def preprocess_input_data(input_data):
    return input_data / 255.0

ALLOWED_EXTENSIONS = {'png','jpg','jpeg'}


def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

topeng_bali_array =['Topeng Bujuh','Topeng Dalem','Topeng Keras','Topeng Penasar','Topeng Sidakarya','Topeng Tua','Topeng Wijil']

products = {
    "error":"false",
    "message":"success",
    "topeng":[
        {
        "id":1,
        "Kisaran Harga":"Rp. 100.000 - Rp. 130.000",
        "informasi":"Topeng spesialis lucu di antara topeng Bali lainnya seperti topeng tua dan topeng keras",
        "name":"Topeng Bujuh"
        },
        {
        "id":2,
        "Kisaran Harga":"Rp. 135.000 - Rp. 150.000",
        "informasi":"Topeng Dalem Arsa Wijaya berwajah manis pada Topeng Wali, melambangkan pemimpin yang membawa harapan (arsa) dan kemenangan (wijaya) bagi rakyatnya. Topeng Arsa Wijaya Secara visual, rupa topeng ini berwajah tampan, bermata sipit, memakai urna, dominan berwarna putih.",
        "name":"Topeng Dalem Arsa Wijaya"
        },
        {
        "id":3,
        "Kisaran Harga":"Rp. 150.000 - Rp. 180.000",
        "informasi":"Topeng keras biasa lebih dikenal sebagai sosok petarung.",
        "name":"Topeng Keras"
        },
        {
        "id":4,
        "Kisaran Harga":"Rp.100.000 - Rp. 120.000",
        "informasi":"tokoh penasar (penutur cerita)",
        "name":"Topeng Penasar"
        },
        {
        "id":5,
        "Kisaran Harga":"Rp.115.000 - Rp.150.000",
        "informasi":"Topeng berasal dari kata tup yang artinya tutup. Sidakarya berasal dari kata \"sida\" yang artinya mencapai, dan \"karya\" yang artinya tujuan atau pekerjaan. Sidakarya memiliki makna mencapai tujuan atau menyelesaikan pekerjaan",
        "name":"Topeng Sidakarya"
        },
        {
        "id":6,
        "Kisaran Harga":"Rp.145.000 - Rp.210.000",
        "informasi":"Topeng tua biasanya digunakan dalam pentas seni dalam ritual peringatan piodalan.",
        "name":"Topeng Tua"
        },
        {
        "id":7,
        "Kisaran Harga":"Rp.250.000",
        "informasi":"Karkater wijil merupakan karakter punakawan yang perpasangan dengan Penasar. Mereka adalah pendamping atau abdi raja dalam kesenian topeng wali",
        "name":"Topeng Wijil"
    }
    ]
    }