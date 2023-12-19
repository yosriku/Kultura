package com.kultura.app.data.response

import com.google.gson.annotations.SerializedName

data class ScanResponse(

	@field:SerializedName("Hasil")
	val hasil: String
)
