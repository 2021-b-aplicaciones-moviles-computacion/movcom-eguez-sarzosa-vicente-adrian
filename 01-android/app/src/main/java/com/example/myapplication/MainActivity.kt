package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            if(result.data != null){
                val data = result.data
                Log.i("intent-epn", "${data?.getStringExtra("nombreModificado")}")
                Log.i("intent-epn", "${data?.getIntExtra("edadModificado", 0)}")
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonCicloVida = findViewById<Button>(R.id.btn_ir_ciclo_vida)
        botonCicloVida
            .setOnClickListener {
                irActividad(ACicloVida::class.java)
            }
        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener {
                irActividad(BListView::class.java)
            }
        val botonIntent = findViewById<Button>(R.id.btn_intent)
        botonIntent
            .setOnClickListener {
                abrirActividadConParametros(CIntentExplicitoParametros::class.java)
            }
    }
    fun abrirActividadConParametros(
        clase: Class<*>,
    ) {
        val intentExplicito = Intent(this, clase)
        // Enviar parametros (solamente variables primitivas)
        intentExplicito.putExtra("nombre", "Adrian")
        intentExplicito.putExtra("apellido", "Eguez")
        intentExplicito.putExtra("edad", 32)
        intentExplicito.putExtra("entrenador",BEntrenador("a","b"))
        resultLauncher.launch(intentExplicito)
//
//        startActivityForResult(intent, CODIGO_RESPUESTA_INTENT_EXPLICITO)

//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            when (it.resultCode) {
//                Activity.RESULT_OK -> {
//                    //Ejecutar codigo OK
//                }
//            }
//        }

    }

    fun irActividad(
        clase: Class<*>,
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}