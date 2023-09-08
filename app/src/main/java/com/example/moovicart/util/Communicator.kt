package com.example.moovicart.util

interface Communicator {
    fun passPosition(position:Int)
    fun onStarted()
    fun onFailure(error:String)
    fun onSuccess(success:String)
}
//if(dataList[position].isLike){
//    holder.likes.isChecked=false
//    dataList[position].isLike=false
//    dataList[position].isDislike=false
//    dataList[position].likes-=1
//    holder.likescounter.text = (dataList[position].likes).toString()
//
//
//}else{
//    if(dataList[position].isDislike){
//        dataList[position].dislikes-=1
//        holder.dislikescounter.text = (dataList[position].dislikes).toString()
//
//    }
//    holder.likes.isChecked=true
//    dataList[position].isLike=true
//    holder.dislikes.isChecked=false
//    dataList[position].isDislike=false
//    dataList[position].likes+=1
//    holder.likescounter.text = (dataList[position].likes).toString()
//
//
//
//
//}
