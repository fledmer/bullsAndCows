package com.example.bullandcows

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random
import kotlin.random.nextUInt


class MainActivity : AppCompatActivity() {
    lateinit var inputText: TextInputEditText
    lateinit var button: Button
    lateinit var words: Array<String>
    lateinit var word: String
    lateinit var bullsText: TextView
    lateinit var cowsText: TextView
    lateinit var wordLen: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputText = findViewById(R.id.inputText)
        button = findViewById(R.id.runButton)
        words = resources.getStringArray(R.array.word)
        word = words[((Random.nextUInt())% words.size.toUInt()).toInt()]
        bullsText = findViewById(R.id.bullsText)
        cowsText = findViewById(R.id.cowsText)
        wordLen = findViewById(R.id.wordLen)
        wordLen.setText("${word.length} букв")
        button.setOnClickListener{
            @SuppressLint("SetTextI18n")
                if (inputText.text.toString() == word){
                    onWin()
                    return@setOnClickListener
                }
                var bulls = 0
                var cows = 0
                inputText.text.toString().forEachIndexed() { index, c ->
                    if (word.length > index && word[index] == c) {
                        bulls++
                    } else if (word.contains(c)) {
                        cows++
                    }
                }
                bullsText.text = "Быки: $bulls"
                cowsText.text = "Коровы: $cows"
            }
    }

    fun onWin(){
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Поздравляем!")
        alertDialogBuilder.setMessage("Вы победили!")
        alertDialogBuilder.setPositiveButton("OK") { dialog: DialogInterface, which: Int ->
            // Действия, выполняемые при нажатии на кнопку "OK"
            dialog.dismiss() // Закрываем диалоговое окно
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
        word = words[((Random.nextUInt())% words.size.toUInt()).toInt()]
        wordLen.setText("${word.length} букв")
    }
}