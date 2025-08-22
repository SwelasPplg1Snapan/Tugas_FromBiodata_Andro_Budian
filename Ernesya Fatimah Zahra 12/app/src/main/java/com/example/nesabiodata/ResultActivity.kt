package com.example.nesabiodata

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("BIODATA_PREF", Context.MODE_PRIVATE)

        // Hubungkan variabel dengan komponen di layout
        val tvHasilNama: TextView = findViewById(R.id.tvHasilNama)
        val tvHasilTanggalLahir: TextView = findViewById(R.id.tvHasilTanggalLahir)
        val tvHasilAlamat: TextView = findViewById(R.id.tvHasilAlamat)
        val tvHasilJenisKelamin: TextView = findViewById(R.id.tvHasilJenisKelamin)
        val tvHasilAgama: TextView = findViewById(R.id.tvHasilAgama)

        // Ambil data dari SharedPreferences
        val nama = sharedPreferences.getString("NAMA", "Tidak ada data")
        val tanggalLahir = sharedPreferences.getString("TANGGAL_LAHIR", "Tidak ada data")
        val alamat = sharedPreferences.getString("ALAMAT", "Tidak ada data")
        val jenisKelamin = sharedPreferences.getString("JENIS_KELAMIN", "Tidak ada data")
        val agama = sharedPreferences.getString("AGAMA", "Tidak ada data")

        // Tampilkan data ke TextView
        tvHasilNama.text = "Nama: $nama"
        tvHasilTanggalLahir.text = "Tanggal Lahir: $tanggalLahir"
        tvHasilAlamat.text = "Alamat: $alamat"
        tvHasilJenisKelamin.text = "Jenis Kelamin: $jenisKelamin"
        tvHasilAgama.text = "Agama: $agama"
    }
}