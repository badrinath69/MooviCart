package com.example.moovicart.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.moovicart.BaseActivity
import com.example.moovicart.R
import com.example.moovicart.data.UserData
import com.example.moovicart.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebase: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        firebase= FirebaseFirestore.getInstance()

        binding.btn.setOnClickListener {

            val fname=binding.fname.text.toString()
            val lname=binding.lname.text.toString()
            val cc=binding.cc.text.toString()
            val cn=binding.cn.text.toString()
            val email=binding.email.text.toString()
            val password=binding.pass.text.toString()
            val repass=binding.rePass.text.toString()

            val user=UserData(
                fname = fname,
                lname = lname,
                contact_no = cc,
                country_code = cn,
                email = email,
                password = password
            )

            if (fname.isNotEmpty() && lname.isNotEmpty() && cc.isNotEmpty() && cn.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
                    if (password==repass){
                            binding.btn.text="Signing Up ..."
                            firebaseAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        FirebaseFirestore.getInstance().collection("users")
                                            .document(firebaseAuth.uid!!)
                                            .set(user).addOnSuccessListener {
                                                startActivity(Intent(requireContext(), BaseActivity::class.java))
                                            }
                                    }
                                }

                    }
            }

        }
    }


}