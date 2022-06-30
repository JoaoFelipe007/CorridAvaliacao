package com.example.calcularcorrida

import android.opengl.GLES30
import android.opengl.GLES31
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calcularcorrida.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    binding.calculate.setOnClickListener{calculateRun()}
    }
    private fun calculateRun(){
        //A val valueRace = vai pegar o valor inserido no editText e vaiC onverter para string
        val valueRace = binding.raceValue.text.toString()
        val custo = valueRace.toDoubleOrNull() // depois de converter para String ele converte pra Double pq não pode fazer isso direto
        if(custo == null){
           binding.runResult.text=""
            return
        }
        val avaliacaoPorcentagem = when(binding.percentageOptions.checkedRadioButtonId){
            R.id.option_excelent->0.20
            R.id.option_good->0.18
            R.id.option_regular->0.10
            else->{0.05}
        }
        var tip = avaliacaoPorcentagem * custo

        if(binding.roundRun.isChecked){
            tip = kotlin.math.ceil(tip) // faz arredondar o resultado
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip) //Faz o resultado aparecer em dinheiro
        binding.runResult.text = getString(R.string.run_result_text,formattedTip)
    }

}