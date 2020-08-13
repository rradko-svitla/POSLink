package com.ascend5050.paxposlink.voidt

import android.annotation.SuppressLint
import com.ascend5050.paxposlink.BaseFragment
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.R
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_void.*

class VoidFragment : BaseFragment<VoidPresenter, IBaseView>(), IBaseView {

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_void
    }

    override fun initPresenter(): VoidPresenter {
        return VoidPresenter(this)
    }

    @SuppressLint("CheckResult")
    override fun setupViews() {
        RxTextView.textChanges(voidRefNumEditText)
            .map { it.toString() }
            .subscribe(presenter.origRefNum)

        BehaviorSubject.create<String>().map { presenter.origRefNum }
            .subscribe { voidButton.isEnabled = it.value!!.isNotEmpty() }
            .addTo(disposable)

        RxView.clicks(voidButton)
            .subscribe {
                presenter.request.onNext(requireContext())
            }
    }

}