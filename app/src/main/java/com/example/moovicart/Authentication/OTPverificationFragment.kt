package com.example.moovicart.Authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.moovicart.Authentication.Verification.FirebaseLoginResponseStates
import com.example.moovicart.Authentication.Verification.LoginViewModel
import com.example.moovicart.BaseActivity
import com.example.moovicart.Fragments.HomeFragment
import com.example.moovicart.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class OTPVerifyFragment() : Fragment() {
    private lateinit var parentView: View
    private lateinit var editTextOtp1: EditText
    private lateinit var editTextOtp2: EditText
    private lateinit var editTextOtp3: EditText
    private lateinit var editTextOtp4: EditText
    private lateinit var editTextOtp5: EditText
    private lateinit var editTextOtp6: EditText
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify_o_t_p, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeAllViewsAndVars()

    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        if (nextAnim != 0) {
            return AnimationUtils.loadAnimation(context, nextAnim)
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }


    private fun initializeAllViewsAndVars() {
        // initializing all the Views and Variables
        parentView = requireView()
        editTextOtp1 = parentView.findViewById(R.id.optET1); editTextOtp2 =
            parentView.findViewById(R.id.optET2)
        editTextOtp3 = parentView.findViewById(R.id.optET3); editTextOtp4 =
            parentView.findViewById(R.id.optET4)
        editTextOtp5 = parentView.findViewById(R.id.optET5); editTextOtp6 =
            parentView.findViewById(R.id.optET6)
        val timerTV: TextView = parentView.findViewById(R.id.otpTimerTVSignupVerification)
        val buttonVerifyOtp: Button = parentView.findViewById(R.id.verify_otp_signup_user)
        val myLoginViewModel = LoginViewModel()

        // setting up the OTP edit text and OTP Timer
        setUpOtpView()
        buttonVerifyOtp.setOnClickListener {
            // verifying the entered OTP by the user.
            val enteredOTP = editTextOtp1.text.toString() + editTextOtp2.text.toString() +
                    editTextOtp3.text.toString() + editTextOtp4.text.toString() +
                    editTextOtp5.text.toString() + editTextOtp6.text.toString()

            myLoginViewModel.verifyWithOtp(enteredOTP)
        }

        myLoginViewModel.signInStates().observe(this.viewLifecycleOwner) {
            if (it is FirebaseLoginResponseStates.LoginSuccessState) {
                countDownTimer.cancel()
                redirectToHomeFragment()
            } else if (it is FirebaseLoginResponseStates.ErrorLoginState) {
                Snackbar.make(parentView, "Invalid OTP", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
        setUpOTPTimer(timerTV)
    }

    private fun redirectToHomeFragment() {
        parentFragmentManager.popBackStack()

//        parentFragmentManager.beginTransaction()
//            .replace(R.id.verify, HomeFragment())
//            .setCustomAnimations(R.anim.fragment_slide_in, R.anim.fragment_slide_out)
//            .commit()

        startActivity(Intent(requireContext(), BaseActivity::class.java))
    }

    // requesting focus for first OTP EditText and
    // assigning each OTP EditText a Text Watcher
    private fun setUpOtpView() {
        editTextOtp1.isEnabled = true
        editTextOtp1.addTextChangedListener(OtpTextWatcher(editTextOtp1))
        editTextOtp2.addTextChangedListener(OtpTextWatcher(editTextOtp2))
        editTextOtp3.addTextChangedListener(OtpTextWatcher(editTextOtp3))
        editTextOtp4.addTextChangedListener(OtpTextWatcher(editTextOtp4))
        editTextOtp5.addTextChangedListener(OtpTextWatcher(editTextOtp5))
        editTextOtp6.addTextChangedListener(OtpTextWatcher(editTextOtp6))
    }

    // A TextWatcher to auto jump over each EditText
    // while entering OTP by the user
    inner class OtpTextWatcher(private val givenView: View) : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // nothing to do here
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // nothing to do here
        }

        override fun afterTextChanged(p0: Editable?) {
            // checking if the current entered Text is Empty or not
            // in each EditText and requesting focus accordingly
            val enteredText = p0.toString()
            when (givenView.id) {
                R.id.optET1 -> if (enteredText.length == 1) editTextOtp2.requestFocus()
                R.id.optET2 -> if (enteredText.length == 1) editTextOtp3.requestFocus() else if (enteredText.isEmpty()) editTextOtp1.requestFocus()
                R.id.optET3 -> if (enteredText.length == 1) editTextOtp4.requestFocus() else if (enteredText.isEmpty()) editTextOtp2.requestFocus()
                R.id.optET4 -> if (enteredText.length == 1) editTextOtp5.requestFocus() else if (enteredText.isEmpty()) editTextOtp3.requestFocus()
                R.id.optET5 -> if (enteredText.length == 1) editTextOtp6.requestFocus() else if (enteredText.isEmpty()) editTextOtp4.requestFocus()
                R.id.optET6 -> if (enteredText.isEmpty()) editTextOtp5.requestFocus()
            }
        }
    }

    // Setting up the OTP timer
    private fun setUpOTPTimer(timerText: TextView) {
        countDownTimer = object : CountDownTimer(50000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerText.text =
                    resources.getString(R.string.otp_countdownText, millisUntilFinished / 1000)
            }
            override fun onFinish() {
                timerText.text = requireContext().getString(R.string.otp_countdownFinishText)
                timerText.setTextColor(resources.getColor(R.color.black))
            }
        }.start()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onDestroyView() {
        super.onDestroyView()

        countDownTimer.cancel()
    }


}