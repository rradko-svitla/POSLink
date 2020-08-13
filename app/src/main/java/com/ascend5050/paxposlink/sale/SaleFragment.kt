package com.ascend5050.paxposlink.sale

import android.annotation.SuppressLint
import com.ascend5050.paxposlink.BaseFragment
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.R
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_sale.*
import kotlinx.android.synthetic.main.fragment_void.*

class SaleFragment : BaseFragment<SalePresenter, IBaseView>(), IBaseView {

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_sale
    }

    override fun initPresenter(): SalePresenter {
        return SalePresenter(this)
    }

    @SuppressLint("CheckResult")
    override fun setupViews() {
        RxTextView.textChanges(saleAmountEditText)
            .map { if (it.isEmpty()) 0.0 else it.toString().toDouble() }
            .subscribe(presenter.amount)

        BehaviorSubject.create<String>().map { presenter.amount }
            .subscribe { voidButton.isEnabled = it.value!! != 0.0 }
            .addTo(disposable)

        RxView.clicks(saleButton)
            .subscribe {
                presenter.request.onNext(requireContext())
            }
    }
}