package com.ascend5050.paxposlink.config

import com.ascend5050.paxposlink.BasePresenter
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.services.CCDevice
import com.ascend5050.paxposlink.services.CCService
import com.ascend5050.paxposlink.services.CCStatus
import io.reactivex.Observable

class ConfigPresenter(view: IBaseView) : BasePresenter<IBaseView>(view)  {
    override fun doRequest(device: CCDevice): Observable<CCStatus> = CCService.testConnection(device)
}