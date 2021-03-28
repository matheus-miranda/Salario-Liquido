package com.matheusmiranda.igti.salarioliquido

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.NumberFormatException

class ActivityMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etSalary: EditText = findViewById(R.id.et_salary)
        val etDependents: EditText = findViewById(R.id.et_dependents)
        val etOtherDiscounts: EditText = findViewById(R.id.et_other_discounts)
        val btnCalculate: Button = findViewById(R.id.btn_calculate)

        // Default values
        etDependents.setText("0")
        etOtherDiscounts.setText("0")

        // Passing extras to Activity Result
        btnCalculate.setOnClickListener {
            try {
                Intent(this, ActivityResult::class.java).also {
                    it.putExtra("EXTRA_SALARY", etSalary.text.toString().toFloat())
                    it.putExtra("EXTRA_DEPENDENT", etDependents.text.toString().toInt())
                    it.putExtra("EXTRA_DISCOUNTS", etOtherDiscounts.text.toString().toFloat())
                    startActivity(it)
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(this, getString(R.string.main_insert_salary), Toast.LENGTH_SHORT).show()
            } finally {
                if (etDependents.text.toString().isEmpty())
                    etDependents.setText("0")
                if (etOtherDiscounts.text.toString().isEmpty())
                    etOtherDiscounts.setText("0")
            }

        }
    }
}