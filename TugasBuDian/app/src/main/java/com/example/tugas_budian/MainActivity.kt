package com.example.tugas_budian

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etAlamat: EditText
    private lateinit var etTanggalLahir: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var spAgama: Spinner
    private lateinit var btnSubmit: Button

    private val agamaList = arrayOf("Islam", "Kristen", "Hindu", "Budha", "Lainnya")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi
        etNama = findViewById(R.id.etNama)
        etAlamat = findViewById(R.id.etAlamat)
        etTanggalLahir = findViewById(R.id.etTanggalLahir)
        rgGender = findViewById(R.id.rgGender)
        spAgama = findViewById(R.id.spAgama)
        btnSubmit = findViewById(R.id.btnSubmit)

        // Isi Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, agamaList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spAgama.adapter = adapter

        // DatePicker
        etTanggalLahir.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, y, m, d ->
                etTanggalLahir.setText("$d/${m+1}/$y")
            }, year, month, day)
            datePicker.show()
        }

        // Tombol Submit
        btnSubmit.setOnClickListener {
            val nama = etNama.text.toString()
            val alamat = etAlamat.text.toString()
            val tglLahir = etTanggalLahir.text.toString()
            val genderId = rgGender.checkedRadioButtonId
            val gender = if (genderId != -1) findViewById<RadioButton>(genderId).text else ""
            val agama = spAgama.selectedItem.toString()

            // Validasi
            if (nama.isEmpty() || alamat.isEmpty() || tglLahir.isEmpty() || gender.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
            } else {
                // Kirim ke activity hasil
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra("NAMA", nama)
                    putExtra("ALAMAT", alamat)
                    putExtra("TGL", tglLahir)
                    putExtra("GENDER", gender)
                    putExtra("AGAMA", agama)
                }
                startActivity(intent)
            }
        }
    }
}
