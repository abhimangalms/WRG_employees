package com.whiterabbit.wrgemployees.utils

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController

object Utils {

    fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
        Intent(this, activity).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }



    fun Fragment.safeNavigate(directions: NavDirections) {
        val currentDestination = findNavController().currentDestination as FragmentNavigator.Destination
        if (javaClass.name == currentDestination.className) {
            findNavController().navigate(directions)
        }
    }

}