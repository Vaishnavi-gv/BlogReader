package com.example.blogreader.data.model

data class BlogPost(
    val id: Int,
    val date: String,
    val title: Rendered,
    val content: Rendered,
    val excerpt: Rendered,
    val link: String
)
data class Rendered(
    val rendered: String
)

