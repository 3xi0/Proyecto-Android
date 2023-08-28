package com.example.proyectoandroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.proyectoandroid.R
import com.example.proyectoandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.getCompareResult().observe(this) {
            binding.compareResultView.text = getString(if(it) R.string.compare_result_success else R.string.compare_result_failure)
        }

        binding.compareButton.setOnClickListener {
            mainViewModel.setFirstField(binding.firstField.text.toString())
            mainViewModel.setSecondField(binding.secondField.text.toString())
            mainViewModel.compare()
        }
    }
}