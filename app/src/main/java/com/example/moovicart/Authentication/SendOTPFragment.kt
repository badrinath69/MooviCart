package com.example.moovicart.Authentication

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.moovicart.Authentication.Verification.FirebaseLoginResponseStates
import com.example.moovicart.Authentication.Verification.LoginViewModel
import com.example.moovicart.R
import com.example.moovicart.databinding.FragmentSendOtpBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class SendOTPFragment : Fragment() {

    private lateinit var binding: FragmentSendOtpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var parentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSendOtpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewsAndVariables()

        val ccp = binding.ccp
        val num = binding.phoneNumEtSignup

        ccp.registerCarrierNumberEditText(num)

        binding.myCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showDialog()
            }
        }

        val text=ccp.fullNumberWithPlus.toString()
        val bundle = Bundle()
        bundle.putString("Number",text)

        val fragment = OTPVerifyFragment()
        fragment.arguments = bundle

    }

    private fun initializeViewsAndVariables() {

        firebaseAuth = FirebaseAuth.getInstance()
        parentView = requireView()
        val ccp = binding.ccp

        val editTextSignUp = binding.phoneNumEtSignup
        val buttonGetOtp = binding.getOtpSignupUser
        val progressIndicator = binding.sendOtpProgressIndicator


        val myViewModel = LoginViewModel()
        buttonGetOtp.setOnClickListener {
            // sending OTP to valid Mobile Numbers

            if (binding.ccp.isValidFullNumber) {

                buttonGetOtp.text = ""; progressIndicator.visibility = View.VISIBLE
                myViewModel.sendOtpToMobileNumber(
                    firebaseAuth,
                    ccp.fullNumberWithPlus,
                    requireActivity()
                )
            } else {
                Snackbar.make(parentView, "Invalid Mobile Number", Snackbar.LENGTH_LONG).show()
            }
        }

        // observing the Login States sent by the ViewModel
        myViewModel.signInStates().observe(this.viewLifecycleOwner) {
            if (it is FirebaseLoginResponseStates.CodeSentLoginState) {
                myViewModel.resetSignInStates()

                parentFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fragment_slide_in, R.anim.fragment_slide_out)
                    .replace(
                        R.id.layout, OTPVerifyFragment()
                    )
                    .commit()

            } else if (it is FirebaseLoginResponseStates.ErrorLoginState) {
                myViewModel.resetSignInStates()
                buttonGetOtp.text = "Get OTP"; progressIndicator.visibility = View.INVISIBLE
                Snackbar.make(
                    parentView,
                    "Error : ${it.errorMessage}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun showDialog() {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setMessage("Terms and conditions matter here !...")
            .setPositiveButton("Agree", DialogInterface.OnClickListener { dialog, _ ->
                // Handle OK button click
                dialog.dismiss()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
                // Handle Cancel button click
                dialog.dismiss()
                binding.myCheckbox.isChecked = false // Uncheck the checkbox
            })

        val dialog = dialogBuilder.create()
        dialog.show()
    }

}