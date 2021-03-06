package com.ascend5050.paxposlink.main

import com.ascend5050.paxposlink.BaseActivity
import com.ascend5050.paxposlink.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var pagerAdapter: MainPagerAdapter

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        pagerAdapter = MainPagerAdapter(supportFragmentManager)
        mainPager.adapter = pagerAdapter
        mainTab.setupWithViewPager(mainPager)
    }
}
