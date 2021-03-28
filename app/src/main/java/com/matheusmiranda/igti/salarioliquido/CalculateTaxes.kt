package com.matheusmiranda.igti.salarioliquido

import kotlin.math.absoluteValue

class CalculateTaxes(salary: Float) {
    private val grossSalary: Float = salary
    private var netSalary: Float = 0.0F
    private var discountPercent = 0.0F
    private var inssSalary: Float = 0.0F
    private var irrfSalary: Float = 0.0F
    private var inssDeduction: Float = 0.0F
    private var irrfDeduction: Float = 0.0F

    fun calculateINSS(): String {
        when {
            grossSalary <= 1045 -> {
                inssDeduction = (grossSalary * 0.075).toFloat()
            }
            grossSalary <= 2089.6 -> {
                inssDeduction = ((grossSalary * 0.09) - 15.67).toFloat()
            }
            grossSalary <= 3134.4 -> {
                inssDeduction = ((grossSalary * 0.12) - 78.36).toFloat()
            }
            grossSalary <= 6101.06 -> {
                inssDeduction = ((grossSalary * 0.14) - 141.05).toFloat()
            }
            else -> {
                inssDeduction = 713.10F
            }
        }
        inssSalary = grossSalary - inssDeduction
        return String.format("%.2f", inssDeduction)
    }

    fun calculateIRRF(numDepend: Int): String {
        val baseSalary = inssSalary - (numDepend * 189.59)

        when {
            baseSalary <= 1903.98 -> {
                irrfDeduction = 0F
            }
            baseSalary <= 2826.65 -> {
                irrfDeduction = ((baseSalary * 0.075) - 142.8).toFloat()
            }
            baseSalary <= 3751.05 -> {
                irrfDeduction = ((baseSalary * 0.15) - 354.8).toFloat()
            }
            baseSalary <= 4664.68 -> {
                irrfDeduction = ((baseSalary * 0.225) - 636.13).toFloat()
            }
            else -> {
                irrfDeduction = ((baseSalary * 0.275) - 869.36).toFloat()
            }
        }
        irrfSalary = grossSalary - irrfDeduction
        return String.format("%.2f", irrfDeduction)
    }

    fun netSalary(discounts: Float): String {
        netSalary = grossSalary - inssDeduction - irrfDeduction - discounts
        return String.format("%.2f", netSalary)
    }

    fun calcDiscountPercent(): String {
        discountPercent = ((netSalary * 100) / grossSalary) - 100
        return String.format("%.2f", discountPercent.absoluteValue)
    }

}