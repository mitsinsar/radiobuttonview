package com.hipo.radiogroupview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import java.lang.ref.WeakReference
import kotlin.properties.Delegates

class RadioGroupView : View {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    private var parentView: ViewGroup? by Delegates.observable<ViewGroup?>(null) { _, _, newValue ->
        newValue?.run { parseRadioButtons(newValue) }
    }

    private val radioButtonList: MutableList<WeakReference<RadioButtonView>> = mutableListOf()

    private var checkedButtonId: Int by Delegates.observable(NOT_SELECTED) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            if (oldValue != NOT_SELECTED) {
                setCheckState(oldValue, false)
            }
            setCheckState(newValue, true)
        }
    }

    val checkedButton: RadioButtonView?
        get() = parentView?.findViewById(checkedButtonId)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initParent()
    }

    private fun initParent() {
        if (parent is ViewGroup) {
            parentView = parent as ViewGroup
        } else {
            throw Exception("$LOG_TAG - Parent must be ViewGroup!")
        }
    }

    private fun setCheckState(refId: Int, state: Boolean) {
        parentView?.findViewById<RadioButtonView>(refId)?.isChecked = state
    }

    private fun parseRadioButtons(viewGroup: ViewGroup) {
        viewGroup.forEach { view ->
            when (view) {
                is ViewGroup -> parseRadioButtons(viewGroup)
                is RadioButtonView -> if (view.tag == tag) initRadioButton(view)
            }
        }
    }

    private fun initRadioButton(radioButton: RadioButtonView) {
        radioButtonList.add(WeakReference(radioButton))
        if (radioButton.isChecked) {
            if (checkedButtonId == NOT_SELECTED) checkedButtonId = radioButton.id else radioButton.isChecked = false
        }
        radioButton.onCheckedChanged = { checkedButtonId = radioButton.id }
    }

    companion object {
        private val LOG_TAG = RadioGroupView::class.java.simpleName
        private const val NOT_SELECTED = -1
    }
}
