package com.kontrakanprojects.appresepobat.utils

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.kontrakanprojects.appresepobat.R
import www.sanju.motiontoast.MotionToast

const val EXTRA_OBJECT_TYPE = "EXTRA_OBJECT_TYPE"

fun View.snackbar(message: String) {
    Snackbar.make(
        this,
        message,
        Snackbar.LENGTH_LONG
    ).also { snackbar ->
        snackbar.setAction("OKE") {
            snackbar.dismiss()
        }
//        snackbar.view.apply {
//            setBackgroundColor(backgroundColor)
//        }
    }.show()
}

fun showMessage(
    activity: Activity,
    title: String,
    message: String = "Cek Koneksi Internet Dan Coba Lagi!",
    style: String
) {
    MotionToast.createColorToast(
        activity,
        title,
        message,
        style,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(activity, R.font.helvetica_regular)
    )
}

fun checkValue(value: String, ti: TextInputEditText, errorMessage: String): Boolean {
    if (value.isEmpty()) {
        ti.error = errorMessage
        return false
    } else {
        ti.error = null
    }
    return true
}

fun isLoading(
    state: Boolean,
    pb: ProgressBar,
    isClickButton: Boolean = false,
    btn: MaterialButton? = null
) {
    if (isClickButton) {
        if (state) {
            pb.visibility = View.VISIBLE
            btn!!.visibility = View.GONE
        } else {
            pb.visibility = View.GONE
            btn!!.visibility = View.VISIBLE
        }
    } else {
        if (state) {
            pb.visibility = View.VISIBLE
        } else {
            pb.visibility = View.GONE
        }
    }
}


fun dataNotFound(
    img: ImageView,
    ti: TextView,
    message: String? = "Data Gagal diperoleh, silahkan cek koneksi dan coba kembali."
) {
    ti.visibility = View.VISIBLE
    ti.text = message
    img.visibility = View.VISIBLE
}

fun isLoadingImage(state: Boolean, pb: ProgressBar) {
    if (state) {
        pb.visibility = View.VISIBLE
    } else {
        pb.visibility = View.GONE
    }
}

fun convertToArrString(data: ArrayList<String>): Array<String> {
    var array = arrayOfNulls<String>(data.size)
    return data.toArray(array)
}
