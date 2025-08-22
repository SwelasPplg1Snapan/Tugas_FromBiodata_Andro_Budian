package com.example.pbmnarendra

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etAlamat: EditText
    private lateinit var tvTanggal: TextView
    private lateinit var spAgama: Spinner
    private lateinit var btnPilihTanggal: Button
    private lateinit var btnSimpan: Button

    private var selectedDate: Calendar? = null
    private val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id","ID"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNama = findViewById(R.id.etNama)
        etAlamat = findViewById(R.id.etAlamat)
        tvTanggal = findViewById(R.id.tvTanggal)
        spAgama = findViewById(R.id.spAgama)
        btnPilihTanggal = findViewById(R.id.btnPilihTanggal)
        btnSimpan = findViewById(R.id.btnSimpan)

        // isi spinner agama
        val listAgama = listOf("Islam", "Kristen", "Katolik", "Hindu", "Budha", "Konghucu")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listAgama)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spAgama.adapter = adapter

        btnPilihTanggal.setOnClickListener {
            showDatePicker()
        }

        btnSimpan.setOnClickListener {
            simpanData()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            { _, y, m, d ->
                selectedDate = Calendar.getInstance()
                selectedDate?.set(y, m, d)
                tvTanggal.text = dateFormat.format(selectedDate!!.time)
            }, year, month, day)

        datePickerDialog.show()
    }

    private fun simpanData() {
        val nama = etNama.text.toString().trim()
        val alamat = etAlamat.text.toString().trim()
        val tanggal = tvTanggal.text.toString()
        val agama = spAgama.selectedItem.toString()

        if (nama.isEmpty() || alamat.isEmpty() || tanggal.isEmpty()) {
            Toast.makeText(this, "Semua data wajib diisi!", Toast.LENGTH_SHORT).show()
            return
        }

        val umur = if (selectedDate != null) hitungUmur(selectedDate!!) else 0

        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("nama", nama)
        intent.putExtra("alamat", alamat)
        intent.putExtra("tanggal", tanggal)
        intent.putExtra("umur", umur)
        intent.putExtra("agama", agama)
        startActivity(intent)
    }

    private fun hitungUmur(tanggal: Calendar): Int {
        val today = Calendar.getInstance()
        var umur = today.get(Calendar.YEAR) - tanggal.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) < tanggal.get(Calendar.DAY_OF_YEAR)) {
            umur--
        }
        return umur
    }
}