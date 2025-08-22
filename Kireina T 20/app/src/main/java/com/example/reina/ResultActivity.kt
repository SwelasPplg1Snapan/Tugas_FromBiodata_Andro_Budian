package com.example.reina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reina.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari Intent
        val nama = intent.getStringExtra("NAMA")
        val alamat = intent.getStringExtra("ALAMAT")
        val tanggalLahir = intent.getStringExtra("TANGGAL_LAHIR")
        val jenisKelamin = intent.getStringExtra("JENIS_KELAMIN")
        val agama = intent.getStringExtra("AGAMA")

        // Tampilkan data ke TextView
        binding.tvResultNama.text = "Nama: $nama"
        binding.tvResultAlamat.text = "Alamat: $alamat"
        binding.tvResultTanggalLahir.text = "Tanggal Lahir: $tanggalLahir"
        binding.tvResultJenisKelamin.text = "Jenis Kelamin: $jenisKelamin"
        binding.tvResultAgama.text = "Agama: $agama"

        // Set listener untuk tombol kembali
        binding.btnKembali.setOnClickListener {
            // Selesaikan activity ini dan kembali ke activity sebelumnya (MainActivity)
            finish()
        }
    }
}