package com.ascend5050.paxposlink.refund

import android.content.Context
import com.ascend5050.paxposlink.BasePresenter
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.services.CCService
import com.ascend5050.paxposlink.services.CCStatus
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class RefundPresenter(view: IBaseView) : BasePresenter<IBaseView>(view)  {

    val amount = BehaviorSubject.createDefault<Double>(0.0)

    override fun doRequest(context: Context): Observable<CCStatus> {
        val amt = amount.value
        return if (amt != null && amt > 0) {
            CCService.refund(context, amt)
        } else {
            Observable.error(IllegalArgumentException("Missing inputs"))
        }
    }
}