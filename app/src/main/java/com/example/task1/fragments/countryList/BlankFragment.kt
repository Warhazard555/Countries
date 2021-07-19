package com.example.task1.fragments.countryList

import android.content.Context
import android.os.Bundle
import android.util.Log.d
import android.view.*
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.*
import com.example.task1.data.CountryItem
import com.example.task1.retrofit.RetrofitService
import com.example.task1.room.CountryApp
import com.example.task1.room.CountryDao
import com.example.task1.room.TableModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlankFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var responseBody: MutableList<CountryItem>
    private var retrofitBuilder = RetrofitService.getInstance()
    private var statusSort = true
    private lateinit var progressBar: FrameLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
        if (!statusSort) {
            menu.findItem(R.id.sorted).setIcon(R.drawable.ic_action_up).isChecked = false
        } else {
            menu.findItem((R.id.sorted)).setIcon(R.drawable.ic_action_down).isChecked = true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sorted) {
            if (statusSort) {
                recyclerAdapter.sortDescendingItem()
                item.setIcon(R.drawable.ic_action_up)
            } else {
                recyclerAdapter.sortItem()
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
        progressBar.visibility = ProgressBar.VISIBLE
        retrofitData.enqueue(object : Callback<MutableList<CountryItem>> {
            override fun onResponse(
                call: Call<MutableList<CountryItem>?>,
                response: Response<MutableList<CountryItem>?>
            ) {
                responseBody = response.body() as MutableList<CountryItem>

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

                recyclerAdapter.notifyDataSetChanged()
                recyclerView.adapter = recyclerAdapter
                recyclerAdapter.repopulate(responseBody)
                sorting(statusSort)
                progressBar.visibility = ProgressBar.GONE
            }

            override fun onFailure(call: Call<MutableList<CountryItem>?>, t: Throwable) {
                d("BlankFragment", "onFailure: " + t.message)
                progressBar.visibility = ProgressBar.GONE

            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sortStatusSharedPref()
        recyclerView = view.findViewById(R.id.recycler)
        recyclerAdapter = RecyclerAdapter()
        val daoCountry = CountryApp.mCountryDatabase.сountryDao()
        progressBar = view.findViewById(R.id.progressBar)
        recyclerAdapter.setItemClick { item ->
            val bundle = Bundle()
            bundle.putString(COUNTRY_NAME_KEY, item.name)
            bundle.putString(COUNTRY_FLAG_KEY, item.flag)
            findNavController().navigate(
                R.id.action_blankFragment_to_countryDetailsFragment,
                bundle
            )
        }
        // recyclerView.adapter = recyclerAdapter
        getCountry(daoCountry)
        saveSharedPref(statusSort)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    private fun saveSharedPref(statusSort: Boolean) {

        activity?.getSharedPreferences(FILE_NAME_SHARED_PREF, Context.MODE_PRIVATE)?.edit()
            ?.apply { putBoolean(KEY_SORT, statusSort) }?.apply()
    }

    private fun sortStatusSharedPref() {

        val sharedPrefs =
            activity?.getSharedPreferences(FILE_NAME_SHARED_PREF, Context.MODE_PRIVATE)
        val status = sharedPrefs?.getBoolean(KEY_SORT, statusSort)
        if (status != null) {
            statusSort = status
        }

    }

    fun sorting(statusSort: Boolean) {
        if (!statusSort) {
            recyclerAdapter.sortDescendingItem()
        } else {
            recyclerAdapter.sortItem()
        }
    }
}