package com.mindorks.framework.mvvm


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

public class MainTrainingActivity : AppCompatActivity(){

    lateinit var viewModel: TrainingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trainingtest)

        updateText()

        viewModel = ViewModelProvider(this).get(TrainingViewModel::class.java)

        /*viewModel.currentMul2.observe(this, Observer {
            findViewById<TextView>(R.id.multiple2).text = it.toString()
        })*/








    }

    private fun updateText(){

        findViewById<Button>(R.id.buttonChercher).setOnClickListener {
            val descTraining =findViewById<EditText>(R.id.Description_Training).getText().toString()
            val idTraining =findViewById<EditText>(R.id.IdTraining).getText().toString()



        }

    }
   /* fun showPopupMenu(view: View) {
        PopupMenu(view.context, view).apply {
            menuInflater.inflate(R.menu.popup_men, menu)
            setOnMenuItemClickListener { item ->
                Toast.makeText(view.context, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                true
            }
        }.show()
    }*/
}

