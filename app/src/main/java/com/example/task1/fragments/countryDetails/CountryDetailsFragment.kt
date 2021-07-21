package com.example.task1.fragments.countryDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.task1.COUNTRY_FLAG_KEY
import com.example.task1.COUNTRY_NAME_KEY
import com.example.task1.ERROR
import com.example.task1.R
import com.example.task1.data.CountryItem
import com.example.task1.data.Language
import com.example.task1.ext.loadImageSvg
import com.example.task1.ext.showAlertDialog
import com.example.task1.retrofit.RetrofitService
import com.google.android.gms.maps.CameraUpdateFactory.newLatLng
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers



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
    private lateinit var progress: FrameLayout


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
        progress = view.findViewById(R.id.progress)
        languageAdapter = LanguagesAdapter()
        rvLanguages.adapter = languageAdapter

        srCountryDetails.setOnRefreshListener {
            getCountryDetails(true)
        }
        getCountryDetails(false)
    }

    private fun getCountryDetails(isRefresh: Boolean) {
        progress.visibility = if (isRefresh) View.GONE else View.VISIBLE
        RetrofitService.getInstance().getCountryByName(mCountryName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe( {response ->
                countryList = response

                Log.e("CountryDetailsFragment", countryList.toString())
                countryList?.get(0)?.also { countryItem = it }
                //  countryItem = (countryList?.get(0).also { it } ?: "") as CountryItem

                languageAdapter.repopulate(
                    countryItem.languages as MutableList<Language>
                )

                srCountryDetails.isRefreshing = false
                if (countryItem.area != 0.0) {
                    mapLng = LatLng(countryItem.latlng[0], countryItem.latlng[1])

                    getMapLocation(mapLng)
                }
                progress.visibility = View.GONE

            },{throwable -> throwable.printStackTrace()
                srCountryDetails.isRefreshing = false
                activity?.showAlertDialog()
                progress.visibility = View.GONE})
//                 {
//                    countryList = response.body()
//
//                    Log.e("CountryDetailsFragment", countryList.toString())
//                    countryList?.get(0)?.also { countryItem = it }
//                    //  countryItem = (countryList?.get(0).also { it } ?: "") as CountryItem
//
//                    languageAdapter.repopulate(
//                        countryItem.languages as MutableList<Language>
//                    )
//
//                    srCountryDetails.isRefreshing = false
//                    if (countryItem.area != 0.0) {
//                        mapLng = LatLng(countryItem.latlng[0], countryItem.latlng[1])
//
//                        getMapLocation(mapLng)
//                    }
//                    progress.visibility = View.GONE
//                }
//
//            override fun onFailure(call: Call<MutableList<CountryItem>>, t: Throwable) {
//                t.printStackTrace()
//                srCountryDetails.isRefreshing = false
//                activity?.showAlertDialog()
//                progress.visibility = View.GONE
//            }
//        })
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
