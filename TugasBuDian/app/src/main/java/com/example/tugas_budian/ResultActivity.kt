package com.example.tugas_budian

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvResult = findViewById(R.id.tvResult)

        val nama = intent.getStringExtra("NAMA")
        val alamat = intent.getStringExtra("ALAMAT")
        val tgl = intent.getStringExtra("TGL")
        val gender = intent.getStringExtra("GENDER")
        val agama = intent.getStringExtra("AGAMA")

        tvResult.text = """
            Nama: $nama
            Alamat: $alamat
            Tanggal Lahir: $tgl
            Gender: $gender
            Agama: $agama
        """.trimIndent()
    }
}
