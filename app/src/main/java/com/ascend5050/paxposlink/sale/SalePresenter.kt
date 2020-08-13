package com.ascend5050.paxposlink.sale

import android.content.Context
import com.ascend5050.paxposlink.BasePresenter
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.services.CCService
import com.ascend5050.paxposlink.services.CCStatus
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class SalePresenter(view: IBaseView) : BasePresenter<IBaseView>(view)  {

    val amount = BehaviorSubject.createDefault(0.0)

    public override fun doRequest(context: Context): Observable<CCStatus> {
        val amt = amount.value
        return if (amt != null && amt > 0) {
            CCService.sale(amt, context)
        } else {
            Observable.error(IllegalArgumentException("Missing inputs"))
        }
    }
}