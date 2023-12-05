package pl.ozodbek.shimmerpractice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.ozodbek.mastededittextpractice.util.viewBinding
import pl.ozodbek.shimmerpractice.R
import pl.ozodbek.shimmerpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}