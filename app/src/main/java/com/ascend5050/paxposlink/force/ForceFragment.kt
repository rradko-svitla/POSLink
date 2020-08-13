package com.ascend5050.paxposlink.force

import android.annotation.SuppressLint
import com.ascend5050.paxposlink.BaseFragment
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.R
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_force.*

class ForceFragment : BaseFragment<ForcePresenter, IBaseView>(), IBaseView {
    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_force
    }

    override fun initPresenter(): ForcePresenter {
        return ForcePresenter(this)
    }

    @SuppressLint("CheckResult")
    override fun setupViews() {
        RxTextView.textChanges(forceAmountEditText)
                .map { if (it.isEmpty()) 0.0 else it.toString().toDouble() }
                .subscribe(presenter.amount)

        RxTextView.textChanges(forceAuthCodeEditText)
                .map { it.toString() }
                .subscribe(presenter.authCode)

        Observables
                .combineLatest(presenter.amount, presenter.authCode ) {amount, authCode ->
                    amount > 0 && authCode.isNotBlank()
                }
                .subscribe { forceButton.isEnabled = it }
                .addTo(disposable)

        RxView.clicks(forceButton)
            .subscribe {
                presenter.request.onNext(requireContext())
            }

    }
}