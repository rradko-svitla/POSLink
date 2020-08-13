package com.ascend5050.paxposlink.batch

import android.content.Context
import com.ascend5050.paxposlink.BasePresenter
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.services.CCService
import com.ascend5050.paxposlink.services.CCStatus
import io.reactivex.Observable

class CloseBatchPresenter(view: IBaseView) : BasePresenter<IBaseView>(view)  {
    override fun doRequest(context: Context): Observable<CCStatus> = CCService.closeBatch(context)
}