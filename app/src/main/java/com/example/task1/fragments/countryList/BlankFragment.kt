package com.example.task1.fragments.countryList

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.task1.*
import com.example.task1.data.CountryItem
import com.example.task1.ext.showAlertDialog
import com.example.task1.ext.showDialogWithOneButton
import com.example.task1.retrofit.RetrofitService
import com.example.task1.room.CountryApp
import com.example.task1.room.CountryDao
import com.example.task1.room.TableModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class BlankFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var responseBody: MutableList<CountryItem>
    private var retrofitBuilder = RetrofitService.getInstance()
    private var statusSort = true
    private lateinit var progressBar: FrameLayout
    private lateinit var srCountry: SwipeRefreshLayout


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
        if (item.itemId == R.id.app_bar_search) {
            activity?.showDialogWithOneButton(
                "Find Country",
                R.string.find
            ) { findCountry() }
        }
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

    private fun getCountry(daoCountry: CountryDao?, isRefresh: Boolean) {
        progressBar.visibility = if (isRefresh) View.GONE else View.VISIBLE
         retrofitBuilder.getData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe( {response ->

                    responseBody = response

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
                    srCountry.isRefreshing = false
                    progressBar.visibility = ProgressBar.GONE

                }, {throwable ->
                throwable.printStackTrace()
                activity?.showAlertDialog()
                srCountry.isRefreshing = false
                progressBar.visibility = ProgressBar.GONE
            })

//        retrofitData.enqueue(object : Callback<MutableList<CountryItem>> {
//            override fun onResponse(
//                call: Call<MutableList<CountryItem>?>,
//                response: Response<MutableList<CountryItem>?>
//            ) {
//                responseBody = response.body() as MutableList<CountryItem>
//
//                val list: MutableList<TableModel> = mutableListOf()
//                responseBody.let {
//                    responseBody.forEach { item ->
//                        list.add(
//                            TableModel(
//                                item.name,
//                                item.capital,
//                                item.area,
//                                item.languages.convertToList()
//                            )
//                        )
//                    }
//                }
//                daoCountry?.insertDatabase(list)
//
//                recyclerAdapter.notifyDataSetChanged()
//                recyclerView.adapter = recyclerAdapter
//                recyclerAdapter.repopulate(responseBody)
//                sorting(statusSort)
//                srCountry.isRefreshing = false
//                progressBar.visibility = ProgressBar.GONE
//            }
//
//            override fun onFailure(call: Call<MutableList<CountryItem>?>, t: Throwable) {
//                d("BlankFragment", "onFailure: " + t.message)
//                activity?.showAlertDialog()
//                srCountry.isRefreshing = false
//                progressBar.visibility = ProgressBar.GONE
//            }
//        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sortStatusSharedPref()
        srCountry = view.findViewById(R.id.sr_country)
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
        srCountry.setOnRefreshListener {
            getCountry(daoCountry, true)
        }
        getCountry(daoCountry, false)
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

    private fun findCountry() {
        val countryName = COUNTRY_FIND_NAME
        val listCountry =
            responseBody.filter { it.name.contains(countryName, true) } as MutableList<CountryItem>
        recyclerAdapter.repopulate(listCountry)
    }

}