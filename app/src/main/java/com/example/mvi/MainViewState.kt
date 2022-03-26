package com.example.mvi

sealed class MainViewState{
    //idle
    object Idle:MainViewState()
    //number
    data class Number(val Num:Int):MainViewState()
    //error
    data class Error(val error:String):MainViewState()
}
