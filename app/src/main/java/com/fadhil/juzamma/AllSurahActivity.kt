package com.fadhil.juzamma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.fadhil.juzamma.adapter.SurahAdapter
import com.fadhil.juzamma.api.ApiConfig
import com.fadhil.juzamma.api.DataItem
import com.fadhil.juzamma.api.ResponseSurah
import com.fadhil.juzamma.databinding.ActivityAllSurahBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class AllSurahActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllSurahBinding

    private lateinit var surahAdapter: SurahAdapter
    private var originalSurahList: List<DataItem> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllSurahBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showProgressBar()
        getDataSurah()
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun getDataSurah() {
        ApiConfig.instanceRetrofit.getListSurah().enqueue(object :
            Callback<ResponseSurah> {
            override fun onResponse(call: Call<ResponseSurah>, response: Response<ResponseSurah>) {
                hideProgressBar()
                if (response.isSuccessful) {
                    val responseSurah = response.body()
                    val status = responseSurah?.status
                    if (status != null) {
                        Log.d("DATA", status)
                    }
                    originalSurahList = responseSurah?.data
                        ?.filterNotNull()
                        ?.filter { it.number in 78..114 }
                        ?: emptyList()
                    displaySurahList(originalSurahList)
                }
            }

            override fun onFailure(call: Call<ResponseSurah>, t: Throwable) {
                Toast.makeText(this@AllSurahActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun displaySurahList(surahList: List<DataItem>) {
        surahAdapter = SurahAdapter(surahList)
        binding.rycAyah.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@AllSurahActivity, 1)
            adapter = surahAdapter
        }
        binding.etSeach.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase(Locale.getDefault())
                val filteredList = originalSurahList.filter { surahList ->
                    surahList.name?.lowercase(Locale.getDefault())?.contains(query) == true ||
                            surahList.englishName?.lowercase(Locale.getDefault())?.contains(query) == true ||
                            surahList.englishNameTranslation?.lowercase(Locale.getDefault())?.contains(query) == true
                }
                surahAdapter.filterList(filteredList)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used
            }

            override fun afterTextChanged(s: Editable?) {
                // Not used
            }
        })
    }
}
