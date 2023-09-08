package com.example.moovicart.Viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codequest.util.product
import com.example.ecom.util.Resource

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyMoviesListViewModel @Inject constructor()  :ViewModel() {

    private val firebase= Firebase.firestore

    val _c= MutableStateFlow<Resource<List<product>>>(Resource.unspecified())
    val c: StateFlow<Resource<List<product>>> = _c

    init {
        fetchdata()
    }

    private fun fetchdata() {

        viewModelScope.launch {
            _c.emit (Resource.Loading())
        }



        firebase.collection("myMovies")
            .whereEqualTo("category","my movies").get().addOnSuccessListener { result ->
                val ProductList=result.toObjects(product::class.java)
                viewModelScope.launch {
                    _c.emit(Resource.Success(ProductList))
                }

            }

            .addOnFailureListener {
                viewModelScope.launch {
                    _c.emit(Resource.Error(it.message.toString()))
                }
            }
    }


}