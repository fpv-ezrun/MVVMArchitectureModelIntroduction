package com.mindorks.framework.mvvm


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

public class MainActivity : AppCompatActivity(){

    lateinit var viewModel: TestViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trainingtest)

        updateText()

        viewModel = ViewModelProvider(this).get(TestViewModel::class.java)

        viewModel.currentMul2.observe(this, Observer {
            findViewById<TextView>(R.id.multiple2).text = it.toString()
        })
        viewModel.currentMul3.observe(this, Observer {
            findViewById<TextView>(R.id.multiple3).text = it.toString()
        })
        viewModel.currentMul5.observe(this, Observer {
            findViewById<TextView>(R.id.multiple5).text = it.toString()
        })
        viewModel.currentMul7.observe(this, Observer {
            findViewById<TextView>(R.id.multiple7).text = it.toString()
        })






    }

    private fun updateText(){

        findViewById<Button>(R.id.buttonChercher).setOnClickListener {
            val temp =findViewById<EditText>(R.id.Number_input).getText().toString()
            if (!"".equals(temp)){
                viewModel.numberSaisie=Integer.parseInt(temp);
            }
            if(viewModel.numberSaisie % 2 == 0){
                viewModel.currentMul2.value = "MULTIPLE DE 2: OUI"}else {
                viewModel.currentMul2.value ="MULTIPLE DE 2: NON"
            }
            if(viewModel.numberSaisie % 3 == 0){
                viewModel.currentMul3.value = "MULTIPLE DE 3: OUI"}else {
                viewModel.currentMul3.value ="MULTIPLE DE 3: NON"
            }
            if(viewModel.numberSaisie % 5 == 0){
                viewModel.currentMul5.value = "MULTIPLE DE 5: OUI"}else {
                viewModel.currentMul5.value ="MULTIPLE DE 5: NON"
            }
            if(viewModel.numberSaisie % 7 == 0){
                viewModel.currentMul7.value = "MULTIPLE DE 7: OUI"}else {
                viewModel.currentMul7.value ="MULTIPLE DE 7: NON"
            }
        }

    }
}

