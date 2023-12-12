def preprocess_input_data(input_data):
    return input_data / 255.0

topeng_bali_array =['Topeng Bujuh','Topeng Dalem','Topeng Keras','Topeng Penasar','Topeng Sidakarya','Topeng Tua','Topeng Wijil']

products = {
    1:{'name':'Topeng Bujuh','Kisaran Harga':'Rp. 100.000 - Rp. 130.000','informasi':'Topeng spesialis lucu di antara topeng Bali lainnya seperti topeng tua dan topeng keras'},
    2:{'name':'Topeng Dalem Arsa Wijaya','Kisaran Harga':'Rp. 135.000 - Rp. 150.000','informasi':'Topeng Dalem Arsa Wijaya berwajah manis pada Topeng Wali, melambangkan pemimpin yang membawa harapan (arsa) dan kemenangan (wijaya) bagi rakyatnya. Topeng Arsa Wijaya Secara visual, rupa topeng ini berwajah tampan, bermata sipit, memakai urna, dominan berwarna putih.'},
    3:{'name':'Topeng Keras','Kisaran Harga':'Rp. 150.000 - Rp. 180.000','informasi':'Topeng keras biasa lebih dikenal sebagai sosok petarung.'},
    4:{'name':'Topeng Penasar','Kisaran Harga' :'Rp.100.000 - Rp. 120.000','informasi' :'tokoh penasar (penutur cerita)'},
    5:{'name':'Topeng Sidakarya','Kisaran Harga' : 'Rp.115.000 - Rp.150.000' ,'informasi':'Topeng berasal dari kata tup yang artinya tutup. Sidakarya berasal dari kata "sida" yang artinya mencapai, dan "karya" yang artinya tujuan atau pekerjaan. Sidakarya memiliki makna mencapai tujuan atau menyelesaikan pekerjaan'},
    6:{'name':'Topeng Tua','Kisaran Harga' : 'Rp.145.000 - Rp.210.000','informasi':'Topeng tua biasanya digunakan dalam pentas seni dalam ritual peringatan piodalan.'},
    7:{'name':'Topeng Wijil','Kisaran Harga' : 'Rp.250.000','informasi':'Karkater wijil merupakan karakter punakawan yang perpasangan dengan Penasar. Mereka adalah pendamping atau abdi raja dalam kesenian topeng wali'},
}