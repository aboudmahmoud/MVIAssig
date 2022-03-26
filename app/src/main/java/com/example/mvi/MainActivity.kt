package com.example.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mvi.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel:AddNumberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel= ViewModelProvider(this).get(AddNumberViewModel::class.java)
        Rednder()
        binding.addNumber.setOnClickListener{
            //send
            lifecycleScope.launch {
                viewModel.IntentChanenl.send(mainIntent.AddNumber)
            }
        }

    }
    //render
    private  fun Rednder(){
lifecycleScope.launchWhenStarted {
    viewModel.state.collect{
        when(it)
        {
            is MainViewState.Idle -> binding.NumberTextView.setText("Idel")
            is MainViewState.Number ->binding.NumberTextView.setText(it.Num.toString())
            is MainViewState.Error->binding.NumberTextView.setText(it.error)

        }
    }
}
    }
}