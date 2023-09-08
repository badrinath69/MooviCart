package com.example.moovicart.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.ecom.util.Resource
import com.example.moovicart.Adapter.ViewPagerAdapter
import com.example.moovicart.CastAndCrew
import com.example.moovicart.InfoPages.InfoActivity
import com.example.moovicart.Media
import com.example.moovicart.R
import com.example.moovicart.RelatedMovies
import com.example.moovicart.TheatreAndShow
import com.example.moovicart.Viewmodels.MovieDetailsViewModel
import com.example.moovicart.Viewmodels.MyDashBoardViewModel
import com.example.moovicart.databinding.FragmentMovieDetailsBinding
import com.example.moovicart.databinding.FragmentMyDashboardBinding
import com.example.moovicart.util.Communicator
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.collectLatest
import org.w3c.dom.Text

class MovieDetailsFragment : Fragment() {

    var po:Int?=null
    private lateinit var tab: TabLayout
    private val moviedetailviewmodel by viewModels<MovieDetailsViewModel>()
    private val mydashviewmodel by viewModels<MyDashBoardViewModel>()

    private lateinit var communicator: Communicator




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
//                NavHostFragment.findNavController(this@MovieDetailsFragment).navigateUp();

                findNavController().navigate(R.id.action_movieDetailsFragment_to_myDashboardFragment)

//                if (true == conditionForCustomAction) {
//                    CustomActionHere()
//                } else        {
//                    NavHostFragment.findNavController(this@MovieDetailsFragment).navigateUp()
//                }

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, onBackPressedCallback
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        val moviedetailviewmodel = ViewModelProvider(requireActivity()).get(MovieDetailsViewModel::class.java)


