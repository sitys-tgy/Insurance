package com.edu.tarc.insurance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edu.tarc.insurance.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.spinnerAge.setOnItemClickListener(this)
        binding.buttonCalculate.setOnClickListener {
            var age = binding.spinnerAge.selectedItemPosition
            var gender = binding.radioGroupGender.checkedRadioButtonId
            var smoker = binding.checkBoxSmoker.isChecked

            var premium = 0
            var genderPremium = 0
            var smokerPremium = 0

            if(age == 0){
                premium = 60
            }
            when(age){
                1 -> {
                    premium = 70
                }
                2-> {
                    premium = 90
                }
                3-> {
                    premium = 120
                }
                in 4..5 ->{
                    premium = 150
                }
            }

            if(gender == R.id.radioButtonMale){
                when(age){
                    0-> {
                        genderPremium = 0
                    }
                    1 -> {
                        genderPremium = 50
                    }
                    2-> {
                        genderPremium = 100
                    }
                    3-> {
                        genderPremium = 150
                    }
                    in 4..5 ->{
                        genderPremium = 200
                    }
                }
            }

            if(smoker){
                when(age){
                    0-> {
                        smokerPremium = 0
                    }
                    1 -> {
                        smokerPremium = 100
                    }
                    2-> {
                        smokerPremium = 150
                    }
                    3-> {
                        smokerPremium = 200
                    }
                    4 ->{
                        smokerPremium = 250
                    }
                    5->{
                        smokerPremium = 300
                    }
                }
            }
            //Display the final premium value
            val calTotal = premium + genderPremium + smokerPremium
            val myLocale = Locale.getDefault()
            val myCurrency = Currency.getInstance(myLocale)
            binding.textViewPremium.text = String.format("%s %d",myCurrency.currencyCode.toString(), calTotal)
        }

        binding.buttonReset.setOnClickListener {
            binding.spinnerAge.setSelection(0)
            binding.radioGroupGender.clearCheck()
            binding.checkBoxSmoker.isChecked = false
            binding.textViewPremium.text = ""
        }
    }
}