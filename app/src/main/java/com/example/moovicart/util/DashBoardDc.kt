package com.example.moovicart.util

data class DashBoardDc(
    val mimg: String,
    val mname: String,
    val mlikes:String,
    val mrating:String,
    val mredeem:String,
    val mshare:String,
    val docid:String,
    val islike:Boolean,
    val israting:Boolean,
    val distributedloc:String

){
    constructor(): this("","","","","","","",false,false,"")
}
