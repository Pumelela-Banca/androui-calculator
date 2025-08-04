package com.example.caculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity



class MainActivity : ComponentActivity() {

    private lateinit var result: EditText
    private var firstNumber: String? = null
    private var secondNumber: String? = null

    // operations
    private var operation: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get the display value
        result = findViewById(R.id.display)
        firstNumber = ""

        // Get all the buttons
        val button0: Button = findViewById(R.id.btn_0)
        val button1: Button = findViewById(R.id.btn_1)
        val button2: Button = findViewById(R.id.btn_2)
        val button3: Button = findViewById(R.id.btn_3)
        val button4: Button = findViewById(R.id.btn_4)
        val button5: Button = findViewById(R.id.btn_5)
        val button6: Button = findViewById(R.id.btn_6)
        val button7: Button = findViewById(R.id.btn_7)
        val button8: Button = findViewById(R.id.btn_8)
        val button9: Button = findViewById(R.id.btn_9)

        // operations
        val buttonClear: Button = findViewById(R.id.btn_clear)
        val buttonPlusMinus: Button = findViewById(R.id.btn_plus_minus)
        val buttonPlus: Button = findViewById(R.id.btn_plus)
        val buttonMinus: Button = findViewById(R.id.btn_minus)
        val buttonMultiply: Button = findViewById(R.id.btn_multiply)
        val buttonPercent: Button = findViewById(R.id.btn_percent)
        val buttonDivide: Button = findViewById(R.id.btn_divide)
        val buttonClearAll: Button = findViewById(R.id.btn_clear_all)
        val buttonEqual: Button = findViewById(R.id.btn_equal)
        val buttonDot: Button = findViewById(R.id.btn_dot)

        val listener = View.OnClickListener { v ->
            val b = v as Button

            if (b.text == "0" && firstNumber == null)
            {
                Toast.makeText(
                    this,"Number has two leading zeros", Toast.LENGTH_LONG).show()

            } else if (firstNumber!!.contains(".") && b.text.toString() == ".")
            {
                Toast.makeText(
                    this,"Too many dots", Toast.LENGTH_LONG).show()
            } else
            {
                firstNumber += b.text.toString()
                result.setText(
                    firstNumber
                )
            }
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

        // Handle operations
        val opListiner = View.OnClickListener { v->
            val op = (v as Button).text.toString()

            val operationsList = listOf<String>("+", "-", "*", "/", "%")
            if (firstNumber == null)
            {
                Toast.makeText(this,
                    "Start number then $op", Toast.LENGTH_LONG).show()
            } else if (secondNumber != null)
            {
                // Equates answer and populates text with answer (- ,+ ,% ,* )
                TODO("This will go straight to answer and ")

            } else if (operationsList.contains(op))
            {

            } else
            {
                val secondDisplay = "$firstNumber $op"
                result.setText(
                    secondDisplay
                )
            }
        }

        buttonPlus.setOnClickListener(opListiner)
        buttonMinus.setOnClickListener(opListiner)
        buttonMultiply.setOnClickListener(opListiner)
        buttonDivide.setOnClickListener(opListiner)




    }
}