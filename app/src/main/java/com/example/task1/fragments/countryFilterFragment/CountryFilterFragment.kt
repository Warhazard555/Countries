package com.example.task1.fragments.countryFilterFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.domain.dto.CountryItemDto
import com.example.domain.outcome.Outcome
import com.example.task1.COUNTRY_FILTER_LISTNER_KEY
import com.example.task1.FILTER_COUNTRY_KEY
import com.example.task1.R
import com.example.task1.ext.distanceFromMyLocation
import com.example.task1.ext.lastLocation
import com.google.android.material.slider.RangeSlider
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class CountryFilterFragment : ScopeFragment() {

    private var areaMin: Float = 0F
    private var areaMax: Float = 0F
    private var populationMin: Int = 0
    private var populationMax: Int = 0
    private lateinit var mAreaSlider: RangeSlider
    private lateinit var mPopulationSlider: RangeSlider
    private lateinit var mCountry: List<CountryItemDto>
    private lateinit var mCountryFilter: List<CountryItemDto>
    private lateinit var minArea: AppCompatTextView
    private lateinit var maxArea: AppCompatTextView
    private lateinit var minPopulation: AppCompatTextView
    private lateinit var maxPopulation: AppCompatTextView
    private lateinit var buttonApply: AppCompatButton
    private lateinit var distance: AppCompatEditText
    private lateinit var strDistance: String
    private var distanceMax: Int = 0
    private val mViewModel: CountryFilterViewModel by stateViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getCountryList()
        mViewModel.mCountryFilterLivedata.observe(viewLifecycleOwner, {
            when (it) {
                is Outcome.Progress -> {
                }
                is Outcome.Next -> {
                    mCountry = it.data
                    setAreaValues(mCountry)
                    setPopulationValues(mCountry)
                }
                is Outcome.Failure -> {
                }
                is Outcome.Success -> {
                }
            }
        })

        mAreaSlider = view.findViewById(R.id.rs_area)
        mAreaSlider.addOnChangeListener { slider, _, _ ->
            minArea = view.findViewById(R.id.tv_min_area)
            maxArea = view.findViewById(R.id.tv_max_area)
            areaMin = slider.values[0]
            areaMax = slider.values[1]
            minArea.text = getString(
                R.string.area_min,
                slider.values[0]
            )
            maxArea.text = getString(
                R.string.area_max,
                slider.values[1]
            )
        }
        mPopulationSlider = view.findViewById(R.id.rs_population)
        mPopulationSlider.addOnChangeListener { slider, _, _ ->
            minPopulation = view.findViewById(R.id.tv_min_population)
            maxPopulation = view.findViewById(R.id.tv_max_population)
            populationMin = slider.values[0].toInt()
            populationMax = slider.values[1].toInt()
            minPopulation.text = getString(
                R.string.population_min,
                slider.values[0]
            )
            maxPopulation.text = getString(
                R.string.population_max,
                slider.values[1]
            )
        }
        context.let {
            if (it != null) {
                lastLocation(it)
            }
        }
        distance = view.findViewById(R.id.etv_distance)
        buttonApply = view.findViewById(R.id.apply_button)
        buttonApply.setOnClickListener {

            strDistance = distance.text.toString()
            mCountryFilter = if (strDistance.isEmpty()) {
                mCountry
                    .filter { it.area in areaMin..areaMax }
                    .filter { it.population in populationMin..populationMax }
            } else {
                distanceMax = strDistance.toInt()
                mCountry
                    .filter { it.area in areaMin..areaMax }
                    .filter { it.population in populationMin..populationMax }
                    .filter { distanceFromMyLocation(it.latlng, distanceMax) }
            }
            setFragmentResult(
                COUNTRY_FILTER_LISTNER_KEY, bundleOf(
                    FILTER_COUNTRY_KEY to mCountryFilter
                )
            )
            findNavController().navigate(R.id.action_countryFilterFragment_to_blankFragment)

        }
    }

    private fun setAreaValues(list: List<CountryItemDto>) {
        val areaMin = list.minByOrNull { it.area }?.area
        val areaMax = list.maxByOrNull { it.area }?.area
        if (areaMin != null && areaMax != null) {
            mAreaSlider.valueFrom = areaMin
            mAreaSlider.valueTo = areaMax
        }
        mAreaSlider.setValues(areaMin, areaMax)
    }

    private fun setPopulationValues(list: List<CountryItemDto>) {
        val populationMin = list.minByOrNull { it.population }?.population?.toFloat()
        val populationMax = list.maxByOrNull { it.population }?.population?.toFloat()
        if (populationMin != null && populationMax != null) {
            mPopulationSlider.valueFrom = populationMin
            mPopulationSlider.valueTo = populationMax
        }
        mPopulationSlider.setValues(populationMin, populationMax)
    }


}