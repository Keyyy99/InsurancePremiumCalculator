package com.example.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var myData: DataModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    myData = ViewModelProviders.of(this).get(DataModel::class.java)

        if(myData.total!=null){
            result.text="RM %s".format(myData.total.toString())
        }
        buttonCalculate.setOnClickListener() {
            try {
                val age: String = spinnerAge.getSelectedItem().toString()
                val gender: RadioButton = findViewById(radioGroupGender.checkedRadioButtonId)
                var total = 0
                var extra = 0

                when {
                    age == "Less than 17"-> {
                        total += 60
                    }
                    age == "17 to 25"-> {
                        total += 70
                        if(gender.text == "Male") {
                            extra += 50
                        }
                        if(checkBoxSmoker.isChecked) {
                            extra += 100
                        }
                    }
                    age == "26 to 30"-> {
                        total += 90
                        if(gender.text == "Male") {
                            extra += 100
                        }
                        if(checkBoxSmoker.isChecked) {
                            extra += 150
                        }
                    }age == "31 to 40"-> {
                        total += 120
                        if(gender.text == "Male") {
                            extra += 150
                        }
                        if(checkBoxSmoker.isChecked) {
                            extra += 200
                        }
                    }age == "41 to 55"-> {
                        total += 150
                        if(gender.text == "Male") {
                            extra += 200
                        }
                        if(checkBoxSmoker.isChecked) {
                            extra += 250
                        }
                    }age == "More than 55"-> {
                        total += 150
                        if(gender.text == "Male") {
                            extra += 200
                        }
                        if(checkBoxSmoker.isChecked) {
                            extra += 300
                        }
                    }
                    else -> {
                        result.text = "Not Available"
                    }
                }
                val grand : Int = calculate (total, extra)
                myData.total=grand
                result.text = "RM %d".format(grand)


            } catch (e: Exception) {
                val toast: Toast = Toast.makeText(
                    applicationContext,
                    "Please fill in the gender!",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.CENTER, 0, -20)
                toast.show()
            }
        }

        buttonReset.setOnClickListener() {
            spinnerAge.setSelection(0)
            radioGroupGender.clearCheck()
            checkBoxSmoker.setChecked(false)
            result.text =""
            myData.total=0
        }
    }
    private fun calculate(total:Int,extra:Int):Int{
        return total + extra
    }
}
