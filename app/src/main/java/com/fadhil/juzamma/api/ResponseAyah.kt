package com.fadhil.juzamma.api

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseAyah(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<DataSurahItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class DataSurahItem(

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

	@field:SerializedName("edition")
	val edition: Edition? = null,

	@field:SerializedName("ayahs")
	val ayahs: List<AyahsItem?>? = null,

	@field:SerializedName("englishNameTranslation")
	val englishNameTranslation: String? = null
) : Parcelable

@Parcelize
data class AyahsItem(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("hizbQuarter")
	val hizbQuarter: Int? = null,

	@field:SerializedName("ruku")
	val ruku: Int? = null,

	@field:SerializedName("manzil")
	val manzil: Int? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("sajda")
	val sajda: Boolean? = null,

	@field:SerializedName("numberInSurah")
	val numberInSurah: Int? = null,

	@field:SerializedName("juz")
	val juz: Int? = null,

	@field:SerializedName("audioSecondary")
	val audioSecondary: List<String?>? = null,

	@field:SerializedName("audio")
	val audio: String? = null,

) : Parcelable

@Parcelize
data class Edition(

	@field:SerializedName("identifier")
	val identifier: String? = null,

	@field:SerializedName("englishName")
	val englishName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("format")
	val format: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("direction")
	val direction: String? = null
) : Parcelable
