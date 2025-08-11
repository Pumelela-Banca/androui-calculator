package com.example.caculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

private const val STATE_RESULT = "result"
private  const val  STATE_FIRST_NUMBER = "firstNumber"
private const val  STATE_SECOND_NUMBER =  "secondNumber"
private const val STATE_OPERATION = "operation"


class MainActivity : ComponentActivity() {

    private lateinit var result: EditText
    private var firstNumber: String? = null
    private var secondNumber: String? = null
    private var prevAnswer: String? = null

    // operations
    private var operation: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get the display value
        result = findViewById(R.id.display)
        if (savedInstanceState != null)
        {
            firstNumber = savedInstanceState.getString(
                STATE_FIRST_NUMBER, "")
            secondNumber = savedInstanceState.getString(STATE_SECOND_NUMBER,
                "")
            prevAnswer = savedInstanceState.getString(STATE_OPERATION,
                "")
        } else
        {
            firstNumber = ""
            secondNumber = ""
            prevAnswer = ""
        }


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
            //val operationsList = listOf<String>("+", "-", "*", "/", "%")

            if (b.text == "0" && firstNumber == "")
            {
                Toast.makeText(
                    this,"Number has two leading zeros", Toast.LENGTH_LONG).show()

            }  else if (result.text.contains(Regex("[+\\-*/%]")))
            {
                secondNumber += b.text.toString()
                val  secondDisplay = "$firstNumber $operation $secondNumber"
                result.setText(
                    secondDisplay
                )
            }
            else
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
        buttonDot.setOnClickListener {
            if (!firstNumber!!.contains(".") && secondNumber == "" )
            {
                firstNumber += "."
                result.setText(
                    firstNumber
                )
            } else if (!secondNumber!!.contains(".") && firstNumber != ""
                && operation != "")
            {
                secondNumber += "."
                val  secondDisplay = "$firstNumber $operation $secondNumber"
                result.setText(
                    secondDisplay
                )
            }
        }

        // Handle equal operation
        buttonEqual.setOnClickListener {
            if (firstNumber != "" && secondNumber != "" && operation != "")
                performOperation(firstNumber!!, secondNumber!!, operation!!)
        }

        // Handle operations
        val opListiner = View.OnClickListener { v->
            val op = (v as Button).text.toString()

            val operationsList = listOf<String>("+", "-", "*", "/", "%")
            if (firstNumber == "" && prevAnswer != "")
            {
                Toast.makeText(this,
                    "Start number then $op", Toast.LENGTH_LONG).show()
            } else if (secondNumber != "" && prevAnswer == "")
            {
                // Equates answer and populates text with answer (- ,+ ,% ,* )
                performOperation(firstNumber!!, secondNumber!!, operation!!)
                firstNumber = prevAnswer
                operation = op
                val secondDisplay = "$firstNumber $op"
                result.setText(
                    secondDisplay
                )
                prevAnswer = ""
            } else if (operationsList.contains(op) && secondNumber == "")
            {
                val secondDisplay = "$firstNumber $op"
                operation = op
                result.setText(
                    secondDisplay
                )
            } else
            {
                val secondDisplay = "$firstNumber $op"
                operation = op
                result.setText(
                    secondDisplay
                )
            }
        }

        buttonPlus.setOnClickListener(opListiner)
        buttonMinus.setOnClickListener(opListiner)
        buttonMultiply.setOnClickListener(opListiner)
        buttonDivide.setOnClickListener(opListiner)
        buttonPercent.setOnClickListener(opListiner)

        // Clear screen
        buttonClearAll.setOnClickListener {
            secondNumber = ""
            firstNumber = ""
            operation = ""
            prevAnswer = ""
            result.setText("0")
        }

        // delete last item
        buttonClear.setOnClickListener {
            // TODO: delete last item on screen
        }
    }



    private fun performOperation (value1: String?, value2: String?, operand: String? )
    {
        Log.d("Tag", "Inside operation function")
        if (value2 == "0" && operand == "/")
        {
            Toast.makeText(this, "Zero division Error", Toast.LENGTH_LONG).show()
        }
        else if (value1!!.toIntOrNull() == null || value2!!.toIntOrNull() == null)
        {
            val num1 = value1.toFloat()
            val num2 = value2!!.toFloat()
            val answers = when (operand) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> num1 / num2
                else -> num1
            }
            val newNum = answers.toString()
            result.setText(newNum)
            secondNumber = ""
            firstNumber = ""
            prevAnswer = newNum

        } else if (value1.toIntOrNull() != null && value2.toIntOrNull() != null) {
            val num1 = value1.toInt()
            val num2 = value2.toInt()
            val answers = when (operand) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> num1 / num2
                else -> num1
            }
            val newNum = answers.toString()
            result.setText(newNum)
            secondNumber = ""
            firstNumber = ""
            operation = ""
            prevAnswer = newNum
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (firstNumber != null)
        {
            outState.putString(STATE_FIRST_NUMBER, firstNumber)
        }
        if (operation != null)
            outState.putString(STATE_OPERATION, operation)
        if (secondNumber != null)
            outState.putString(STATE_SECOND_NUMBER, secondNumber)
        if (prevAnswer != null)
            outState.putString(STATE_RESULT, prevAnswer)
    }
}