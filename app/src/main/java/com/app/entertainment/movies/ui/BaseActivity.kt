package com.app.entertainment.movies.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.app.entertainment.movies.databinding.ProgressDialogLayoutBinding

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var progressDialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createProgressDialog()
    }

    private fun createProgressDialog() {
//        progressDialog = Dialog(this, R.style.progress_bar_dialog)
        progressDialog = Dialog(this)
        val dialogViewBinding = ProgressDialogLayoutBinding.inflate(layoutInflater)
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog.setContentView(dialogViewBinding.root)
        progressDialog.setCancelable(false)
        progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        /*Glide.with(this)
            .load(R.drawable.animation_1)
            .into(dialogViewBinding.progressImage)*/

    }

     fun showProgressDialog() {
        if (this::progressDialog.isInitialized)
            progressDialog.show()
    }

     fun hideProgressDialog() {
        if (this::progressDialog.isInitialized && progressDialog.isShowing)
            progressDialog.dismiss()

    }
}