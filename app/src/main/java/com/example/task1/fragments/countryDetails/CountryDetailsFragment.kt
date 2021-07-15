package com.example.task1.fragments.countryDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.task1.COUNTRY_FLAG_KEY
import com.example.task1.COUNTRY_NAME_KEY
import com.example.task1.ERROR
import com.example.task1.R
import com.example.task1.data.CountryItem
import com.example.task1.data.Language
import com.example.task1.retrofit.RetrofitService
import com.google.android.gms.maps.CameraUpdateFactory.newLatLng
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class CountryDetailsFragment : Fragment() {

    private lateinit var mCountryName: String
    private lateinit var itemName: AppCompatTextView
    private lateinit var rvLanguages: RecyclerView
    private lateinit var languageAdapter: LanguagesAdapter
    private lateinit var srCountryDetails: SwipeRefreshLayout
    private lateinit var mapView: MapView
    private lateinit var flag: String
    private lateinit var googleMap: GoogleMap
    private lateinit var flagView: AppCompatImageView
    private lateinit var mapLng: LatLng
    private lateinit var countryItem: CountryItem
    private var countryList: MutableList<CountryItem>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCountryName = arguments?.getString(COUNTRY_NAME_KEY) ?: ERROR
        flag = arguments?.getString(COUNTRY_FLAG_KEY) ?: ERROR

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
        flagView = view.findViewById(R.id.iv_country_flag)
        flagView.loadImageSvg(flag)
        mapView = view.findViewById(R.id.mv_country_details)
        mapView.onCreate(savedInstanceState)
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
                countryList = response.body()

                Log.e("CountryDetailsFragment", countryList.toString())
                countryList?.get(0)?.also { countryItem = it }

                languageAdapter.repopulate(
                    countryItem.languages as MutableList<Language>
                )
                srCountryDetails.isRefreshing = false

                mapLng = LatLng(countryItem.latlng[0], countryItem.latlng[1])

                getMapLocation(mapLng)
            }

            override fun onFailure(call: Call<MutableList<CountryItem>>, t: Throwable) {
                t.printStackTrace()
                srCountryDetails.isRefreshing = false
            }
        })
    }

    private fun AppCompatImageView.loadImageSvg(url: String) {
        val image = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadImageSvg.context)) }
            .build()
        val request = ImageRequest.Builder(this.context)
            .data(url)
            .target(this)
            .build()
        image.enqueue(request)
    }

    fun getMapLocation(latLng: LatLng) {
        mapView.getMapAsync {
            googleMap = it
            googleMap.moveCamera(newLatLng(latLng))

        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

}