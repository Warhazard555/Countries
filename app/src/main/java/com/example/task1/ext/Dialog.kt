package com.example.task1.ext

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.StringRes
import com.example.task1.COUNTRY_FIND_NAME
import com.example.task1.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder


val DIALOG_WIDTH_DELTA_7: Float = 0.7F

fun Activity.showAlertDialog() {
    val alertDialog = MaterialAlertDialogBuilder(this)
        .setTitle("Alert")
        .setMessage("Connection error")
        .setPositiveButton("OK") { dialog, which -> dialog.dismiss() }
    alertDialog.show()
}

private fun createDialog(activity: Activity): Dialog {
    val dialog = Dialog(activity)

    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.window?.let {
        it.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                //                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    return dialog
}

private fun Activity.initBaseOneButtonContent(
    title: String?,
): Pair<Dialog, View> {
    val dialog = createDialog(this)
    dialog.setCanceledOnTouchOutside(true)
    val contentView = LayoutInflater.from(this)
        .inflate(R.layout.custom_dialog, null)

    val tvTitle: TextView = contentView.findViewById(R.id.tvTitle)
    title?.let {
        tvTitle.text = it
        tvTitle.visibility = View.VISIBLE
    }
    return Pair(dialog, contentView)
}

private fun setContentView(dialog: Dialog, contentView: View) {
    dialog.setContentView(contentView)
    val window = dialog.window
    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val resources = dialog.context
        .resources
    val params = contentView.layoutParams as FrameLayout.LayoutParams
    params.width = ((resources.displayMetrics.widthPixels * DIALOG_WIDTH_DELTA_7).toInt())
    contentView.layoutParams = params
}

fun Activity.showDialogWithOneButton(
    title: String?,
    @StringRes leftButtonTextId: Int,
    leftClickListener: View.OnClickListener?
): Dialog {
    val (dialog, contentView) = initBaseOneButtonContent(title)
    val textEdit: EditText = contentView.findViewById(R.id.etFind)
    val btnLeft: Button = contentView.findViewById(R.id.btnFind)
    btnLeft.setText(leftButtonTextId)
    btnLeft.setOnClickListener {
        val editText: String = textEdit.text.toString()
        COUNTRY_FIND_NAME = editText
        dialog.dismiss()
        leftClickListener?.onClick(it)
    }

    setContentView(dialog, contentView)
    if (!this.isFinishing) {
        dialog.show()
    }

    return dialog
}