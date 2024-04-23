package com.fadhil.juzamma.adapter

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.juzamma.R
import com.fadhil.juzamma.api.AyahsItem

class AyahAdapter(
    private val ayahsList: List<AyahsItem>,
    private val translations: List<AyahsItem>
    ) : RecyclerView.Adapter<AyahAdapter.AyahViewHolder>() {

    class AyahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ayahNumber: TextView = itemView.findViewById(R.id.item_number_ayah)
        val itemAyah : TextView = itemView.findViewById(R.id.item_ayah)
        val translate : TextView = itemView.findViewById(R.id.item_translation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyahViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_ayah, parent, false)
        return AyahViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AyahViewHolder, position: Int) {
        val currentAyah = ayahsList[position]
        val currentTranslation = translations[position]
        holder.ayahNumber.text = currentAyah.numberInSurah.toString()

        val bismillahPrefix = "بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ"
        val ayahText = currentAyah.text

        val displayText = if (ayahText?.startsWith(bismillahPrefix) == true) {
            ayahText.substring(bismillahPrefix.length)
        } else {
            ayahText
        }
        holder.itemAyah.text = displayText
        holder.translate.text = currentTranslation.text

        val context = holder.itemView.context
        holder.itemView.setOnClickListener {
            val audioUrl = currentAyah.audio
            if (!audioUrl.isNullOrBlank()) {
                showAudioDialog(context, audioUrl)
            } else {
                AlertDialog.Builder(context)
                    .setMessage("Audio tidak tersedia")
                    .setPositiveButton("Ok") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                    .show()

            }
        }

    }

    override fun getItemCount(): Int {
        return ayahsList.size
    }

    private fun showAudioDialog(context: Context, audioUrl: String) {
        val alertDialog = AlertDialog.Builder(context)
            .setTitle("Memutar Audio")
            .setMessage("Apakah Anda ingin memutar audio untuk ayat ini?")
            .setPositiveButton("Ya") { _, _ ->
                playAudio(context, audioUrl)
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        alertDialog.show()
    }


    private fun playAudio(context: Context, audioUrl: String) {
        Log.d("AudioPlayer", "Memutar audio dari URL: $audioUrl")
        val mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(audioUrl)
        mediaPlayer.prepare()
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            Log.d("AudioPlayer", "Audio selesai diputar")
            mediaPlayer.release()
        }
    }

}