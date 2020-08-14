package com.ascend5050.paxposlink.util

import android.content.Context
import android.text.Html
import android.widget.Toast
import com.ascend5050.paxposlink.R
import com.ascend5050.paxposlink.model.NameValueStringUnEditableEntity
import com.ascend5050.paxposlink.model.RenderEntity
import com.ascend5050.paxposlink.model.SingleButtonEntity
import com.pax.poslink.PaymentResponse
import com.pax.poslink.peripheries.POSLinkPrinter
import com.pax.poslink.peripheries.ProcessResult
import org.dom4j.DocumentException
import org.dom4j.Element
import org.dom4j.io.SAXReader
import java.io.ByteArrayInputStream

object UIUtil {
    fun paddingLine(left: String, right: String): String {
        return "<tr><td align=\"left\">$left</td><td align=\"right\">$right</td></tr>"
    }

    fun paddingLine(text: String): String {
        return "<tr><td colspan=\"2\" align=\"center\">$text</td></tr>"
    }

    /**
     * Parse XML with dom4j
     * @param data xml data
     * @param node node
     * @return value
     */
    fun findXMl(data: String, node: String): String {
        val extData = "<root>$data</root>"
        val input: ByteArrayInputStream
        input = ByteArrayInputStream(extData.toByteArray())
        val saxReader = SAXReader()
        try {
            val document = saxReader.read(input)
            val eleRoot = document.rootElement
            val iterator: Iterator<*> = eleRoot.elementIterator()
            while (iterator.hasNext()) {
                val ele = iterator.next() as Element
                if (node == ele.name) {
                    return ele.text
                }
            }
        } catch (e: DocumentException) {
            e.printStackTrace()
        }
        return ""
    }

    fun fillPaymentDetails(context: Context, response: PaymentResponse): MutableList<RenderEntity> {
        val responseRenderEntityList = mutableListOf<RenderEntity>()
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_resultCode),
                response.ResultCode
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_resultTxt),
                response.ResultTxt
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_authCode),
                response.AuthCode
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_approvedAmt),
                response.ApprovedAmount
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_avsResponse),
                response.AvsResponse
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_bogusAccountNum),
                response.BogusAccountNum
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_cardType),
                response.CardType
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_cvResponse),
                response.CvResponse
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_hostCode),
                response.HostCode
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_hostResponse),
                response.HostResponse
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_message),
                response.Message
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_refNum),
                response.RefNum
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_remainingBalance),
                response.RemainingBalance
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_extraBalance),
                response.ExtraBalance
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_requestedAmt),
                response.RequestedAmount
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_timestamp),
                response.Timestamp
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_sigfilename),
                response.SigFileName
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_sigdata),
                response.SignData
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_vasCode),
                response.VASResponseInfo.VASCode.toString()
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_vasData),
                formatVASData(response.VASResponseInfo.VASData)
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_NDEFData),
                response.VASResponseInfo.NDEFData
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.tor_info_response_record_type),
                response.TORResponseInfo.RecordType
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.tor_info_response_reversal_time_stamp),
                response.TORResponseInfo.ReversalTimeStamp
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.tor_info_response_host_response_code),
                response.TORResponseInfo.HostResponseCode
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.tor_info_response_host_response_message),
                response.TORResponseInfo.HostResponseMessage
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.tor_info_response_host_reference_number),
                response.TORResponseInfo.HostReferenceNumber
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.tor_info_response_gateway_transaction_id),
                response.TORResponseInfo.GatewayTransactionID
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.tor_info_response_orig_amount),
                response.TORResponseInfo.OrigAmount
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.tor_info_response_masked_pan),
                response.TORResponseInfo.MaskedPAN
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.tor_info_response_batch_no),
                response.TORResponseInfo.BatchNo
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.tor_info_response_reversal_auth_code),
                response.TORResponseInfo.ReversalAuthCode
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.tor_info_response_orig_trans_type),
                response.TORResponseInfo.OrigTransType
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.tor_info_response_orig_trans_date_time),
                response.TORResponseInfo.OrigTransDateTime
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.tor_info_response_orig_trans_auth_code),
                response.TORResponseInfo.OrigTransAuthCode
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.payment_response_tic),
                response.TranIntgClass
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_aci),
                response.AddlRspData.ACI
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_trans_id),
                response.AddlRspData.TransID
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_card_level_result),
                response.AddlRspData.CardLevelResult
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_source_reason_code),
                response.AddlRspData.SourceReasonCode
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_bank_net_data),
                response.AddlRspData.BankNetData
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_pos_entry_mode_chg),
                response.AddlRspData.POSEntryModeChg
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_tran_edit_errcode),
                response.AddlRspData.TranEditErrCode
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_disc_proc_code),
                response.AddlRspData.DiscProcCode
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_disc_pos_entry),
                response.AddlRspData.DiscPOSEntry
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_disc_resp_code),
                response.AddlRspData.DiscRespCode
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_pos_data),
                response.AddlRspData.POSData
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_disc_trans_qualifier),
                response.AddlRspData.DiscTransQualifier
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_disc_nrid),
                response.AddlRspData.DiscNRID
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_trnmsn_date_time),
                response.AddlRspData.TrnmsnDateTime
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_orig_stan),
                response.AddlRspData.OrigSTAN
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_cvv_error_code),
                response.AddlRspData.CVVErrorCode
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_xcode_resp),
                response.AddlRspData.XCodeResp
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_ath_ntwk_id),
                response.AddlRspData.AthNtwkID
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_term_entry_capablt),
                response.AddlRspData.TermEntryCapablt
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_pos_entry_mode),
                response.AddlRspData.POSEntryMode
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_serv_code),
                response.AddlRspData.ServCode
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_spend_qind),
                response.AddlRspData.SpendQInd
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_wlt_id),
                response.AddlRspData.WltID
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_local_date_time),
                response.AddlRspData.LocalDateTime
            )
        )
        responseRenderEntityList.add(
            NameValueStringUnEditableEntity(
                context.resources.getString(R.string.response_addlrspdata_emv_tags),
                response.AddlRspData.EMVTags
            )
        )
