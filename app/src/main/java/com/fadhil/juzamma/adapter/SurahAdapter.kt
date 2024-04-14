package com.fadhil.juzamma.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.juzamma.DetailSurahActivity
import com.fadhil.juzamma.R
import com.fadhil.juzamma.api.DataItem

class SurahAdapter(var itemSurah: List<DataItem?>?) : RecyclerView.Adapter<SurahAdapter.MyViewHolder>() {

    fun getData(): List<DataItem?>? {
        return itemSurah
    }

    fun filterList(filteredData: List<DataItem?>) {
        itemSurah = filteredData
        notifyDataSetChanged()
    }

    class MyViewHolder(v:View) : RecyclerView.ViewHolder(v){
        val number = v.findViewById<TextView>(R.id.tv_number)
        val title = v.findViewById<TextView>(R.id.tv_surah)
        val ayah  = v.findViewById<TextView>(R.id.tv_ayah)
        val name_arabic = v.findViewById<TextView>(R.id.tv_name_arabic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahAdapter.MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_row_surah,parent,false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: SurahAdapter.MyViewHolder, position: Int) {
        holder.number.text = itemSurah?.get(position)?.number.toString()
        holder.title.text = itemSurah?.get(position)?.englishName
        holder.ayah.text = itemSurah?.get(position)?.revelationType
        holder.name_arabic.text = itemSurah?.get(position)?.name

        val contex = holder.itemView.context
        holder.itemView.setOnClickListener{
            val i = Intent(contex, DetailSurahActivity::class.java)
            i.putExtra("SURAH NUMBER", itemSurah?.get(position)?.number ?: 0)
            i.putExtra("SURAH", itemSurah?.get(position))
            contex.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return itemSurah?.size!!
    }

}