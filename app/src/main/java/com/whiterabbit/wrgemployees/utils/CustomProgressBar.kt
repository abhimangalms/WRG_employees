package com.whiterabbit.wrgemployees.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.WindowManager
import android.widget.ProgressBar
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.whiterabbit.wrgemployees.R

class CustomProgressBar  {


    companion object{
        private var customDialogProgress: Dialog? = null
        private var doubleBounce: Sprite? = null
        fun startProgressBar(context: Context?) {
            customDialogProgress = Dialog(context!!)
            customDialogProgress!!.setContentView(R.layout.popup_progressbar)
            customDialogProgress!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            customDialogProgress!!.setCancelable(false)

            val window = customDialogProgress!!.window
            val windowsParams = window!!.attributes
            windowsParams.width = WindowManager.LayoutParams.MATCH_PARENT
            windowsParams.height = WindowManager.LayoutParams.WRAP_CONTENT
            windowsParams.gravity = Gravity.CENTER
            windowsParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            window.attributes = windowsParams
            val progressBar = customDialogProgress!!.findViewById<ProgressBar>(R.id.spin_kit)
            doubleBounce = DoubleBounce()
            progressBar.indeterminateDrawable = doubleBounce

            customDialogProgress!!.show()
        }


        fun progressBarStop() {
            if (customDialogProgress != null && customDialogProgress!!.isShowing) {
                customDialogProgress!!.dismiss()
            }
            doubleBounce = null
        }
    }

}