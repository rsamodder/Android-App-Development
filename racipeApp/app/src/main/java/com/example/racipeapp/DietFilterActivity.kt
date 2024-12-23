package com.example.racipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast


class DietFilterActivity : AppCompatActivity() {
    var button: Button? = null
    var button2: Button? = null
    var sexo: Boolean? = null
    var TMB: Double? = null
    var idade: Int? = 0
    var peso: Double? = 0.00
    var altura: Int? = 0
    var atividade: Int? = 4
    var natasha: DoubleArray = doubleArrayOf(1.00, 1.11, 1.25, 1.48)


    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_filterctivity)


        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)


        //Find view by id

        var etIdade = findViewById<EditText>(R.id.etIdade)
        var etPeso = findViewById<EditText>(R.id.etPeso)
        var etAltura = findViewById<EditText>(R.id.etAltura)
        var texto2 = findViewById<TextView>(R.id.textView3)

        //inicializar funções

        verificarRadioSexo()
        verificarRadioAtividade()


        button?.setOnClickListener {

            //Tirar o null

            if(etIdade.getText().toString() == ""){

                idade = 0

            } else {

                this.idade = etIdade.getText().toString().toInt()

            }

            if(etPeso.getText().toString() == ""){

                peso = 0.00

            } else {

                this.peso = etPeso.getText().toString().toDouble()

            }

            if(etAltura.getText().toString() == ""){

                altura = 0

            } else {

                this.altura = etAltura.getText().toString().toInt()

            }

            // aki fica algum comentario util





            //tratar de peso

            if(idade == 0){
                idade = 0
                mensagemErro("Please enter age")
            } else if(idade!! >= 100.00 || idade!! <=12.00 ) {
                mensagemErro("Invalid age")
            } else if(peso == 0.00){
                peso = 0.00
                mensagemErro("Please enter the weight")
            } else if(peso!! >= 300.00 || peso!! <=40.00 ) {
                mensagemErro("Po inválidoes")
            } else if(altura == 0){
                altura = 0
                mensagemErro("Please enter the height")
            } else if(altura!! >= 300 || altura!! <=100 ) {
                mensagemErro("Invalid height")
            } else if (atividade!! == 4){

                mensagemErro("Please select the activity factor")

            } else if(this.sexo == true){

                TMB  = 354 -(idade!! * 6.9  ) + natasha[atividade!!] * (9.3 * peso!!) + (726 * (altura!!/100) )
                texto2.setText(TMB.toString())


            } else if(this.sexo == false){

                TMB  = 662 -(idade!! * 9.5  ) + natasha[atividade!!] * (15.9 * peso!!) + (539.6 * (altura!!/100) )
                texto2.setText(TMB.toString())

            } else {

                mensagemErro("\n" +
                        "Please select your gender")

            }

        }

        button2?.setOnClickListener {

            if(texto2.getText().toString() != ""){

                /*val intent = Intent(this, Dieta3Activity::class.java)
                    intent.putExtra("EER", TMB.toString())
                    startActivity(intent)*/

                startActivity(Intent (this@DietFilterActivity, Dieta1Activity::class.java).apply {

                    putExtra("eer", TMB.toString())

                })

            } else {

                mensagemErro("Please enter the details above correctly before proceeding")

            }
        }



    }


    public fun verificarRadioSexo(){
        var rgSexo = findViewById<RadioGroup>(R.id.rgSexo)
        var x = findViewById<TextView>(R.id.textView3)
        rgSexo.setOnCheckedChangeListener { group, checkedId ->
            run {

                if (checkedId == R.id.Mulher) {

                    this.sexo = true
                    //x.setText("teste1")

                } else if (checkedId == R.id.Homem) {

                    this.sexo = false
                    // x.setText("teste2")

                } else {

                    //x.setText("teste3")

                }


            }
        }

    }


    public fun verificarRadioAtividade(){
        var rgAtividade = findViewById<RadioGroup>(R.id.rgAtividade)
        rgAtividade.setOnCheckedChangeListener { group, checkedId ->
            run {

                if(checkedId == R.id.rbSedentario){

                    this.atividade = 0

                } else if (checkedId == R.id.rbLeve){

                    this.atividade = 1

                } else if (checkedId == R.id.rbModerado){

                    this.atividade = 2

                } else if (checkedId == R.id.rbIntenso){

                    this.atividade = 3

                } else {

                    //mensagemErro()

                }


            }}

    }


    public fun calcularTMB(altura: Int, peso: Double, idade: Int, atividade: Int){

        val texto = findViewById<TextView>(R.id.textView3)

        if(this.sexo == true){

            TMB  = 66.5 -(idade * 9.5) + (natasha[atividade] * (15.9 * peso)) + (539.6 * altura )
            texto.setText(TMB.toString())


        } else if(this.sexo == false){

            TMB  = 662 -(idade * 9.5) + (natasha[atividade] * (15.9 * peso)) + (539.6 * altura )
            texto.setText(TMB.toString())

        } else {

            //mensagemErro()

        }

    }




    public fun mensagemErro(mensagem: String ){

        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()

    }


}