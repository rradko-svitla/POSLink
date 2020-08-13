package com.ascend5050.paxposlink.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ascend5050.paxposlink.adjust.AdjustFragment
import com.ascend5050.paxposlink.batch.CloseBatchFragment
import com.ascend5050.paxposlink.force.ForceFragment
import com.ascend5050.paxposlink.test.TestFragment
import com.ascend5050.paxposlink.refund.RefundFragment
import com.ascend5050.paxposlink.sale.SaleFragment
import com.ascend5050.paxposlink.voidt.VoidFragment

class MainPagerAdapter(fm: FragmentManager?) :
    FragmentPagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabs: List<Pair<String, Fragment>> = listOf<Pair<String, Fragment>>(
        Pair("Test", TestFragment()),
        Pair("Sale", SaleFragment()),
        Pair("Void", VoidFragment()),
        Pair("Adjust", AdjustFragment()),
        Pair("Refund", RefundFragment()),
        Pair("Force", ForceFragment()),
        Pair("Close Batch", CloseBatchFragment())
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