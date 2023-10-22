package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {
    lateinit var activityBinding : ActivityMainBinding

    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        activityBinding.calculateButton.setOnClickListener{ calculateTip()}
    }

    fun calculateTip() {
        val stringInput = activityBinding.costOfService.text.toString()
        val cost = stringInput.toDoubleOrNull()
        val selectedOption = activityBinding.radioGroup.checkedRadioButtonId
        val roundUp = activityBinding.switchRoundUp.isChecked
        val tipPercentage = when (selectedOption) {
            R.id.radioButtonAmazing -> 0.20
            R.id.radioButtonGood -> 0.15
            R.id.radioButtonOkay -> 0.10
            else -> 0.0
        }
        var tip = 0.0
        if (cost != null) {
            tip = tipPercentage * cost
            if (roundUp) tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        activityBinding.resultTextView.text = getString(R.string.text_result, formattedTip)
    }
}