package com.fadhil.juzamma.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize



@Parcelize
data class ResponseSurah(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
) :Parcelable


@Parcelize
data class DataItem(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("englishName")
	val englishName: String? = null,

	@field:SerializedName("numberOfAyahs")
	val numberOfAyahs: Int? = null,

	@field:SerializedName("revelationType")
	val revelationType: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("englishNameTranslation")
	val englishNameTranslation: String? = null
) : Parcelable
