package com.example.reina

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import com.example.reina.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("BiodataPrefs", Context.MODE_PRIVATE)

        setupSpinner()
        setupDatePicker()

        binding.btnSimpan.setOnClickListener {
            if (validateInput()) {
                saveAndShowData()
            }
        }
    }

    private fun setupSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.agama_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerAgama.adapter = adapter
        }
    }

    private fun setupDatePicker() {
        binding.etTanggalLahir.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Bulan dimulai dari 0, jadi tambah 1
                    val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    binding.etTanggalLahir.setText(selectedDate)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }

    private fun validateInput(): Boolean {
        if (binding.etNamaLengkap.text.isNullOrEmpty()) {
            showToast("Nama lengkap tidak boleh kosong!")
            return false
        }
        if (binding.etAlamat.text.isNullOrEmpty()) {
            showToast("Alamat tidak boleh kosong!")
            return false
        }
        if (binding.etTanggalLahir.text.isNullOrEmpty()) {
            showToast("Tanggal lahir tidak boleh kosong!")
            return false
        }
        if (binding.rgJenisKelamin.checkedRadioButtonId == -1) {
            showToast("Pilih jenis kelamin!")
            return false
        }
        return true
    }

    private fun saveAndShowData() {
        val nama = binding.etNamaLengkap.text.toString()
        val alamat = binding.etAlamat.text.toString()
        val tanggalLahir = binding.etTanggalLahir.text.toString()

        val selectedGenderId = binding.rgJenisKelamin.checkedRadioButtonId
        val radioButton = findViewById<RadioButton>(selectedGenderId)
        val jenisKelamin = radioButton.text.toString()

        val agama = binding.spinnerAgama.selectedItem.toString()

        // 1. Simpan ke SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString("NAMA", nama)
        editor.putString("ALAMAT", alamat)
        editor.putString("TANGGAL_LAHIR", tanggalLahir)
        editor.putString("JENIS_KELAMIN", jenisKelamin)
        editor.putString("AGAMA", agama)
        editor.apply() // Simpan secara asynchronous

        showToast("Data berhasil disimpan!")

        // 2. Kirim data dan pindah ke ResultActivity
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("NAMA", nama)
            putExtra("ALAMAT", alamat)
            putExtra("TANGGAL_LAHIR", tanggalLahir)
            putExtra("JENIS_KELAMIN", jenisKelamin)
            putExtra("AGAMA", agama)
        }
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}