//        responseRenderEntityList.add(
//            NameValueStringUnEditableEntity(
//                context.resources.getString(R.string.payment_response_extData),
//                generateReceipt(response.ExtData)
//            )
//        )
        responseRenderEntityList.add(
            SingleButtonEntity(
                "PRINT RECEIPT",
                SingleButtonEntity.ClickCallback { _, _ ->
                    if (!response.ResultCode.contains("000000") && !response.ResultCode.contains("000100")) {
                        Toast.makeText(context, "No Receipt!", Toast.LENGTH_LONG).show()
                    } else if (response.ResultCode.contains("000100")) {
                        Toast.makeText(
                            context,
                            "Don't Print Receipt For Decline",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        val receipt: String = generateReceipt(response)!!
                        POSLinkPrinter.getInstance(context).print(
                            Html.fromHtml(receipt).toString(),
                            object : POSLinkPrinter.PrintListener {
                                override fun onSuccess() {
                                    TODO("Not yet implemented")
                                }

                                override fun onError(p0: ProcessResult?) {
                                    TODO("Not yet implemented")
                                }
                            })
                    }
                })
        )
        return responseRenderEntityList
    }

    private fun formatVASData(vasData: Array<String?>?): String? {
        if (vasData == null) {
            return ""
        }
        val iMax = vasData.size - 1
        if (iMax == -1) return ""
        val b = StringBuilder()
        var i = 0
        while (true) {
            b.append(vasData[i])
            if (i == iMax) return b.toString()
            b.append("[1e]")
            i++
        }
    }

    private fun generateReceipt(response: PaymentResponse): String? {
        val receiptWidth = "100%"
        var content =
            "<html><body><table width=\"$receiptWidth\" align=\"center\" border=\"0\"><tbody>"

        //time stamp
        var temp = response.Timestamp
        var left: String
        var right: String
        if (temp.isNotEmpty()) {
            left = temp.substring(4, 6) + "/" + temp.substring(6, 8) + "/" + temp.substring(0, 4)
            right =
                temp.substring(8, 10) + ":" + temp.substring(10, 12) + ":" + temp.substring(12, 14)
            content += paddingLine(left, right)
            content += paddingLine("&nbsp;")
        }

        // edcType + transType
//        left = "$mLastRequestTender $mLastRequestTrans:"
//        content += UIUtil.paddingLine(left, "")
//        content += UIUtil.paddingLine("&nbsp;")

        //transaction number;
        left = "Transaction #:"
        right = response.RefNum
        content += paddingLine(left, right)

        //card Type:
        if (response.CardType.isNotEmpty()) {
            left = "Card Type:"
            right = response.CardType
            content += paddingLine(left, right)
        }

        //account type
        left = "Account:"
        right = response.BogusAccountNum
        content += paddingLine(left, right)

        //entry mode
        //left = "Entry:";
        // right =
        left = "Entry"
        temp = UIUtil.findXMl(response!!.ExtData, "PLEntryMode")
        if (temp.contains("0")) {
            right = "Manual"
        } else if (temp.contains("1")) {
            right = "Swipe"
        } else if (temp.contains("2")) {
            right = "Contactless"
        } else if (temp.contains("3")) {
            right = "Scanner"
        } else if (temp.contains("4")) {
            right = "Chip"
        } else if (temp.contains("5")) {
            right = "Chip Fall Back Swipe"
        }
        if (temp.isNotEmpty()) content += paddingLine(left, right)
        if (response!!.ResultCode.contains("000000")) {
            //amount
            temp = response!!.ApprovedAmount
            if (temp.isNotEmpty()) {
                left = "Amount:"
                val len = temp.length
                right = if (len == 2) {
                    "\$0.$temp"
                } else if (len == 1) {
                    "\$0.0$temp"
                } else {
                    "$" + temp.substring(0, len - 2) + "." + temp.substring(len - 2)
                }
                content += paddingLine(left, right)
            }

            //order number
            right = UIUtil.findXMl(response!!.ExtData, "MOTOECommerceOrderNum")
            if (right.isNotEmpty()) {
                left = "Order Number"
                content += paddingLine(left, right)
            }
            content += paddingLine("&nbsp;")

            //ref
            right = response!!.HostCode
            if (right.isNotEmpty()) {
                left = "Ref. Number:"
                content += paddingLine(left, right)
            }

            //VALCODE
            right = UIUtil.findXMl(response!!.ExtData, "ValCode")
            if (right.isNotEmpty()) {
                left = "ValCode:"
                content += paddingLine(left, right)
            }

            //auth code
            right = response!!.AuthCode
            if (right.isNotEmpty()) {
                left = "Auth Code:"
                content += paddingLine(left, right)
            }

            //response
            right = response!!.Message
            if (right.isNotEmpty()) {
                left = "Response:"
                content += paddingLine(left, right)
            }

            //TC
            right = UIUtil.findXMl(response!!.ExtData, "TC")
            if (right.isNotEmpty()) {
                left = "TC:"
                content += paddingLine(left, right)
            }

            //TVR
            right = UIUtil.findXMl(response!!.ExtData, "TVR")
            if (right.isNotEmpty()) {
                left = "TVR:"
                content += paddingLine(left, right)
            }

            //AID
            right = UIUtil.findXMl(response!!.ExtData, "AID")
            if (right.isNotEmpty()) {
                left = "AID:"
                content += paddingLine(left, right)
            }

            //TSI
            right = UIUtil.findXMl(response!!.ExtData, "TSI")
            if (right.isNotEmpty()) {
                left = "TSI:"
                content += paddingLine(left, right)
            }

            //TSI
            right = UIUtil.findXMl(response!!.ExtData, "ATC")
            if (right.isNotEmpty()) {
                left = "ATC:"
                content += paddingLine(left, right)
            }

            //APPLIB
            right = UIUtil.findXMl(response!!.ExtData, "APPLAB")
            if (right.isNotEmpty()) {
                left = "APPLAB:"
                content += paddingLine(left, right)
            }

            //APPPN
            right = UIUtil.findXMl(response!!.ExtData, "APPPN")
            if (right.isNotEmpty()) {
                left = "APPPN:"
                content += paddingLine(left, right)
            }
            content += paddingLine("&nbsp;")
            content += paddingLine("I AGREE TO PAY ABOVE TOTAL")
            content += paddingLine("AMOUNT ACCORDING TO CARD ISSUER")
            content += paddingLine("AGREEMENT (MERCHANT AGREEMENT")
            content += paddingLine("IF CREDIT VOUCHER)")
            content += paddingLine("&nbsp;")
            content += paddingLine("X..............................", "")
            content += paddingLine("SIGNATURE")
        } else if (response!!.ResultCode != "000000") {
            content += paddingLine("///////////////////////////////")
            right = response!!.Message
            if (right.isNotEmpty()) {
                left = "Response:"
                content += paddingLine(left, right)
            }
            content += paddingLine("///////////////////////////////")
        }
        content += "</tbody></table></body></html>"
        return content
    }
}