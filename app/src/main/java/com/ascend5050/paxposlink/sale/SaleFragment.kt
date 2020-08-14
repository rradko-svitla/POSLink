package com.ascend5050.paxposlink.sale

import android.annotation.SuppressLint
import android.text.TextUtils
import com.ascend5050.paxposlink.BaseFragment
import com.ascend5050.paxposlink.IBaseView
import com.ascend5050.paxposlink.R
import com.ascend5050.paxposlink.model.CommonItemView
import com.ascend5050.paxposlink.model.NameValueEntity
import com.ascend5050.paxposlink.model.RenderEntity
import com.ascend5050.paxposlink.services.CCResult
import com.ascend5050.paxposlink.util.UIUtil
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.pax.poslink.PaymentResponse
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_sale.*
import kotlinx.android.synthetic.main.fragment_void.*

open class SaleFragment : BaseFragment<SalePresenter, IBaseView>(), IBaseView {

    private var response: PaymentResponse? = null
    private var responseRenderEntityList: MutableList<RenderEntity> = mutableListOf()

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_sale
    }

    override fun initPresenter(): SalePresenter {
        return SalePresenter(this)
    }

    override fun onSuccess(result: CCResult) {
        super.onSuccess(result)
        response = result.response as PaymentResponse
        setPaymentResponse(response!!)
    }

    @SuppressLint("CheckResult")
    override fun setupViews() {
        RxTextView.textChanges(saleAmountEditText)
            .map { if (it.isEmpty()) 0.0 else it.toString().toDouble() }
            .subscribe(presenter.amount)

        BehaviorSubject.create<String>().map { presenter.amount }
            .subscribe { voidButton.isEnabled = it.value!! != 0.0 }
            .addTo(disposable)

        RxView.clicks(saleButton)
            .subscribe {
                presenter.request.onNext(requireContext())
            }
    }

    private fun setPaymentResponse(response: PaymentResponse) {
        responseContainer.removeAllViews()
        responseRenderEntityList.clear()
        responseRenderEntityList = UIUtil.fillPaymentDetails(requireContext(), response)
        for (renderEntity in responseRenderEntityList) {
            if (renderEntity is NameValueEntity<*> && TextUtils.isEmpty((renderEntity as NameValueEntity<String?>).value)) {
                continue
            }
            val itemView: CommonItemView<RenderEntity> = renderEntity.createView(responseContainer)
            responseContainer.addView(itemView.view)
            itemView.render(renderEntity)
        }
    }
}