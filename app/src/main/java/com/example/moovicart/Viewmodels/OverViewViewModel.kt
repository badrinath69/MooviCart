package com.example.moovicart.Viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecom.util.Resource
import com.example.moovicart.util.DashBoardDc
import com.example.moovicart.util.OverViewDc
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class OverViewViewModel @Inject constructor(): ViewModel(){
    val firebase = Firebase.firestore
    private val _c = MutableStateFlow<Resource<List<OverViewDc>>>(Resource.unspecified())
    val c: StateFlow<Resource<List<OverViewDc>>> = _c
    private var _des= String()
    val des: String
        get() = _des
    init {
        fetchdata(0)
    }


    fun getdes():String{
        return _des
    }

    private fun fetchdata(i: Int) {
        viewModelScope.launch {
            _c.emit(Resource.Loading())
        }
        firebase.collection("MovieInfo").get().addOnSuccessListener { task ->
            val pro = task.toObjects(OverViewDc::class.java)
            _des=pro.get(i).ov_des
            viewModelScope.launch{
                _c.emit(Resource.Success(pro))
            }
        }
            .addOnFailureListener {
                viewModelScope.launch{
                    _c.emit(Resource.Error(it.message.toString()) )
                }
            }
    }
}