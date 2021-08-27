package com.example.task1.fragments.capitalFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.R
import com.example.task1.base.mvvm.Outcome
import com.example.task1.ext.showAlertDialog
import org.koin.androidx.scope.ScopeFragment
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


        mViewModel.getAllCapital()
        mViewModel.capitalLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Outcome.Progress -> {
                    progress.visibility = if (it.loading) View.VISIBLE else View.GONE
                }
                is Outcome.Next -> {
                }
                is Outcome.Success -> {
                    capitalAdapter.repopulate(it.data)
                }
                is Outcome.Failure -> {
                    activity?.showAlertDialog()
                }
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_capital, container, false)
    }


}