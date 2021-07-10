package com.example.task1

import android.content.Context
import android.os.Bundle
import android.util.Log.d
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.data.CountryItem
import com.example.task1.room.CountryApp
import com.example.task1.room.CountryDao
import com.example.task1.room.TableModel
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
    private var retrofitBuilder = RetrofitService.getInstance()
    private var statusSort = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }

        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sortStatusSharedPref()
        recyclerView = view.findViewById(R.id.recycler)
        CountryApp.mCountryDatabase
        val daoCountry = CountryApp.mCountryDatabase.сountryDao()
        getCountry(daoCountry)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
        if (!statusSort) {
            menu.findItem(R.id.sort).setIcon(R.drawable.ic_action_up).isChecked = false
        } else {
            menu.findItem((R.id.sort)).setIcon(R.drawable.ic_action_down).isChecked = true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort) {

            if (statusSort) {
                responseBody.sortByDescending { it.area }
                item.setIcon(R.drawable.ic_action_up)
            } else {
                responseBody.sortBy { it.area }
                item.setIcon(R.drawable.ic_action_down)
            }
            statusSort = !statusSort
            recyclerAdapter.notifyDataSetChanged()
            saveSharedPref(statusSort)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getCountry(daoCountry: CountryDao?) {

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<CountryItem>?> {
            override fun onResponse(
                call: Call<List<CountryItem>?>,
                response: Response<List<CountryItem>?>
            ) {
                responseBody = (response.body() as MutableList<CountryItem>?)!!
                responseBody.sorting(statusSort)
                val list: MutableList<TableModel> = mutableListOf()
                responseBody.let {
                    responseBody.forEach { item ->
                        list.add(
                            TableModel(
                                item.name,
                                item.capital,
                                item.area,
                                item.languages.convertToList()
                            )
                        )
                    }
                }
                daoCountry?.insertDatabase(list)
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

    private fun saveSharedPref(statusSort: Boolean) {

        activity?.getSharedPreferences("data", Context.MODE_PRIVATE)?.edit()
            ?.apply { putBoolean("SortStatus", statusSort) }?.apply()
    }

    private fun sortStatusSharedPref() {

        val sharedPrefs = activity?.getSharedPreferences("data", Context.MODE_PRIVATE)
        val status = sharedPrefs?.getBoolean("SortStatus", statusSort)
        if (status != null) {
            statusSort = status
        }

    }

    fun MutableList<CountryItem>.sorting(statusSort: Boolean) {
        if (!statusSort) {
            this.sortByDescending { it.area }
        } else {
            this.sortBy { it.area }
        }
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