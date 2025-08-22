package com.example.nesabiodata

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etTanggalLahir: EditText
    private lateinit var etAlamat: EditText
    private lateinit var rgJenisKelamin: RadioGroup
    private lateinit var spinnerAgama: Spinner
    private lateinit var btnSimpan: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("BIODATA_PREF", Context.MODE_PRIVATE)

        // Hubungkan variabel dengan komponen di layout
        etNama = findViewById(R.id.etNama)
        etTanggalLahir = findViewById(R.id.etTanggalLahir)
        etAlamat = findViewById(R.id.etAlamat)
        rgJenisKelamin = findViewById(R.id.rgJenisKelamin)
        spinnerAgama = findViewById(R.id.spinnerAgama)
        btnSimpan = findViewById(R.id.btnSimpan)

        // Setup Spinner
        setupSpinner()

        // Setup DatePickerDialog
        setupDatePicker()

        // Setup Tombol Simpan
        btnSimpan.setOnClickListener {
            if (validasiInput()) {
                simpanDanTampilkanData()
            }
        }
    }

    private fun setupSpinner() {
        // Buat adapter untuk spinner dari array di strings.xml
        ArrayAdapter.createFromResource(
            this,
            R.array.agama_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerAgama.adapter = adapter
        }
    }

    private fun setupDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(calendar)
        }

        etTanggalLahir.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun updateLabel(calendar: Calendar) {
        val myFormat = "dd-MM-yyyy" // format tanggal
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        etTanggalLahir.setText(sdf.format(calendar.time))
    }

    private fun validasiInput(): Boolean {
        if (etNama.text.isEmpty()) {
            Toast.makeText(this, "Nama lengkap harus diisi!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (etTanggalLahir.text.isEmpty()) {
            Toast.makeText(this, "Tanggal lahir harus diisi!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (etAlamat.text.isEmpty()) {
            Toast.makeText(this, "Alamat harus diisi!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (rgJenisKelamin.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Jenis kelamin harus dipilih!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun simpanDanTampilkanData() {
        // Ambil data dari input
        val nama = etNama.text.toString()
        val tanggalLahir = etTanggalLahir.text.toString()
        val alamat = etAlamat.text.toString()

        val selectedRadioButtonId = rgJenisKelamin.checkedRadioButtonId
        val radioButton = findViewById<RadioButton>(selectedRadioButtonId)
        val jenisKelamin = radioButton.text.toString()

        val agama = spinnerAgama.selectedItem.toString()

        // Simpan data ke SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString("NAMA", nama)
        editor.putString("TANGGAL_LAHIR", tanggalLahir)
        editor.putString("ALAMAT", alamat)
        editor.putString("JENIS_KELAMIN", jenisKelamin)
        editor.putString("AGAMA", agama)
        editor.apply() // .apply() berjalan di background, .commit() berjalan di UI thread

        // Pindah ke ResultActivity dan kirim data
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
    }
}