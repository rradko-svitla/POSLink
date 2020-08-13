package com.ascend5050.paxposlink.sale

import android.content.Context
import com.ascend5050.paxposlink.BaseFragment
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.R
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_sale.*

class SaleFragment : BaseFragment<SalePresenter, IBaseView>(), IBaseView {

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_sale
    }

    override fun initPresenter(): SalePresenter {
        return SalePresenter(this)
    }

    override fun setupViews() {
        RxTextView.textChanges(saleAmountEditText)
            .map { if (it.isEmpty()) 0.0 else it.toString().toDouble() }
            .subscribe(presenter.amount)

//        saleButton.setOnClickListener { presenter.doRequest(requireContext()).subscribe() }
        saleButton.setOnClickListener {
            initPresenter()
            Observable.fromCallable {requireContext()}
                .subscribe(presenter.request)
        }
    }
}