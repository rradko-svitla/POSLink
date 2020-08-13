package com.ascend5050.paxposlink.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ascend5050.paxposlink.config.ConfigFragment
import com.ascend5050.paxposlink.sale.SaleFragment

class MainPagerAdapter(fm: FragmentManager?) :
    FragmentPagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabs: List<Pair<String, Fragment>> = listOf<Pair<String, Fragment>>(
        Pair("Init", ConfigFragment()),
        Pair("Sale", SaleFragment())
    )

    override fun getItem(position: Int): Fragment {
        return tabs[position].second
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position].first
    }

    override fun getCount(): Int {
        return tabs.count()
    }
}