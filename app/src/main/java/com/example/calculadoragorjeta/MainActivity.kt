package com.example.calculadoragorjeta

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.calculadoragorjeta.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val textUser = binding.costOfServiceEditText.text.toString()
        val cost = textUser.toDoubleOrNull()

        if (cost == null) {
            binding.tipResult.text = " "
            return
        }
        //pega o valor da gorjeta baseado em qual botao esta selecionado
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        /**
         * calcula o valor da gorjeta arredondada caso o switch de arredondar gorjeta esteja selecionado
         */
        val tipTotal = calculateTipCostRounded(cost,
            tipPercentage, binding.roundUpSwitch.isChecked)

        val newValue = cost + tipTotal
        //formata os valores para a moeda local
        val formattedTip = NumberFormat.getCurrencyInstance().format(tipTotal)
        val formattedValue = NumberFormat.getCurrencyInstance().format(newValue)

        ("New value of service is:$formattedValue and your tip $formattedTip"
                ).also { binding.tipResult.text = it }
    }

    private fun calculateTipCostRounded(
        value: Double,
        tip: Double, checked:
                        // kotlin.math.ceil  arredonda o valor para o proximo int
        Boolean): Double = if (checked)  kotlin.math.ceil(value * tip)  else  value * tip

    }






