package com.example.pbmnarendra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RiwayatAdapter(
    private val list: List<Biodata>,
    private val onDelete: (Biodata) -> Unit,
    private val onShare: (Biodata) -> Unit
) : RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama: TextView = view.findViewById(R.id.tvNama)
        val tvAlamat: TextView = view.findViewById(R.id.tvAlamat)
        val tvTanggal: TextView = view.findViewById(R.id.tvTanggal)
        val tvUmur: TextView = view.findViewById(R.id.tvUmur)
        val tvAgama: TextView = view.findViewById(R.id.tvAgama)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
        val btnShare: ImageButton = view.findViewById(R.id.btnShare)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_biodata, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val biodata = list[position]
        holder.tvNama.text = biodata.nama
        holder.tvAlamat.text = biodata.alamat
        holder.tvTanggal.text = "Tanggal Lahir: ${biodata.tanggal}"
        holder.tvUmur.text = "Umur: ${biodata.umur} tahun"
        holder.tvAgama.text = "Agama: ${biodata.agama}"

        holder.btnDelete.setOnClickListener { onDelete(biodata) }
        holder.btnShare.setOnClickListener { onShare(biodata) }
    }

    override fun getItemCount(): Int = list.size
}