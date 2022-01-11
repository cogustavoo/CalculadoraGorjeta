package com.example.calculadoragorjeta

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        var tipPercentage = 0.20
        val textUser = binding.costOfServiceEditText.text.toString()
        val cost = textUser.toDoubleOrNull()

        if (cost == null) {
            binding.tipResult.text = " "
            return
        }

        binding.tipOptions.setOnCheckedChangeListener { _ , check ->
            when (check) {
                R.id.option_twenty_percent -> tipPercentage = 0.20
                R.id.option_eighteen_percent -> tipPercentage = 0.18
                R.id.option_fifteen_percent -> tipPercentage = 0.15
            }
        }

        val tipTotal = calculateTipCostRounded(cost,
            tipPercentage, binding.roundUpSwitch.isChecked)
        val newValue = calculateNewCost(cost, tipTotal)
        val formattedTip = NumberFormat.getCurrencyInstance().format(tipTotal)
        val formattedValue = NumberFormat.getCurrencyInstance().format(newValue)

        binding.tipResult.setTextColor(Color.GREEN)
        ("New value of service is:$formattedValue and your tip $formattedTip"
                ).also { binding.tipResult.text = it }
    }

    private fun calculateTipCostRounded(
        value: Double,
        tip: Double, checked:
        Boolean): Double = if (checked)  kotlin.math.ceil(value * tip)  else  value * tip

    private fun calculateNewCost(value: Double, tip: Double): Double = value + tip
}




