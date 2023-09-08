package com.example.moovicart.util

data class OverViewDc(
    val ov_des : String,
    val ov_images:List<String>,
    val ov_vid:List<String>
){
    constructor():this("", listOf(""), listOf(""))
}