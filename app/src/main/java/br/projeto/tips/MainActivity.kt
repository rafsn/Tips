package br.projeto.tips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.projeto.tips.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val valorInicial = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenTip(valorInicial)
        binding.btnCalcular.setOnClickListener{ calcularTip() }
    }

    private fun calcularTip() {
        val inputNumber = binding.inputValueEditText.text.toString().toDoubleOrNull()
        if (inputNumber == null) {
            screenTip(valorInicial)
            return
        }

        var tip = when (binding.serviceOption.checkedRadioButtonId) {
            R.id.otima_opcao -> 0.18 * inputNumber
            R.id.boa_opcao -> 0.15 * inputNumber
            else -> 0.12 * inputNumber
        }
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        screenTip(tip)
    }


    private fun screenTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tvResultado.text = getString(R.string.quantia, formattedTip)
    }
}