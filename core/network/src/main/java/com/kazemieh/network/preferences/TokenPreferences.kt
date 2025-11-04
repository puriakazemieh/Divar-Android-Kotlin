package com.kazemieh.network.preferences

import android.content.SharedPreferences
import com.kazemieh.secure_shared_pref.di.SharedPrefConstant
import javax.inject.Inject


class TokenPreferences @Inject constructor(val sharedPreferences: SharedPreferences) {
    fun readToken(): String {
        return sharedPreferences.getString(SharedPrefConstant.TOKEN, "") ?: ""
    }
}