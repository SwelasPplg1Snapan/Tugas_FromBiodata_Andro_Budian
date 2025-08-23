package com.example.biodataku

import android.app.DatePickerDialog
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
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val spAgama = findViewById<Spinner>(R.id.spAgama)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        // Spinner agama
        val agamaList = arrayOf("Islam", "Kristen", "Hindu", "Buddha", "Konghucu")
        spAgama.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, agamaList)


        val calendar = Calendar.getInstance()
        etTanggal.setOnClickListener {
            val dp = DatePickerDialog(
                this,
                { _, year, month, day ->
                    etTanggal.setText(getString(R.string.day_month_year, day, month + 1, year))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            dp.show()
        }


        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val alamat = etAlamat.text.toString().trim()
            val tanggal = etTanggal.text.toString().trim()
            val gender = when (rgGender.checkedRadioButtonId) {
                R.id.rbLaki -> "Laki-laki"
                R.id.rbPerempuan -> "Perempuan"
                else -> ""
            }
            val agama = spAgama.selectedItem.toString()


            if (nama.isEmpty() || alamat.isEmpty() || tanggal.isEmpty() || gender.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
            } else {
                tvHasil.text = getString(
                    R.string.nama_alamat_tanggal_gender_agama,
                    nama,
                    alamat,
                    tanggal,
                    gender,
                    agama
                ).trimIndent()
            }
        }
    }
}