package com.kultura.app.data.response

import com.google.gson.annotations.SerializedName

data class DetailTopengResponse(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("image-url")
	val imageUrl: String,

	@field:SerializedName("Kisaran Harga")
	val kisaranHarga: String,

	@field:SerializedName("informasi")
	val informasi: String
)
