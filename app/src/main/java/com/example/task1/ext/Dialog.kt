package com.example.task1.ext

import android.app.Activity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Activity.showAlertDialog() {
    val alertDialog = MaterialAlertDialogBuilder(this)
        .setTitle("Alert")
        .setMessage("Connection error")
        .setPositiveButton("OK") { dialog, which -> dialog.dismiss() }
    alertDialog.show()
}