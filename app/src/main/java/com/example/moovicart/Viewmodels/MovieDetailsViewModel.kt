package com.example.moovicart.Viewmodels

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecom.util.Resource
import com.example.moovicart.util.Communicator
import com.example.moovicart.util.OverViewDc
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel@Inject constructor(): ViewModel() {
    val firebase = Firebase.firestore
    var fmdlistner: Communicator?=null
//    private var _k = String()
    val isliked = MutableLiveData<String>()
    val israting = MutableLiveData<String>()
    val rating_value = MutableLiveData<String>()
    val share_value = MutableLiveData<String>()

    fun onLikeClicked(view:View){

        fmdlistner?.onStarted()

    }

    fun ftotlikevalue(doc: String){
        firebase.collection("MovieInfo").document(doc).update("islike",true,
        "mlikes","10")
            .addOnSuccessListener {
                isLikeCheckedOrNot(doc)
            }.addOnFailureListener {
                viewModelScope.launch {
//                    Toast.makeText(con, "error", Toast.LENGTH_SHORT).show()
                    fmdlistner?.onFailure(it.message.toString())
                }
            }
    }

    fun ftotratingvalue(doc: String,rat:String){
        firebase.collection("MovieInfo").document(doc).update("israting",true,
            "mrating",rat)
            .addOnSuccessListener {
                isRatingCheckedOrNot(doc)
            }.addOnFailureListener {
                viewModelScope.launch {
//                    Toast.makeText(con, "error", Toast.LENGTH_SHORT).show()
                    fmdlistner?.onFailure(it.message.toString())
                }
            }
    }


    fun ttoflikevalue(doc: String){
        firebase.collection("MovieInfo").document(doc).update("islike",false,
            "mlikes","null").addOnSuccessListener {
                isLikeCheckedOrNot(doc)
        }.addOnFailureListener {
            viewModelScope.launch {
//                    Toast.makeText(con, "error", Toast.LENGTH_SHORT).show()
                fmdlistner?.onFailure(it.message.toString())
            }
        }

    }

//    fun ttofratingvalue(doc: String,rat: String){
//        firebase.collection("MovieInfo").document(doc).update("israting",false,
//            "mrating",rat)
//            .addOnSuccessListener {
//            isRatingCheckedOrNot(doc)
//        }.addOnFailureListener {
//            viewModelScope.launch {
////                    Toast.makeText(con, "error", Toast.LENGTH_SHORT).show()
//                fmdlistner?.onFailure(it.message.toString())
//            }
//        }
//
//    }

    fun getrating(doc: String){
        firebase.collection("MovieInfo").document(doc).get().addOnSuccessListener { value ->
            rating_value.postValue(value.data!!["mrating"].toString())
        }
            .addOnFailureListener {
                viewModelScope.launch {
//                    Toast.makeText(con, "error", Toast.LENGTH_SHORT).show()
                    fmdlistner?.onFailure(it.message.toString())
                }
            }
    }
    fun incshare(doc: String,context: Context){
        viewModelScope.launch {
            firebase.collection("MovieInfo").document(doc).get().addOnSuccessListener { value ->
//                share_value.postValue(value.data!!["mshare"].toString())
                var temp = 0
                temp += value.data!!["mshare"].toString().toInt()
                firebase.collection("MovieInfo").document(doc).update("mshare","${temp+10}")
                    .addOnSuccessListener {
                        Toast.makeText(context,"ADDED 10 POINTS TO BACKEND",Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        viewModelScope.launch {
//                    Toast.makeText(con, "error", Toast.LENGTH_SHORT).show()
                            fmdlistner?.onFailure(it.message.toString())
                        }
                    }
            }
                .addOnFailureListener {
                    viewModelScope.launch {
//                    Toast.makeText(con, "error", Toast.LENGTH_SHORT).show()
                        fmdlistner?.onFailure(it.message.toString())
                    }
                }
        }


    }



    fun isRatingCheckedOrNot(doc: String){
        firebase.collection("MovieInfo").document(doc).get().addOnSuccessListener { value ->
            israting.postValue(value.data!!["israting"].toString())
        }
            .addOnFailureListener {
                viewModelScope.launch {
//                    Toast.makeText(con, "error", Toast.LENGTH_SHORT).show()
                    fmdlistner?.onFailure(it.message.toString())
                }
            }
    }
    fun isLikeCheckedOrNot(doc:String){
        firebase.collection("MovieInfo").document(doc).get().addOnSuccessListener { value ->
            isliked.postValue(value.data!!["islike"].toString())
        }
            .addOnFailureListener {
                viewModelScope.launch {
//                    Toast.makeText(con, "error", Toast.LENGTH_SHORT).show()
                    fmdlistner?.onFailure(it.message.toString())
                }
            }
    }

//        if (firebase.collection("MovieInfo").document(doc).get()!!.islike) { // alread liked
//            likebt.isChecked = true
//            likebt.isClickable = true
//            likebt.setOnClickListener {
//                firebase.collection("MovieInfo").document(data.docid).update(
//                    "mlikes", "null", "islike", false
//                )
//                likebt.isClickable = true
//
//
//            }
//
//        } else { // if not liked yet
//            likebt.isChecked = false
//            likebt.isClickable = true
//            likebt.setOnClickListener {
////                                Toast.makeText(requireContext(),"hi",Toast.LENGTH_SHORT).show()
//                firebase.collection("MovieInfo").document(data.docid).update(
//                    "mlikes", "20", "islike", true
//                )
//
//
//            }
//
//
//        }




//    private fun fetchdata() {
//        viewModelScope.launch {
//        }
//        firebase.collection("MovieInfo").get().addOnSuccessListener { task ->
//            val pro = task.toObjects(OverViewDc::class.java)
//            _des=pro.get(i).ov_des
//            viewModelScope.launch{
//                _c.emit(Resource.Success(pro))
//            }
//        }
//            .addOnFailureListener {
//                viewModelScope.launch{
//                    _c.emit(Resource.Error(it.message.toString()) )
//                }
//            }
//    }
}