package com.example.vatapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vatapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root // reference layout file
        setContentView(view) // link layout

        binding.btnCalculateVat.setOnClickListener {
            calculateVat()
        } // End setOnClickListener

    } // End onCreate

    private fun calculateVat() {
        val stringVatet = binding.etTotalCost.text.toString()
        val cost = stringVatet.toDouble()

        // Radio group
        val selectedID = binding.rgVatOption.checkedRadioButtonId

        val vatPercentage = when(selectedID){
            R.id.rb_vat_10 -> 0.10
            R.id.rb_vat_15 -> 0.10
            else -> 0.10
        } // End when

        var total = vatPercentage * cost


        val roundVat = binding.switchRoundUp.isChecked

        if (roundVat){
            total = kotlin.math.ceil(total)
        }

        // Total Formatting
        val formatedTotal = NumberFormat.getCurrencyInstance().format(total)

        binding.txtCostTotal.text = getString(R.string.total_amount, formatedTotal)

    }
}