package com.example.task1.fragments.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.dto.CountryItemDto
import com.example.task1.R
import com.example.task1.base.mvp.BaseMvpFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.ext.android.inject

class MapsFragment : BaseMvpFragment<MapsFragmentView, MapsFragmentPresenter>(), MapsFragmentView,
    OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private val mapsModulePresenter: MapsFragmentPresenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPresenter().attachView(this)
        getPresenter().getCountryLocation()
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(google: GoogleMap) {
        googleMap = google
    }

    override fun showAllCountryMarkers(country: MutableList<CountryItemDto>) {
        for (item in country) {
            if (item.latlng.size == 2) {
                val mapLng = LatLng(item.latlng[0], item.latlng[1])
                googleMap.addMarker(
                    MarkerOptions()
                        .position(mapLng)
                        .title(item.name)
                )
            }
        }
    }

    override fun createPresenter() {
        mPresenter = mapsModulePresenter
    }

    override fun getPresenter(): MapsFragmentPresenter = mPresenter


    override fun showError(error: String, throwable: Throwable) {
//        activity?.showAlertDialog()
    }

    override fun showProgress() {
        //      progress.visibility = View.INVISIBLE
    }

    override fun hideProgress() {
        //      progress.visibility = View.GONE
    }


}