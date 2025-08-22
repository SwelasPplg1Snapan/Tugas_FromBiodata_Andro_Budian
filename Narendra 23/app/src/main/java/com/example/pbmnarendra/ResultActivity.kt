package com.example.pbmnarendra

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ResultActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RiwayatAdapter

    companion object {
        val listRiwayat = mutableListOf<Biodata>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RiwayatAdapter(listRiwayat,
            onDelete = { biodata ->
                listRiwayat.remove(biodata)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Data dihapus", Toast.LENGTH_SHORT).show()
            },
            onShare = { biodata ->
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT,
                        "Nama: ${biodata.nama}\nAlamat: ${biodata.alamat}\n" +
                                "Tanggal Lahir: ${biodata.tanggal}\nUmur: ${biodata.umur}\nAgama: ${biodata.agama}")
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Bagikan via"))
            }
        )
        recyclerView.adapter = adapter

        // ambil data baru dari MainActivity
        val nama = intent.getStringExtra("nama")
        val alamat = intent.getStringExtra("alamat")
        val tanggal = intent.getStringExtra("tanggal")
        val umur = intent.getIntExtra("umur", 0)
        val agama = intent.getStringExtra("agama")

        if (nama != null && alamat != null && tanggal != null && agama != null) {
            val biodata = Biodata(nama, alamat, tanggal, umur, agama)
            listRiwayat.add(0, biodata) // tambah di paling atas
            adapter.notifyDataSetChanged()
        }
    }
}