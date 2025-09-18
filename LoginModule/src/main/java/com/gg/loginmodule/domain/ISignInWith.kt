package com.gg.loginmodule.domain

import com.gg.loginmodule.domain.models.UserAuth

interface ISignInWith {

    suspend fun signInWithGoogle(): UserAuth?
    suspend fun signInWithEmail(email: String, password: String): UserAuth?
}