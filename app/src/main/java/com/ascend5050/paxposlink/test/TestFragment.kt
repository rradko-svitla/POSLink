package com.ascend5050.paxposlink.test

import android.annotation.SuppressLint
import com.ascend5050.paxposlink.BaseFragment
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.R
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment : BaseFragment<TestPresenter, IBaseView>(), IBaseView {

    override fun initPresenter(): TestPresenter {
        return TestPresenter(this)
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_test
    }

    @SuppressLint("CheckResult")
    override fun setupViews() {
        RxView.clicks(testConnectionButton)
            .subscribe {
                presenter.request.onNext(requireContext())
            }
    }

}