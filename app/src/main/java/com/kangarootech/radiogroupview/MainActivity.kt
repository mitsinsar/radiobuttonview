package com.kangarootech.radiogroupview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioButton1.setOnCheckedChangeListener { buttonView, isChecked ->
            showSelectedButtonId()
        }

        radioButton2.setOnCheckedChangeListener { buttonView, isChecked ->
            showSelectedButtonId()
        }

        radioButton3.setOnCheckedChangeListener { buttonView, isChecked ->
            showSelectedButtonId()
        }
    }

    private fun showSelectedButtonId() {
        Log.e("Test", "${radioGroupView.checkedButton?.id}")
    }
}
