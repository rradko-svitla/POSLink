package com.ascend5050.paxposlink.force

import android.content.Context
import com.ascend5050.paxposlink.BasePresenter
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.services.CCService
import com.ascend5050.paxposlink.services.CCStatus
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class ForcePresenter(view: IBaseView) : BasePresenter<IBaseView>(view)  {

    val amount = BehaviorSubject.createDefault(0.0)
    val authCode = BehaviorSubject.createDefault("")

    override fun doRequest(context: Context): Observable<CCStatus> {
        val amt = amount.value
        var code = authCode.value
        return if (amt != null && amt > 0 && code != null && code.isNotBlank()) {
            CCService.force(context, amt, code)
        } else {
            Observable.error(IllegalArgumentException("Missing inputs"))
        }
    }
}