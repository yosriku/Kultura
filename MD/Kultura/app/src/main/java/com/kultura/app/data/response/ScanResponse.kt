package com.kultura.app.data.response

import com.google.gson.annotations.SerializedName

data class ScanResponse(

	@field:SerializedName("Hasil")
	val hasil: List<HasilItem>
)

data class HasilItem(

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("id")
	val id: Int
)
