package com.example.nybooks.presentation.base

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    protected fun setupToolbar(toolbar: Toolbar, titleIdRes: Int, showBackButton: Boolean = false) {
        toolbar.title = getString(titleIdRes)
        setSupportActionBar(toolbar)
        if (showBackButton) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
}