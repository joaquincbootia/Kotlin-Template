package com.uruguayemergencia.viewModels

import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    private var email: String? = null
    private var password: String? = null

    fun setEmailAndPassword(email: String, password: String) {
        this.email = email
        this.password = password
    }

    fun clearEmailAndPassword() {
        email = null
        password = null
    }

    fun signUpUser() {
        val email = this.email
        val password = this.password
    }

}