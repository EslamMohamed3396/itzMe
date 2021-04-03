package com.itzme.utilits

import android.app.Application
import android.content.Context
import com.google.firebase.messaging.FirebaseMessaging
import com.itzme.BuildConfig
import timber.log.Timber

class App : Application() {

    companion object {
        private var mContext: App? = null
        private var token: String? = null

        fun getContext(): Context? {
            return mContext
        }

        fun getToken(): String? {
            return token
        }

    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
        getToken()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }


    private fun getToken() {
        FirebaseMessaging.getInstance().token.addOnSuccessListener { task ->
            token = task
        }
    }
}