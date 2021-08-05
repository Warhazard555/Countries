package com.example.task1.fragments.countryList

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.*
import android.widget.FrameLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.task1.*
import com.example.task1.base.mvvm.Outcome
import com.example.task1.data.CountryItem
import com.example.task1.ext.showAlertDialog
import io.reactivex.rxjava3.disposables.CompositeDisposable


class BlankFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var responseBody: MutableList<CountryItem>
    private var statusSort = true
    private lateinit var progressBar: FrameLayout
    private lateinit var srCountry: SwipeRefreshLayout
    private val mCompositeDisposable = CompositeDisposable()
    private val mViewModel = BlankFragmentViewModel(SavedStateHandle())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sortStatusSharedPref()
        srCountry = view.findViewById(R.id.sr_country)
        recyclerView = view.findViewById(R.id.recycler)
        recyclerAdapter = RecyclerAdapter()
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
        val filterBar: View = view.findViewById(R.id.filter_bar)
        filterBar.setOnClickListener {
            findNavController().navigate(R.id.action_blankFragment_to_countryFilterFragment)
        }
        mViewModel.getCountryByName()
        mViewModel.mCountryLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Outcome.Progress -> {
                    progressBar.visibility = if (srCountry.isRefreshing) View.GONE else View.VISIBLE
                }
                is Outcome.Next -> {
                    responseBody = it.data
                    mViewModel.getCountryDB(responseBody)
                    recyclerAdapter.notifyDataSetChanged()
                    recyclerView.adapter = recyclerAdapter
                    recyclerAdapter.repopulate(responseBody)
                    sorting(statusSort)
                    srCountry.isRefreshing = false
                }
                is Outcome.Failure -> {
                    activity?.showAlertDialog()
                    srCountry.isRefreshing = false
                    progressBar.visibility = View.GONE
                }
                is Outcome.Success -> {
                    progressBar.visibility = View.GONE
                }
            }
        })
        srCountry.setOnRefreshListener {
            mViewModel.getCountryByName()
        }

        saveSharedPref(statusSort)
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

        val disposable = mViewModel.getSearchSubject()
            ?.subscribe({
                recyclerAdapter.repopulate(it)
            }, { throwable ->
                throwable.printStackTrace()
            })
        mCompositeDisposable.add(disposable)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query.let { mViewModel.mSearchSubject.onNext(query) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mViewModel.mSearchSubject.onNext(newText)
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

        return super.onOptionsItemSelected(item)
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