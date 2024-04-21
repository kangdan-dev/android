package com.example.myapplication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import java.time.Duration

class MainActivity : AppCompatActivity() {
    var initTime = 0L
    var pauseTime = 0L
    var datas = mutableListOf<String>()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.startButton.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            binding.chronometer.start()

            binding.startButton.isEnabled = false
            binding.lapButton.isEnabled = true
            binding.stopButton.isEnabled = true
            binding.resetutton.isEnabled = true
        }

        binding.lapButton.setOnClickListener {

            Log.i("kangdan", binding.chronometer.text.toString())
            datas.add(datas.size , binding.chronometer.text.toString())
            Log.i("kangdan", datas.toString())

            binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            binding.recyclerView.adapter = MyAdapter(datas)

        }



        binding.stopButton.setOnClickListener {
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            binding.startButton.isEnabled = true
            binding.lapButton.isEnabled =false
            binding.stopButton.isEnabled = false
            binding.resetutton.isEnabled = true

        }

        binding.resetutton.setOnClickListener {
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            binding.startButton.isEnabled = true
            binding.lapButton.isEnabled =false
            binding.stopButton.isEnabled = false
            binding.resetutton.isEnabled = false

            datas.clear()
            (binding.recyclerView.adapter as MyAdapter).notifyDataSetChanged()
        }


        binding.moveBtn.setOnClickListener {
//            var intent = Intent(this, DrawerActivity::class.java )
//            startActivity(intent)

            startActivity(Intent(this, DrawerActivity::class.java))

        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode === KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - initTime > 3000) {
                Toast.makeText(this, "종료하려면 한번 더 누르세요.", Toast.LENGTH_LONG).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }

        return super.onKeyDown(keyCode, event)
    }
}