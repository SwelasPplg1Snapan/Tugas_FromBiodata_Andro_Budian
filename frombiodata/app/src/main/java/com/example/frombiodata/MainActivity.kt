package com.example.frombiodata

import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etAlamat: EditText
    private lateinit var etTanggal: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var spAgama: Spinner
    private lateinit var btnSimpan: Button
    private lateinit var tvHasil: TextView

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNama = findViewById(R.id.etNama)
        etAlamat = findViewById(R.id.etAlamat)
        etTanggal = findViewById(R.id.etTanggal)
        rgGender = findViewById(R.id.rgGender)
        spAgama = findViewById(R.id.spAgama)
        btnSimpan = findViewById(R.id.btnSimpan)
        tvHasil = findViewById(R.id.tvHasil)

        sharedPref = getSharedPreferences("BiodataPref", MODE_PRIVATE)

        // Spinner agama
        val agamaList = arrayOf("Islam", "Kristen", "Katolik", "Hindu", "Budha", "Konghucu")
        spAgama.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, agamaList)

        // DatePicker
        etTanggal.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dp = DatePickerDialog(this, { _, y, m, d ->
                etTanggal.setText("$d/${m + 1}/$y")
            }, year, month, day)
            dp.show()
        }

        // Tombol Simpan
        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString()
            val alamat = etAlamat.text.toString()
            val tanggal = etTanggal.text.toString()
            val selectedGenderId = rgGender.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) findViewById<RadioButton>(selectedGenderId).text.toString() else ""
            val agama = spAgama.selectedItem.toString()

            // Validasi
            if (nama.isEmpty() || alamat.isEmpty() || tanggal.isEmpty() || gender.isEmpty()) {
                Toast.makeText(this, getString(R.string.msg_field_kosong), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simpan ke SharedPreferences
            sharedPref.edit().apply {
                putString("nama", nama)
                putString("alamat", alamat)
                putString("tanggal", tanggal)
                putString("gender", gender)
                putString("agama", agama)
                apply()
            }

            // Tampilkan data di TextView bawah tombol
            val hasil = """
                ${getString(R.string.hasil_nama, nama)}
                ${getString(R.string.hasil_alamat, alamat)}
                ${getString(R.string.hasil_tanggal, tanggal)}
                ${getString(R.string.hasil_gender, gender)}
                ${getString(R.string.hasil_agama, agama)}
            """.trimIndent()

            tvHasil.text = hasil
        }

        // Jika ada data tersimpan, tampilkan otomatis
        val savedNama = sharedPref.getString("nama", null)
        if (savedNama != null) {
            val savedAlamat = sharedPref.getString("alamat", "")
            val savedTanggal = sharedPref.getString("tanggal", "")
            val savedGender = sharedPref.getString("gender", "")
            val savedAgama = sharedPref.getString("agama", "")
            val hasil = """
                ${getString(R.string.hasil_nama, savedNama)}
                ${getString(R.string.hasil_alamat, savedAlamat)}
                ${getString(R.string.hasil_tanggal, savedTanggal)}
                ${getString(R.string.hasil_gender, savedGender)}
                ${getString(R.string.hasil_agama, savedAgama)}
            """.trimIndent()
            tvHasil.text = hasil
        }
    }
}
