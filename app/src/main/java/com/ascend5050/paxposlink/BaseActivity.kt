package com.ascend5050.paxposlink

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

abstract class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())
        initView()
    }

    abstract fun getLayoutResourceId(): Int

    abstract fun initView()
}