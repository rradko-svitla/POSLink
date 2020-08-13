package com.ascend5050.paxposlink.config

import com.ascend5050.paxposlink.BaseFragment
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_configuration.*


class ConfigFragment : BaseFragment<ConfigPresenter, IBaseView>(), IBaseView {

    override fun initPresenter(): ConfigPresenter {
        return ConfigPresenter(this)
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_configuration
    }

    override fun setupViews() {
        testConnectionButton.setOnClickListener {
            initPresenter()
            val observable = Observable.fromCallable {requireContext()}
            observable.subscribe(presenter.request)
        }
    }

}