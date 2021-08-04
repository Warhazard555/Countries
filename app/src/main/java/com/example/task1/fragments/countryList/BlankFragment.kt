package com.example.task1.fragments.countryList

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.task1.*
import com.example.task1.data.CountryItem
import com.example.task1.ext.showAlertDialog
import com.example.task1.retrofit.RetrofitService
import com.example.task1.room.CountryApp
import com.example.task1.room.CountryDao
import com.example.task1.room.TableModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit


class BlankFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var responseBody: MutableList<CountryItem>
    private var retrofitBuilder = RetrofitService.getInstance()
    private var statusSort = true
    private lateinit var progressBar: FrameLayout
    private lateinit var srCountry: SwipeRefreshLayout
    private val mSearchSubject = BehaviorSubject.create<String>()
    private val mCompositeDisposable = CompositeDisposable()


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
        val search: MenuItem = menu.findItem(R.id.app_bar_search)
        val searchView: SearchView = search.actionView as SearchView
        searchView.queryHint = "Search"

        val disposable = getSearchSubject()
            ?.subscribe({
                recyclerAdapter.repopulate(it)
            },{throwable ->
                throwable.printStackTrace()
            })
        mCompositeDisposable.add(disposable)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query.let { mSearchSubject.onNext(query) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mSearchSubject.onNext(newText)
                return true
            }
        })
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
        if (item.itemId == R.id.all_map_fragment) {
            findNavController().navigate(R.id.action_blankFragment_to_mapsFragment)
        }
        //Search Dialog
//        if (item.itemId == R.id.app_bar_search) {
//            activity?.showDialogWithOneButton(
//                "Find Country",
//                R.string.find
//            ) { findCountry() }
//        }

        return super.onOptionsItemSelected(item)
    }

    private fun getCountry(daoCountry: CountryDao?, isRefresh: Boolean) {
        progressBar.visibility = if (isRefresh) View.GONE else View.VISIBLE
         retrofitBuilder.getData()
             .flatMap { response ->
                 Flowable.fromSupplier {
                     addCountryDataBase(response, daoCountry)
                     return@fromSupplier response
                 }
             }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe( {response ->

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
    }

    private fun addCountryDataBase(
        response: MutableList<CountryItem>,
        countryDao: CountryDao?,
    ){
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
       countryDao?.insertDatabase(list)
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

    private fun getSearchSubject(): @NonNull Observable<MutableList<CountryItem>>? = mSearchSubject
        .filter { it.length >= MIN_SEARCH_STRING_LENGTH }
        .debounce(DEBOUNCE_TIME_MILLIS, TimeUnit.MILLISECONDS)
        .distinctUntilChanged()
        .map { it.lowercase() }
        .flatMap {
            RetrofitService.getInstance().getCountryByName(it).toObservable()
                .onErrorResumeNext { Observable.just(mutableListOf()) }
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
    //Search Dialog
//    private fun findCountry() {
//        val countryName = COUNTRY_FIND_NAME
//        val listCountry =
//            responseBody.filter { it.name.contains(countryName, true) } as MutableList<CountryItem>
//        recyclerAdapter.repopulate(listCountry)
//    }

}