package com.fadhil.juzamma

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadhil.juzamma.adapter.AyahAdapter
import com.fadhil.juzamma.api.ApiConfig
import com.fadhil.juzamma.api.DataItem
import com.fadhil.juzamma.api.ResponseAyah
import com.fadhil.juzamma.databinding.ActivityDetailSurahBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailSurahActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSurahBinding
    private  var mediaPlayer : MediaPlayer? = null

    private fun playAudio(url: String) {
        mediaPlayer?.apply {
            reset()
            setDataSource(url)
            prepare()
            start()
        } ?: run{
            mediaPlayer = MediaPlayer().apply {
                setDataSource(url)
                prepare()
                start()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showProgressBar()
        val surahNumber = intent.getIntExtra("SURAH NUMBER", 0)
        dataSurah()
        loadAyahsForSurah(surahNumber)
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun loadAyahsForSurah(surahNumber: Int) {
        ApiConfig.instanceRetrofit.getDetailSurahWithQuranEdition(surahNumber).enqueue(object: Callback<ResponseAyah> {
            override fun onResponse(call: Call<ResponseAyah>, response: Response<ResponseAyah>) {
                hideProgressBar()
                if (response.isSuccessful) {
                    val ayahsResponse = response.body()
                    val ayahs = ayahsResponse?.data?.get(0)?.ayahs
                    val translation = ayahsResponse?.data?.get(2)?.ayahs

                    if (ayahs != null && translation != null) {

                        val ayahAdapter = AyahAdapter(ayahs.filterNotNull(), translation.filterNotNull())
                        binding.rvSurah.apply {
                            layoutManager = LinearLayoutManager(this@DetailSurahActivity)
                            ayahAdapter.notifyDataSetChanged()
                            adapter = ayahAdapter
                        }
                    }
                } else {
                    Toast.makeText(this@DetailSurahActivity, "Gagal memuat data ayah", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ResponseAyah>, t: Throwable) {
                Toast.makeText(this@DetailSurahActivity, "Gagal memuat data: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun dataSurah() {
        val dataSurah = intent.getParcelableExtra<DataItem>("SURAH")
        if (dataSurah != null) {

            val namaSurah = dataSurah.englishName
            val namaTranslate = dataSurah.englishNameTranslation
            val detailAyah = dataSurah.revelationType
            val detailName = dataSurah.name

            binding.tvDetailSurah.text = namaSurah
            binding.tvDetailNameTranslation.text = namaTranslate
            binding.tvDetailAyah.text = detailAyah
            binding.tvDetailName.text = detailName.toString()

        }
    }




}