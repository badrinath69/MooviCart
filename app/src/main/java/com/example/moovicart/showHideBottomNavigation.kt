package com.example.moovicart

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Fragment.hide(){
    val bn=(activity as BaseActivity).findViewById<BottomNavigationView>(R.id.bottom)
    bn.visibility= View.GONE
}
fun Fragment.show(){
    val bn=(activity as BaseActivity).findViewById<BottomNavigationView>(R.id.bottom)
    bn.visibility= View.VISIBLE
}

