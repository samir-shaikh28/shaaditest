package com.droidtechlab.shaaditestui.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.droidtechlab.shaaditestui.R
import com.droidtechlab.shaaditestui.data.repository.DataSourceRepo
import com.droidtechlab.shaaditestui.data.repository.Interactor
import com.droidtechlab.shaaditestui.data.repository.InteractorVMFactory
import com.droidtechlab.shaaditestui.network.ApiClient
import com.droidtechlab.shaaditestui.network.Error
import com.droidtechlab.shaaditestui.network.Failure
import com.droidtechlab.shaaditestui.network.Success

class MatchListingFragment : Fragment() {


    private lateinit var interactor: Interactor


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.match_listing_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkNotNull(ApiClient.apiClient)
        interactor = ViewModelProvider(this@MatchListingFragment, InteractorVMFactory(DataSourceRepo(requireContext(), ApiClient.apiClient!!))).get(Interactor::class.java)
        loadData()
    }

    private fun loadData() {
        interactor.returnMatchList().observe(viewLifecycleOwner, Observer {  result ->
            when(result) {
                is Success -> {
                    Log.d("###", "success")
                }
                is Error -> {
                    Log.d("###", "error")

                }
                is Failure -> {
                    Log.d("###", "failure")

                }
            }
        })
    }

    companion object {
        const val TAG = "MatchListingFragment"

        @JvmStatic
        fun getInstance() = MatchListingFragment()
    }

}