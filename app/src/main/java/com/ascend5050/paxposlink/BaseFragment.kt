package com.ascend5050.paxposlink

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ascend5050.paxposlink.services.CCResult
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<P, V> : Fragment(),
    IBaseView where V : IBaseView, P : BasePresenter<V> {

    lateinit var presenter: P
    private var progressDialog: AlertDialog? = null

    var disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = initPresenter()
        return inflater.inflate(getLayoutResourceId(), container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    abstract fun getLayoutResourceId(): Int

    abstract fun initPresenter(): P

    abstract fun setupViews()

    override fun getViewContext(): Context {
        return requireContext()
    }

    override fun showLoading(message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setView(R.layout.dialog_sending)
        progressDialog = builder.create()
        progressDialog?.show()
    }

    override fun hideLoading() {
        progressDialog?.dismiss()
    }

    override fun onSuccess(result: CCResult) {
        hideLoading()
        showMessage(result.message)
    }

    override fun onProgress(message: String) {
        progressDialog?.let {
            it.findViewById<TextView>(R.id.loadingTextView)?.text = message
        }
    }

    override fun onError(message: String?) {
        hideLoading()
        showMessage("[ERROR] $message")
    }

    private fun showMessage(message: String?) {
        AlertDialog.Builder(context)
                .setMessage(message)
                .create()
                .show()
    }

}