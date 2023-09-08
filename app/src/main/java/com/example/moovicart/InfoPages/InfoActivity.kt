package com.example.moovicart.InfoPages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.codequest.util.product
import com.example.ecom.util.Resource
import com.example.moovicart.Fragments.BenefitsFragment
import com.example.moovicart.Fragments.HowItWorksFragment
import com.example.moovicart.Fragments.LatestMoviesFragment
import com.example.moovicart.Fragments.MyDashboardFragment
import com.example.moovicart.Fragments.MyProfileFragment
import com.example.moovicart.Fragments.MovieDetailsFragment
import com.example.moovicart.Fragments.Producer.MyMovieListFragment
import com.example.moovicart.R
import com.example.moovicart.databinding.ActivityInfoBinding
import com.example.moovicart.util.Communicator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoActivity : AppCompatActivity(),Communicator {
    private var currentFragmentIndex: Int =0
    private lateinit var binding: ActivityInfoBinding
    val latestmovies_frag = LatestMoviesFragment()
    val profile_frag = MyProfileFragment()
    val dashboard_frag = MyDashboardFragment()
    val benefits_frag = BenefitsFragment()
    val howitworks_frag = HowItWorksFragment()
    val moviedetails = MovieDetailsFragment()
    val myMovieListFragment = MyMovieListFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)
        windowInsetsController?.isAppearanceLightNavigationBars = true
        windowInsetsController?.isAppearanceLightStatusBars = true
        val navController = this.findNavController(R.id.info_frame)
        binding.infoBottomNavigationView.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(item,navController)
            return@setOnItemSelectedListener true
        }
    }

    override fun passPosition(position: Int) {
        val bundle = Bundle()
        bundle.putInt("2posi",position)


    }

    override fun onStarted() {

    }

    override fun onFailure(error: String) {
    }

    override fun onSuccess(success: String) {
    }

    companion object{
    private var position: Int?=null
    fun setposi(po:Int){
        position =po
    }
    @JvmStatic fun getpo(): Int {
        return position!!
    }
}
}