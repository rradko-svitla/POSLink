package com.ascend5050.paxposlink.batch

import android.annotation.SuppressLint
import com.ascend5050.paxposlink.BaseFragment
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.R
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_close_batch.*

class CloseBatchFragment : BaseFragment<CloseBatchPresenter, IBaseView>(), IBaseView {

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_close_batch
    }

    override fun initPresenter(): CloseBatchPresenter {
        return CloseBatchPresenter(this)
    }

    @SuppressLint("CheckResult")
    override fun setupViews() {

        RxView.clicks(closeBatchButton)
            .subscribe {
                presenter.request.onNext(requireContext())
            }
    }
}