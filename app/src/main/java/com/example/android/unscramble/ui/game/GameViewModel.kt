package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    init {
        Log.d("GameFragment", "GameViewModel Created!")
        getNextWord()
    }

    private var _score = 0
    val score:Int
    get() = _score
    private var currentWordCount = 0
    private var wordsList:MutableList<String> = mutableListOf()
    private lateinit var currentWord:String
    private lateinit var _currentScrambledWord:String
    val currentScrambledWord: String
        get() = _currentScrambledWord

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment","GameViewModel Destroyed!")
    }

    private fun getNextWord(){
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        while (tempWord.toString().equals(currentWord,false)){
            tempWord.shuffle()
        }
        if (wordsList.contains(currentWord)){
            getNextWord()
        }else{
            _currentScrambledWord = String(tempWord)
            ++currentWordCount
            wordsList.add(currentWord)
        }
    }
    fun nextWord():Boolean{

        return if (currentWordCount< MAX_NO_OF_WORDS){
            getNextWord()
            true
        }else false

    }

    fun isUserWordCorrect(playerWord:String):Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    private fun increaseScore(){
        _score+= SCORE_INCREASE

    }

}