/*
 * Copyright (c) 2021 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cioinfotech.cychat.core.platform

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.CallSuper
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.MvRx
import com.airbnb.mvrx.MvRxView
import com.airbnb.mvrx.MvRxViewId
import com.cioinfotech.cychat.core.di.DaggerScreenComponent
import com.cioinfotech.cychat.core.di.ScreenComponent
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.util.concurrent.TimeUnit

abstract class VectorBaseDialogFragment<VB : ViewBinding> : DialogFragment(), MvRxView {

    private val mvrxViewIdProperty = MvRxViewId()
    final override val mvrxViewId: String by mvrxViewIdProperty
    private lateinit var screenComponent: ScreenComponent

    /* ==========================================================================================
     * View
     * ========================================================================================== */

    private var _binding: VB? = null

    // This property is only valid between onCreateView and onDestroyView.
    protected val views: VB get() = _binding!!

    abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    /* ==========================================================================================
     * View model
     * ========================================================================================== */

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val activityViewModelProvider
        get() = ViewModelProvider(requireActivity(), viewModelFactory)

    protected val fragmentViewModelProvider
        get() = ViewModelProvider(this, viewModelFactory)

    val vectorBaseActivity: VectorBaseActivity<*> by lazy {
        activity as VectorBaseActivity<*>
    }

    open val showExpanded = false

    interface ResultListener {
        fun onBottomSheetResult(resultCode: Int, data: Any?)

        companion object {
            const val RESULT_OK = 1
            const val RESULT_CANCEL = 0
        }
    }

    var resultListener: ResultListener? = null
    var bottomSheetResult: Int = ResultListener.RESULT_CANCEL
    var bottomSheetResultData: Any? = null

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        resultListener?.onBottomSheetResult(bottomSheetResult, bottomSheetResultData)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = getBinding(inflater, container)
        return views.root
    }

    @CallSuper
    override fun onDestroyView() {
        uiDisposables.clear()
        _binding = null
        super.onDestroyView()
    }

    @CallSuper
    override fun onDestroy() {
        uiDisposables.dispose()
        super.onDestroy()
    }

    override fun onAttach(context: Context) {
        screenComponent = DaggerScreenComponent.factory().create(vectorBaseActivity.getVectorComponent(), vectorBaseActivity)
        viewModelFactory = screenComponent.viewModelFactory()
        super.onAttach(context)
        injectWith(screenComponent)
    }

    protected open fun injectWith(injector: ScreenComponent) = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        mvrxViewIdProperty.restoreFrom(savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume Dialog ${javaClass.simpleName}")
    }

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return super.onCreateDialog(savedInstanceState).apply {
//            val dialog = this as? BottomSheetDialog
//            bottomSheetBehavior = dialog?.behavior
//            bottomSheetBehavior?.setPeekHeight(DimensionConverter(resources).dpToPx(400), false)
//            if (showExpanded) {
//                bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
//            }
//        }
//    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mvrxViewIdProperty.saveTo(outState)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        postInvalidate()
    }

//    @CallSuper
//    override fun invalidate() {
//        if (showExpanded) {
//            // Force the bottom sheet to be expanded
//            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
//        }
//    }

    protected fun setArguments(args: Parcelable? = null) {
        arguments = args?.let { Bundle().apply { putParcelable(MvRx.KEY_ARG, it) } }
    }

    /* ==========================================================================================
     * Disposable
     * ========================================================================================== */

    private val uiDisposables = CompositeDisposable()

    protected fun Disposable.disposeOnDestroyView(): Disposable {
        uiDisposables.add(this)
        return this
    }

    /* ==========================================================================================
     * Views
     * ========================================================================================== */

    protected fun View.debouncedClicks(onClicked: () -> Unit) {
        clicks()
                .throttleFirst(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { onClicked() }
                .disposeOnDestroyView()
    }

    /* ==========================================================================================
     * ViewEvents
     * ========================================================================================== */

    protected fun <T : VectorViewEvents> VectorViewModel<*, *, T>.observeViewEvents(observer: (T) -> Unit) {
        viewEvents
                .observe()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    observer(it)
                }
                .disposeOnDestroyView()
    }
}
