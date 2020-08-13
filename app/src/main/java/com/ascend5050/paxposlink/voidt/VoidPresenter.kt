package com.ascend5050.paxposlink.voidt

import android.content.Context
import com.ascend5050.paxposlink.BasePresenter
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.services.CCService
import com.ascend5050.paxposlink.services.CCStatus
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class VoidPresenter(view: IBaseView) : BasePresenter<IBaseView>(view)  {

    val origRefNum = BehaviorSubject.createDefault("")

    override fun doRequest(context: Context): Observable<CCStatus> {
        val refNum = origRefNum.value
        return if (refNum != null && refNum.isNotBlank()) {
            CCService.void(context, refNum)
        } else {
            Observable.error(IllegalArgumentException("Missing inputs"))
        }
    }
}