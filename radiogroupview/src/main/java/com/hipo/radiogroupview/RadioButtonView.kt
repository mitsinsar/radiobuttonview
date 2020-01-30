package com.hipo.radiogroupview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton

class RadioButtonView : AppCompatRadioButton {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    internal var onCheckedChanged: ((Boolean) -> Unit)? = null

    override fun setChecked(checked: Boolean) {
        if (isChecked != checked) {
            onCheckedChanged?.invoke(checked)
        }
        super.setChecked(checked)
    }
}
