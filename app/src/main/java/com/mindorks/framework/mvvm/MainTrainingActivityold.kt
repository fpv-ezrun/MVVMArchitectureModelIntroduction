package com.mindorks.framework.mvvm


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

public class MainTrainingActivityold : AppCompatActivity(){

    lateinit var viewModel: TrainingViewModelold
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trainingtestold)

        updateText()

        viewModel = ViewModelProvider(this).get(TrainingViewModelold::class.java)

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

