package com.example.classwork3

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivityClickInfo : AppCompatActivity() {

    private lateinit var tvClickInfo : TextView
    private lateinit var tvMotionInfo : TextView
    private var cntClicl : Int = 0
    private var S : String = ""

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_click_info)

        this.tvClickInfo = this.findViewById<TextView>(R.id.tvClickInfo)
        this.tvMotionInfo = this.findViewById<TextView>(R.id.tvMotionInfo)

        val LL:LinearLayout = this.findViewById<LinearLayout>(R.id.a111)

        LL.setOnTouchListener(object : View.OnTouchListener {
            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                val x: Float? = event?.getX()
                val y: Float? = event?.getY()

                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        S += "Click\n"
                    }
                    MotionEvent.ACTION_MOVE -> {
                        S += "Moveing\n"
                    }
                    MotionEvent.ACTION_UP -> {
                        S += "Releasing\n"
                    }
                    else -> return false
                }

                S += "X = ${x}, Y = ${y}"

                tvMotionInfo.text = S

                return false
            }
        })

        LL.setOnClickListener {
            this@MainActivityClickInfo.cntClicl++
            this@MainActivityClickInfo.tvClickInfo.text = "Qty clicks: " + this@MainActivityClickInfo.cntClicl
        }


    }
}