package com.dicoding.sahamiq.core.data.source.remote.response

import com.google.gson.annotations.*

data class NewsResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null,
)

data class Publisher(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,
)

data class Data(

	@field:SerializedName("results")
	val results: List<ResultsItem>? = null,
)

data class ResultsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("publisher")
	val publisher: Publisher? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("published_at")
	val publishedAt: String? = null,

	@field:SerializedName("url")
	val url: String? = null,
)
