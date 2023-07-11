package com.example.moovicart.Authentication.Verification

import android.app.Activity
import com.example.moovicart.Authentication.Verification.LoginMethodFirebase
import com.google.firebase.auth.FirebaseAuth

class LoginRepository {

    fun sendOtpToMobileNumber(
        firebaseAuth: FirebaseAuth,
        givenMobileNumber: String,
        parentActivity: Activity
    ) =
        LoginMethodFirebase.sendOtpToMobileNumber(firebaseAuth, givenMobileNumber, parentActivity)

    fun getLoginStates() = LoginMethodFirebase.loginStates
    fun resetLoginStates() = LoginMethodFirebase.resetLoginStates()

    fun verifyOtp(enteredOtp: String) = LoginMethodFirebase.verifyUserWithOTP(enteredOtp)
}