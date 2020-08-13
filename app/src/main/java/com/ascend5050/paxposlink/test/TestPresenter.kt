package com.ascend5050.paxposlink.test

import android.content.Context
import com.ascend5050.paxposlink.BasePresenter
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.services.CCService
import com.ascend5050.paxposlink.services.CCStatus
import io.reactivex.Observable

class TestPresenter(view: IBaseView) : BasePresenter<IBaseView>(view)  {
    public override fun doRequest(context: Context): Observable<CCStatus> = CCService.testConnection(
        context
    )
}