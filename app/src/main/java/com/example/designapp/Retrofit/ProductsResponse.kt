package com.example.designapp.Retrofit

data class ProductsResponse(
    var id: Int,
    var title: String,
    var price: Double,
    var description: String,
    var category: String,
    var image: String,
    var rating: com.example.designapp.Retrofit.Rating
)
