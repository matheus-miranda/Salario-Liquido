package com.matheusmiranda.igti.salarioliquido

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ActivityResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //Reference all views from XML
        val tvGrossSalary: TextView = findViewById(R.id.tv_gross_salary)
        val tvINSS:TextView = findViewById(R.id.tv_inss)
        val tvIRRF: TextView = findViewById(R.id.tv_irrf)
        val tvDiscounts: TextView = findViewById(R.id.tv_other_discounts)
        val tvNetSalary: TextView = findViewById(R.id.tv_net_salary)
        val tvDiscountPercent: TextView = findViewById(R.id.tv_other_discount_percent)
        val btnBack: Button = findViewById(R.id.btn_back)

        // Receiving Intent Extras from Main Activity
        val salary = intent.getFloatExtra("EXTRA_SALARY", 0.00F)
        val dependents = intent.getIntExtra("EXTRA_DEPENDENT", 0)
        val discounts = intent.getFloatExtra("EXTRA_DISCOUNTS", 0.00F)

        // Instantiate CalculateTaxes with gross salary
        val calculateTaxes = CalculateTaxes(salary)

        // Concatenate strings
        val salStr = "R$${salary}"
        val textInss = "-${calculateTaxes.calculateINSS()}"
        val textIrrf = "-${calculateTaxes.calculateIRRF(dependents)}"
        val textDiscount = "-${discounts}"
        val textNetSalary = "R$${calculateTaxes.netSalary(discounts)}"
        val textDiscPercent = "${calculateTaxes.calcDiscountPercent()}%"

        // Assign all values to TextViews
        tvGrossSalary.text = salStr
        tvINSS.text = textInss
        tvIRRF.text = textIrrf
        tvDiscounts.text = textDiscount
        tvNetSalary.text = textNetSalary
        tvDiscountPercent.text = textDiscPercent

        btnBack.setOnClickListener {
            finish()
        }
    }
}