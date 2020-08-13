package com.ascend5050.paxposlink.adjust

import android.annotation.SuppressLint
import com.ascend5050.paxposlink.BaseFragment
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.R
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_adjust.*

class AdjustFragment : BaseFragment<AdjustPresenter, IBaseView>(), IBaseView {

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_adjust
    }

    override fun initPresenter(): AdjustPresenter {
        return AdjustPresenter(this)
    }

    @SuppressLint("CheckResult")
    override fun setupViews() {
        RxTextView.textChanges(tipAmountEditText)
                .map { if (it.isEmpty()) 0.0 else it.toString().toDouble() }
                .subscribe(presenter.amount)

        RxTextView.textChanges(tipRefNumEditText)
                .map { it.toString() }
                .subscribe(presenter.origRefNum)

        Observables
                .combineLatest(presenter.amount, presenter.origRefNum ) {amount, refNum ->
                    amount > 0 && refNum.isNotBlank()
                }
                .subscribe { adjustButton.isEnabled = it }
                .addTo(disposable)

        RxView.clicks(adjustButton)
            .subscribe {
                presenter.request.onNext(requireContext())
            }
    }
}