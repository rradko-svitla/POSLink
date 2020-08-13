package com.ascend5050.paxposlink.adjust

import android.content.Context
import com.ascend5050.paxposlink.BasePresenter
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.services.CCService
import com.ascend5050.paxposlink.services.CCStatus
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class AdjustPresenter(view: IBaseView) : BasePresenter<IBaseView>(view)  {

    val amount = BehaviorSubject.createDefault(0.0)
    val origRefNum = BehaviorSubject.createDefault("")

    override fun doRequest(context: Context): Observable<CCStatus> {
        val amt = amount.value
        var ref = origRefNum.value
        return if (amt != null && amt > 0 && ref != null && ref.isNotEmpty()) {
            CCService.adjust(context, amt, ref)
        } else {
            Observable.error(IllegalArgumentException("Missing inputs"))
        }
    }
}