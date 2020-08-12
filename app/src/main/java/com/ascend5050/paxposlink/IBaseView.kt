package com.ascend5050.paxposlink

import com.ascend5050.paxposlink.services.CCResult

/**
 * Common interface for all views to implement the MVP pattern.
 */
interface IBaseView {
    fun showLoading(message: String)
    fun hideLoading()
    fun onSuccess(result: CCResult)
    fun onProgress(message: String)
    fun onError(message: String?)
}