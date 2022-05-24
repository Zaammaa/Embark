package com.example.embark

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout

import android.view.LayoutInflater
import android.view.View
import android.widget.EditText


class Adjuster(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    var value: Int
    private var min: Int
    private var max: Int

    var decreaseButton: Button? = null
    var increaseButton: Button? = null
    var valueField: EditText? = null

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Adjuster,
            0, 0).apply {

            try {
                value = getInteger(R.styleable.Adjuster_default_value, 15)
                min = getInteger(R.styleable.Adjuster_min_value, 0)
                max = getInteger(R.styleable.Adjuster_max_value, 99)
            } finally {
                recycle()
            }
        }

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.fragment_adjuster, this, true)

        decreaseButton = getChildAt(0) as Button?
        valueField = getChildAt(1) as EditText?
        increaseButton = getChildAt(2) as Button?

        updateValue(value)

        decreaseButton?.setOnClickListener {
            this.decreaseValue(it)
        }
        increaseButton?.setOnClickListener {
            this.increaseValue(it)
        }
    }

    fun increaseValue(button: View){
        var newValue = value+1
        if ( newValue <= max ){
            value = newValue
            updateValue(value)
        }
    }

    fun decreaseValue(button: View){
        var newValue = value-1
        if ( newValue >= min ){
            value = newValue
            updateValue(value)
        }
    }

    fun setAdjusterValue(v: Int){
        value = v
        updateValue(value)
    }

    fun updateValue(v: Int){
        valueField?.setText(v.toString())
        invalidate()
        requestLayout()
    }

    fun getAdjusterValue(): Int {
        return value
    }
}