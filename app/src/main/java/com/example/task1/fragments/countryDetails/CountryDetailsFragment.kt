package com.example.task1.fragments.countryDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.task1.COUNTRY_FLAG_KEY
import com.example.task1.COUNTRY_NAME_KEY
import com.example.task1.ERROR
import com.example.task1.R
import com.example.task1.base.mvp.BaseMvpFragment
import com.example.task1.data.CountryItem
import com.example.task1.data.Language
import com.example.task1.ext.loadImageSvg
import com.google.android.gms.maps.CameraUpdateFactory.newLatLng
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng


class CountryDetailsFragment : BaseMvpFragment<CountryDetailsView, CountryDetailsPresenter>(), CountryDetailsView {

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
        getPresenter().attachView(this)
        itemName = view.findViewById(R.id.country_name)
        itemName.text = mCountryName
        flagView = view.findViewById(R.id.iv_country_flag)
        mapView = view.findViewById(R.id.mv_country_details)
        mapView.onCreate(savedInstanceState)
        rvLanguages = view.findViewById(R.id.rv_country_details_languages)
        srCountryDetails = view.findViewById(R.id.sr_country_details)
        progress = view.findViewById(R.id.progress)
        languageAdapter = LanguagesAdapter()
        rvLanguages.adapter = languageAdapter

        srCountryDetails.setOnRefreshListener {
            getPresenter().getCountryByName(mCountryName, true)
        }
        getPresenter().getCountryByName(mCountryName, false)
    }

    override fun showCountryInfo(country: CountryItem) {
        Log.e("CountryDetailsFragment", country.toString())
        languageAdapter.repopulate(
            country.languages as MutableList<Language>
        )
        flagView.loadImageSvg(country.flag)
        if (country.area != 0.0) {
            mapLng = LatLng(country.latlng[0], country.latlng[1])
            getMapLocation(mapLng)
        }
        srCountryDetails.isRefreshing = false

    }

    override fun createPresenter() {
        mPresenter = CountryDetailsPresenter()
    }

    override fun getPresenter(): CountryDetailsPresenter = mPresenter

    override fun showError(error: String, throwable: Throwable) {
//        activity?.showAlertDialog()
    }

    override fun showProgress() {
        progress.visibility = View.INVISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
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
