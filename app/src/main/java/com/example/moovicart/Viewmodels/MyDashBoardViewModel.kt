package com.example.moovicart.Viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codequest.util.product
import com.example.ecom.util.Resource
import com.example.moovicart.util.Communicator
import com.example.moovicart.util.DashBoardDc
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class MyDashBoardViewModel @Inject constructor()  : ViewModel() {

    private val firebase= Firebase.firestore

    private val _c= MutableStateFlow<Resource<List<DashBoardDc>>>(Resource.unspecified())
    val c: StateFlow<Resource<List<DashBoardDc>>> = _c
    private var _likes = 0
    val likes: Int
        get() = _likes
    private var _shares = 0
    val shares:Int
        get() = _shares
    private var _rating = 0
    val rating:Int
        get() = _rating
    private var _redeem = 0
    val redeem:Int
        get() = _redeem


    init {
        fetchdata()
    }

    private fun getlike() {

    }

    private fun fetchdata() {

        viewModelScope.launch {
            _c.emit (Resource.Loading())

        }



        firebase.collection("MovieInfo").get().addOnSuccessListener { task ->
            val pro = task.toObjects(DashBoardDc::class.java)
            var si = pro.size


            viewModelScope.launch {
                _c.emit(Resource.Success(pro))
                while (si>0){
                    if (pro[si-1].mlikes!="null"){
                        _likes+=pro[si-1].mlikes.toInt()

                    }
                    if (pro[si-1].mshare!="null"){
                        _shares+=pro[si-1].mshare.toInt()

                    }
                    if(pro[si-1].mrating!="null"){
                        _rating+=pro[si-1].mrating.toInt()

                    }
                    if(pro[si-1].mredeem!="null"){
                        _redeem+=pro[si-1].mredeem.toInt()
                    }
                    si-=1
                }
                Log.d("poi","$_likes $_shares $_rating $_redeem")

//                getlike(0)
            }
        }
            .addOnFailureListener {
                viewModelScope.launch {
                    _c.emit(Resource.Error(it.message.toString()))
                }
            }
    }



}