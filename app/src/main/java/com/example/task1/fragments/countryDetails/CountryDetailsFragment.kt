package com.example.task1.fragments.countryDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.task1.COUNTRY_NAME_KEY
import com.example.task1.ERROR
import com.example.task1.R
import com.example.task1.data.CountryItem
import com.example.task1.data.Language
import com.example.task1.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CountryDetailsFragment : Fragment() {

    private lateinit var mCountryName: String
    private lateinit var itemName: AppCompatTextView
    private lateinit var rvLanguages: RecyclerView
    private lateinit var languageAdapter: LanguagesAdapter
    private lateinit var srCountryDetails: SwipeRefreshLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCountryName = arguments?.getString(COUNTRY_NAME_KEY) ?: ERROR

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemName = view.findViewById(R.id.country_name)
        itemName.text = mCountryName
        rvLanguages = view.findViewById(R.id.rv_country_details_languages)
        srCountryDetails = view.findViewById(R.id.sr_country_details)
        languageAdapter = LanguagesAdapter()
        rvLanguages.adapter = languageAdapter

        srCountryDetails.setOnRefreshListener {
            getCountryDetails()
        }
        getCountryDetails()
    }

    private fun getCountryDetails(){
        RetrofitService.getInstance().getCountryByName(mCountryName).enqueue(object : Callback<MutableList<CountryItem>> {
            override fun onResponse(
                call: Call<MutableList<CountryItem>>,
                response: Response<MutableList<CountryItem>>
            ) {
                Log.e("CountryDetailsFragment", response.body().toString())
                languageAdapter.repopulate(
                    (response.body()?.get(0)?.languages ?: mutableListOf()) as MutableList<Language>
                )
                srCountryDetails.isRefreshing = false
                Thread.sleep(1000)
            }

            override fun onFailure(call: Call<MutableList<CountryItem>>, t: Throwable) {
                t.printStackTrace()
                srCountryDetails.isRefreshing = false
            }
        })

    }


}
