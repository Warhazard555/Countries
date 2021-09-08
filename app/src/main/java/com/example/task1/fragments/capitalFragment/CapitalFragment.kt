package com.example.task1.fragments.capitalFragment

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.outcome.Outcome
import com.example.task1.R
import com.example.task1.ext.showAlertDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.scope.ScopeFragment
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class CapitalFragment : ScopeFragment() {

    private val mViewModel: CapitalFragmentViewModel by stateViewModel()
    private val capitalAdapter = CapitalAdapter()
    private lateinit var progress: FrameLayout
    private lateinit var recycler: RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress = view.findViewById(R.id.progressBar)
        recycler = view.findViewById(R.id.recyclerView)
        recycler.adapter = capitalAdapter


//        mViewModel.getAllCapital()
//        mViewModel.capitalLiveData.observe(viewLifecycleOwner, {
//            when (it) {
//                is Outcome.Progress -> {
//                    progress.visibility = if (it.loading) View.VISIBLE else View.GONE
//                }
//                is Outcome.Next -> {
//                }
//                is Outcome.Success -> {
//                    capitalAdapter.submitList(it.data)
//                }
//                is Outcome.Failure -> {
//                    activity?.showAlertDialog()
//                }
//            }
//        })
        mViewModel.getAllCapitalFlow().asLiveData(lifecycleScope.coroutineContext)
            .observe(viewLifecycleOwner, {
                when (it) {
                    is Outcome.Failure -> {
                        activity?.showAlertDialog()
                    }
                    is Outcome.Next -> {
                    }
                    is Outcome.Progress -> {
                        progress.visibility = if (it.loading) View.VISIBLE else View.GONE
                    }
                    is Outcome.Success -> {
                        capitalAdapter.submitList(it.data)
                    }
                }
            })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.capital_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_capital, container, false)
    }


}