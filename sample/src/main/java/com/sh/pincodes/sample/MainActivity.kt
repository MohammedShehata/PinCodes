package com.sh.pincodes.sample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sh.pincodes.PinCodes
import com.sh.pincodes.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PinCodes.OnPinsCompleteListener {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        PinCodes.newInstance(
            this,
            binding.editPin1,
            binding.editPin2,
            binding.editPin3,
            binding.editPin4,
            binding.editPin5,
            binding.editPin6
        )

        PinCodes.newInstance(
            this,
            binding.editPin21,
            binding.editPin22,
            binding.editPin23,
            binding.editPin24,
        )
    }

    override fun onPinsCompleted() {
        Toast.makeText(this, "pins group completed", Toast.LENGTH_SHORT).show()
    }
}