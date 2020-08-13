package com.ascend5050.paxposlink.services

import android.content.Context
import com.pax.poslink.*
import com.pax.poslink.poslink.POSLinkCreator
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object CCService {

    private const val TRAN_TYPE_MANAGE_INIT = "INIT"
    private const val TRAN_TYPE_PAYMENT_SALE = "SALE"
    private const val TRAN_TYPE_PAYMENT_VOID = "VOID"
    private const val TRAN_TYPE_PAYMENT_REFUND = "RETURN"
    private const val TRAN_TYPE_PAYMENT_FORCEAUTH = "FORCEAUTH"
    private const val TRAN_TYPE_PAYMENT_ADJUST = "ADJUST"
    private const val TRAN_TYPE_BATCH_CLOSE = "BATCHCLOSE"
    private const val EDC_TYPE_CREADIT = "CREDIT"
    private const val TENDER_TYPE_PAYMENT = "CREDIT"

    private val defaultCommSetting: CommSetting
        get() {
            val commSetting = CommSetting()
            commSetting.type = CommSetting.AIDL
            commSetting.timeOut = "60000"
            commSetting.serialPort = "COM1"
            commSetting.baudRate = "9600"
            commSetting.destIP = "172.16.20.15"
            commSetting.destPort = "10009"
            commSetting.macAddr = ""
            commSetting.isEnableProxy = true
            return commSetting
        }

    private fun send(
        request: Any,
        context: Context,
        message: String = "Contacting payment device ..."
    ): Observable<CCStatus> {
        return Observable
            .create<CCStatus> {
                try {
                    it.onNext(CCStatus.Progress(message))
                    val link = POSLinkCreator.createPoslink(context)
                    link.SetCommSetting(defaultCommSetting)

                    when (request) {
                        is PaymentRequest -> link.PaymentRequest = request
                        is BatchRequest -> link.BatchRequest = request
                        is ManageRequest -> link.ManageRequest = request
                    }
                    // Send the message
                    val res = link.ProcessTrans()
                    // Get the result
                    val ccResult = CCResult()
                    var resultCode = ""
                    var resultText = ""

                    when (res.Code) {
                        ProcessTransResult.ProcessTransResultCode.OK -> {
                            when (request) {
                                is PaymentRequest -> {
                                    ccResult.response = link.PaymentResponse
                                    resultCode = link.PaymentResponse.ResultCode
                                    resultText = link.PaymentResponse.ResultTxt
                                }

                                is BatchRequest -> {
                                    ccResult.response = link.BatchResponse
                                    resultCode = link.BatchResponse.ResultCode
                                    resultText = link.BatchResponse.ResultTxt
                                }

                                is ManageRequest -> {
                                    ccResult.response = link.ManageResponse
                                    resultCode = link.ManageResponse.ResultCode
                                    resultText = link.ManageResponse.ResultTxt
                                }
                            }
                            ccResult.message = "[$resultCode] $resultText"
                            it.onNext(CCStatus.Completed(ccResult))
                        }

                        ProcessTransResult.ProcessTransResultCode.TimeOut -> {
                            it.onNext(CCStatus.Error("Timeout"))
                        }

                        ProcessTransResult.ProcessTransResultCode.ERROR -> {
                            it.onNext(CCStatus.Error(res.Msg))
                        }
                    }
                } catch (e: Throwable) {
                    it.onNext(CCStatus.Error(e.localizedMessage))
                } finally {
                    it.onComplete()
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * Send manage-init command to PAX as a mean of testing connection status.
     */
    fun testConnection(context: Context): Observable<CCStatus> {
        val manageRequest = ManageRequest()
        manageRequest.TransType = manageRequest.ParseTransType(TRAN_TYPE_MANAGE_INIT)
        return send(manageRequest, context)
    }

    /**
     * Send SALE command to PAX.
     */
    fun sale(amount: Double, context: Context): Observable<CCStatus> {
        val paymentRequest = PaymentRequest()
        paymentRequest.TenderType = paymentRequest.ParseTenderType(TENDER_TYPE_PAYMENT)
        paymentRequest.TransType = paymentRequest.ParseTransType(TRAN_TYPE_PAYMENT_SALE)
        paymentRequest.ECRRefNum = "1"
        paymentRequest.Amount = (amount * 100).toInt().toString()
        return send(paymentRequest, context)
    }

//    /**
//     * Send VOID command to PAX.
//     */
//    fun void(device: CCDevice, origRefNum: String): Observable<CCStatus> {
//        val paymentRequest = PaymentRequest()
//        paymentRequest.TenderType = paymentRequest.ParseTenderType(TENDER_TYPE_PAYMENT)
//        paymentRequest.TransType = paymentRequest.ParseTransType(TRAN_TYPE_PAYMENT_VOID)
//        paymentRequest.ECRRefNum = "1"
//        paymentRequest.OrigRefNum = origRefNum
//        return send(device, paymentRequest)
//    }
//
//    /**
//     * Send ADJUST command to PAX.
//     */
//    fun adjust(device: CCDevice, tipAmount: Double, origRefNum: String): Observable<CCStatus> {
//        val paymentRequest = PaymentRequest()
//        paymentRequest.TenderType = paymentRequest.ParseTenderType(TENDER_TYPE_PAYMENT)
//        paymentRequest.TransType = paymentRequest.ParseTransType(TRAN_TYPE_PAYMENT_ADJUST)
//        paymentRequest.ECRRefNum = "1"
//        paymentRequest.Amount = (tipAmount * 100).toInt().toString()
//        paymentRequest.OrigRefNum = origRefNum
//        return send(device, paymentRequest)
//    }
//
//    /**
//     * Send RETURN command to PAX.
//     */
//    fun refund(device: CCDevice, amount: Double): Observable<CCStatus> {
//        val paymentRequest = PaymentRequest()
//        paymentRequest.TenderType = paymentRequest.ParseTenderType(TENDER_TYPE_PAYMENT)
//        paymentRequest.TransType = paymentRequest.ParseTransType(TRAN_TYPE_PAYMENT_REFUND)
//        paymentRequest.ECRRefNum = "1"
//        paymentRequest.Amount = (amount * 100).toInt().toString()
//        return send(device, paymentRequest)
//    }
//
//    /**
//     * Send FORCEAUTH command to PAX.
//     */
//    fun force(device: CCDevice, amount: Double, authCode: String): Observable<CCStatus> {
//        val paymentRequest = PaymentRequest()
//        paymentRequest.TenderType = paymentRequest.ParseTenderType(TENDER_TYPE_PAYMENT)
//        paymentRequest.TransType = paymentRequest.ParseTransType(TRAN_TYPE_PAYMENT_FORCEAUTH)
//        paymentRequest.ECRRefNum = "1"
//        paymentRequest.Amount = (amount * 100).toInt().toString()
//        paymentRequest.AuthCode = authCode
//        return send(device, paymentRequest)
//    }
//
//    /**
//     * Send CLOSEBATCH command to PAX.
//     */
//    fun closeBatch(device: CCDevice): Observable<CCStatus> {
//        val batchRequest = BatchRequest()
//        batchRequest.TransType = batchRequest.ParseTransType(TRAN_TYPE_BATCH_CLOSE)
//        batchRequest.EDCType = batchRequest.ParseEDCType(EDC_TYPE_CREADIT)
//        return send(device, batchRequest)
//    }
}