        po = arguments?.getString("posi")?.toInt()
        val likebt = view.findViewById<CheckBox>(R.id.fmd_likechecked)
        val ratingbt = view.findViewById<RatingBar>(R.id.fmd_ratingBar)
        val pic = view.findViewById<ImageView>(R.id.fmv_photo)
        val pictext = view.findViewById<TextView>(R.id.fmv_pictext1)
        val headingtext = view.findViewById<TextView>(R.id.fmv_pictext2)
        val rightstext = view.findViewById<TextView>(R.id.fmv_rightstxt)
        val rightsplace = view.findViewById<TextView>(R.id.fmv_rightsplace)
        val ratingtext = view.findViewById<TextView>(R.id.fmd_ratingtv)
        val share_wa = view.findViewById<ImageView>(R.id.fmv_swhatsapp)
        val share_insta = view.findViewById<ImageView>(R.id.fmv_sinsta)
        val share_twitter = view.findViewById<ImageView>(R.id.fmv_stwitter)

//        Toast.makeText(requireContext(), "positon = ${po}", Toast.LENGTH_SHORT).show()

//        likebt.setOnClickListener {
//            lifecycleScope.launchWhenStarted {
//
//
//            }
//        }
        lifecycleScope.launchWhenStarted{
            mydashviewmodel.c.collectLatest {
                when(it) {
                    is Resource.Loading -> {
//                        showLoading()
                        likebt.isClickable=false
                        ratingbt.isClickable=false
                    }
                    is Resource.Success -> {

                        likebt.isClickable=true
                        ratingbt.isClickable=true
                        val data = it.data?.get(po!!)
                        Glide.with(requireActivity()).load(data?.mimg).into(pic)
                        pictext.text=data?.mname.toString()
                        headingtext.text=data?.mname.toString()
                        rightsplace.text=data?.distributedloc.toString()
                        share_insta.setOnClickListener {
                            moviedetailviewmodel.incshare(data?.docid.toString(),requireContext())
                        }
                        share_wa.setOnClickListener {
                            moviedetailviewmodel.incshare(data?.docid.toString(),requireContext())
                        }
                        share_twitter.setOnClickListener {
                            moviedetailviewmodel.incshare(data?.docid.toString(),requireContext())
                        }
                        moviedetailviewmodel.isLikeCheckedOrNot(data?.docid.toString())
                        moviedetailviewmodel.isRatingCheckedOrNot(data?.docid.toString())
                        moviedetailviewmodel.israting.observe(viewLifecycleOwner){

//                                Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                            if (it=="true") { // alread rating given

                                ratingbt.setIsIndicator(true)
                                moviedetailviewmodel.getrating(data?.docid.toString())
                                moviedetailviewmodel.rating_value.observe(viewLifecycleOwner){it->
//                                    ratingbt.numStars = it.toInt()/10
                                    ratingtext.text=(it.toInt()/10).toString()
                                    val rat = (it.toInt()/10).toFloat()
//                                    Toast.makeText(requireContext(), "$rat", Toast.LENGTH_SHORT).show()

                                    ratingbt.rating=rat
                                }

                            } else { // if not rating given yet
//                                ratingbt.isChecked = false

//                                Toast.makeText(requireContext(), "hlo", Toast.LENGTH_SHORT).show()


                                ratingbt.isClickable = true
                                ratingbt.setOnRatingBarChangeListener { p0, r, p2 -> //                                        val r = ratingbt.rating
                                    val r2 = r.toInt() * 10
                                    Toast.makeText(requireContext(), "$r2", Toast.LENGTH_SHORT)
                                        .show()

                                    moviedetailviewmodel.ftotratingvalue(
                                        data?.docid.toString(),
                                        r2.toString()
                                    )
                                }


                            }

                        }

                        moviedetailviewmodel.isliked.observe(viewLifecycleOwner){

//                                Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                            if (it=="true") { // alread liked
                                likebt.isChecked = true
                                likebt.isClickable = true
                                likebt.setOnClickListener {
                                    moviedetailviewmodel.ttoflikevalue(data?.docid.toString())
                                    likebt.isClickable = true


                                }

                            } else { // if not liked yet
                                likebt.isChecked = false
                                likebt.isClickable = true
                                likebt.setOnClickListener {
//                                Toast.makeText(requireContext(),"hi",Toast.LENGTH_SHORT).show()
                                    moviedetailviewmodel.ftotlikevalue(data?.docid.toString())


                                }


                            }

                        }

//                        holder.likes.isChecked=false
//                        dataList[position].isLike=false
//                        dataList[position].isDislike=false
//                        dataList[position].likes-=1
//                        holder.likescounter.text = (dataList[position].likes).toString()






                    }
                    is Resource.Error -> {
//                        hideLoading()

                        likebt.isClickable=false
                        ratingbt.isClickable=false
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    }
                    else ->Unit

                }
            }

        }


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_movie_details, container, false)
        // set the references of the declared objects above
        tab = view.findViewById(R.id.fmv_tabs)
        // Initializing the ViewPagerAdapter
        val fragmentList = arrayListOf<Fragment>(
            OverViewFragment(),
            CastAndCrew(),
            Media(),
            RelatedMovies(),
            TheatreAndShow()
        )

//        ViewPagerAdapter
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        val pager = view.findViewById<ViewPager2>(R.id.fmv_viewPager)
        pager.adapter = adapter
        pager.isSaveEnabled=false
        val backbt = view.findViewById<ImageView>(R.id.fmv_backbt)
        backbt.setOnClickListener {
            findNavController().navigate(R.id.action_movieDetailsFragment_to_myDashboardFragment)
        }
        TabLayoutMediator(tab, pager) { tab, position ->
            when(position){
                0 -> {
                    tab.text = "OVERVIEW"
                }
                1 -> {
                    tab.text = "CAST & CREW"
                }
                2 -> {
                    tab.text = "MEDIA"
                }
                3 -> {
                    tab.text = "RELATED MOVIES"
                }
                4 -> {
                    tab.text = "THEATRE & SHOW"
                }

            }
        }.attach()
        tab.tabMode = TabLayout.MODE_SCROLLABLE
        return view
    }








}