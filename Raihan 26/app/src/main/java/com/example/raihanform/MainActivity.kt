package com.example.raihanform

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etAlamat = findViewById<EditText>(R.id.etAlamat)
        val etTanggal = findViewById<EditText>(R.id.etTanggal)
        val etUmur = findViewById<EditText>(R.id.etUmur)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val spinnerAgama = findViewById<Spinner>(R.id.spAgama)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        // Daftar agama
        val agamaList = arrayOf("Islam", "Kristen", "Katolik", "Hindu", "Budha", "Konghucu")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, agamaList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAgama.adapter = adapter

        // DatePicker untuk Tanggal Lahir
        etTanggal.isFocusable = false
        etTanggal.isClickable = true
        etTanggal.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, y, m, d ->
                val tanggal = "$d/${m + 1}/$y"
                etTanggal.setText(tanggal)
            }, year, month, day)

            dpd.show()
        }

        // Klik tombol
        btnSubmit.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val alamat = etAlamat.text.toString().trim()
            val tanggal = etTanggal.text.toString().trim()
            val umur = etUmur.text.toString().trim()
            val selectedGenderId = rgGender.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else ""

            val agama = spinnerAgama.selectedItem.toString()

            if (nama.isEmpty() || alamat.isEmpty() || tanggal.isEmpty() || umur.isEmpty() || gender.isEmpty()) {
                Toast.makeText(this, "⚠ Semua field harus diisi!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("NAMA", nama)
                intent.putExtra("ALAMAT", alamat)
                intent.putExtra("TANGGAL", tanggal)
                intent.putExtra("UMUR", umur)
                intent.putExtra("GENDER", gender)
                intent.putExtra("AGAMA", agama)
                startActivity(intent)
            }
        }
    }
}