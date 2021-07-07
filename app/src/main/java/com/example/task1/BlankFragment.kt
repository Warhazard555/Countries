package com.example.task1

import android.content.Context
import android.os.Bundle
import android.util.Log.d
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.data.CountryItem
import com.example.task1.room.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
const val BASE_URL = "https://restcountries.eu/rest/v2/"
/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var responseBody: MutableList<CountryItem>
    var retrofitBuilder = RetrofitService.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
        setHasOptionsMenu(true);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveSharedPref()
        recyclerView = view.findViewById(R.id.recycler)
        CountryApp.dCountryDatabase
        val daoCountry = CountryApp.dCountryDatabase.CountryDao()
        val daoLanguage = CountryApp.dCountryDatabase.LanguageDao()
        getCountry(daoCountry,daoLanguage)
        readSharedPref()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.isChecked) {
            responseBody.sortByDescending { it.area }
            item.setIcon(R.drawable.ic_action_up)
            item.isChecked = false
        } else {
            responseBody.sortBy { it.area }
            item.setIcon(R.drawable.ic_action_down)
            item.isChecked = true
        }
        recyclerAdapter.notifyDataSetChanged()
        return super.onOptionsItemSelected(item)
    }


    private fun getCountry(daoCountry: CountryDao?, daoLanguage: LanguageDao) {

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<CountryItem>?> {
            override fun onResponse(
                call: Call<List<CountryItem>?>,
                response: Response<List<CountryItem>?>
            ) {
                responseBody = (response.body() as MutableList<CountryItem>?)!!

                responseBody.forEach {
                    daoCountry?.insertDatabase(TableModel(it.name, it.capital, it.area))
                   it.languages.forEach{ language ->
                       daoLanguage?.insertDatabase(LanguageTableModel(it.name,language.name))}
                }


                recyclerAdapter = RecyclerAdapter(responseBody)
                recyclerAdapter.notifyDataSetChanged()
                recyclerView.adapter = recyclerAdapter

            }

            override fun onFailure(call: Call<List<CountryItem>?>, t: Throwable) {
                d("BlankFragment", "onFailure: " + t.message)

            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    fun saveSharedPref() {
        activity?.getSharedPreferences("data", Context.MODE_PRIVATE)?.edit()?.apply()
    }

    fun readSharedPref() {
        activity?.getSharedPreferences("data", Context.MODE_PRIVATE)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}