package com.example.raihanform

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        val tvNama = findViewById<TextView>(R.id.tvNama)
        val tvTanggal = findViewById<TextView>(R.id.tvTanggal)
        val tvUmur = findViewById<TextView>(R.id.tvUmur)
        val tvAlamat = findViewById<TextView>(R.id.tvAlamat)
        val tvGender = findViewById<TextView>(R.id.tvGender)
        val tvAgama = findViewById<TextView>(R.id.tvAgama)
        val btnClear = findViewById<Button>(R.id.btnClear)

        val nama = intent.getStringExtra("NAMA")
        val alamat = intent.getStringExtra("ALAMAT")
        val tanggal = intent.getStringExtra("TANGGAL")
        val umur = intent.getStringExtra("UMUR")
        val gender = intent.getStringExtra("GENDER")
        val agama = intent.getStringExtra("AGAMA")

        tvHasil.text = "Hasil Biodata"
        tvNama.text = "Nama : $nama"
        tvTanggal.text = "Tanggal Lahir : $tanggal"
        tvUmur.text = "Umur : $umur"
        tvAlamat.text = "Alamat : $alamat"
        tvGender.text = "Jenis Kelamin : $gender"
        tvAgama.text = "Agama : $agama"

        btnClear.setOnClickListener {
            Toast.makeText(this, "✅ Data berhasil dihapus", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
