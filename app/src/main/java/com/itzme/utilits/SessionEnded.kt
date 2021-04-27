package com.itzme.utilits

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.afollestad.materialdialogs.MaterialDialog


object SessionEnded {
    fun dialogSessionEnded(
            activity: Activity,
            navController: NavController,
            id: Int,
            navDirections: NavDirections
    ) {

        val dialog = MaterialDialog(activity)
                .title(null, "Oops!")
                .message(null, "Sorry your session has expired, please log in again")
                .cancelable(false)
                .cornerRadius(16f, null)
                .positiveButton(null, "LogOut") { dialog ->
                    if (navController.currentDestination?.id == id) {
                        PreferencesUtils(activity).getInstance()?.clear()
                        val action = navDirections.actionId
                        navController.navigate(action)
                        dialog.dismiss()
                    }
                }

        dialog.show()

    }
}