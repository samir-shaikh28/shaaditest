package com.droidtechlab.shaaditestui.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.droidtechlab.shaaditestui.R
import com.droidtechlab.shaaditestui.RequestHandlerInterface
import com.droidtechlab.shaaditestui.data.db.DB
import com.droidtechlab.shaaditestui.data.db.DBInstance
import com.droidtechlab.shaaditestui.data.models.CustomModel
import com.droidtechlab.shaaditestui.data.models.MatchItemModel
import com.droidtechlab.shaaditestui.data.repository.DataSourceRepo
import com.droidtechlab.shaaditestui.data.repository.Interactor
import com.droidtechlab.shaaditestui.data.repository.InteractorVMFactory
import com.droidtechlab.shaaditestui.databinding.MatchListingFragmentBinding
import com.droidtechlab.shaaditestui.network.ApiClient
import com.droidtechlab.shaaditestui.network.Error
import com.droidtechlab.shaaditestui.network.Failure
import com.droidtechlab.shaaditestui.network.Success
import com.droidtechlab.shaaditestui.utils.GenericRecyclerAdapter
import com.droidtechlab.shaaditestui.utils.NetworkUtils
import com.droidtechlab.shaaditestui.utils.VerticalSpaceItemDecoration
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MatchListingFragment : Fragment(), RequestHandlerInterface, View.OnClickListener {


    private lateinit var binding: MatchListingFragmentBinding
    private lateinit var interactor: Interactor
    private lateinit var mDbInstance: DB
    private val compositeDisposable = CompositeDisposable()
    private var networkConnectivity: NetworkConnectivityReceiver? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mDbInstance = DBInstance.getDbInstance(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MatchListingFragmentBinding.inflate(inflater, container, false)
        binding.btnRetry.setOnClickListener(this)
        networkConnectivity = NetworkConnectivityReceiver()
        binding.progress.visibility = View.VISIBLE
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.addItemDecoration(
            VerticalSpaceItemDecoration(
                resources.getDimension(R.dimen.vertical_margin_half).toInt()
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkNotNull(ApiClient.apiClient)
        interactor = ViewModelProvider(
            this@MatchListingFragment,
            InteractorVMFactory(DataSourceRepo(requireContext(), ApiClient.apiClient!!))
        ).get(Interactor::class.java)
        loadData()
    }


    @Suppress("DEPRECATION")
    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        requireContext().registerReceiver(networkConnectivity, filter)
    }

    override fun onPause() {
        super.onPause()
        requireContext().unregisterReceiver(networkConnectivity)
    }

    private fun loadData() {

        if (!NetworkUtils.isNetworkAvailable(requireContext())) {
            fetchFromDatabase(false)
            return
        }

        interactor.returnMatchList().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Success -> {
                    if (consumeCallback()) return@Observer
                    fetchFromDatabase(false)
                }
                is Error -> {
                    updateUi(false, "Something Went Wrong")
                }
                is Failure -> {
                    if(binding.recycler.size > 0) {
                        Snackbar.make(binding.root, "Something went wrong", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Try Again") {
                                loadData()
                            }.show()
                    } else {
                        updateUi(false, result.throwable.message)
                    }


                }
            }
        })
    }

    private fun fetchFromDatabase(isNetworkAvailable: Boolean) {
        if (!::mDbInstance.isInitialized) {
            Log.e(this.javaClass.simpleName, "DB is not initialized")
        }

        val matchModelList = mutableListOf<CustomModel>()

        compositeDisposable.add(
            Observable.fromCallable {
                mDbInstance.shaadiMatchListDao().returnMatchList()
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ entityList ->
                    if (!entityList.isNullOrEmpty()) {
                        updateUi(true, null)
                        entityList.forEach { entity ->
                            matchModelList.add(
                                MatchItemModel(
                                    matchItem = entity,
                                    requestManager = Glide.with(this@MatchListingFragment),
                                    listener = this@MatchListingFragment
                                )
                            )
                        }
                        val messageAdapter = GenericRecyclerAdapter(matchModelList)
                        binding.recycler.adapter = messageAdapter
                        messageAdapter.notifyDataSetChanged()
                    } else {
                        if (isNetworkAvailable) {
                            updateUi(false, "No Data Found!")
                        } else {
                            updateUi(false, "Oops! No Internet Connection!!!")

                        }
                    }
                }, {
                    binding.progress.visibility = View.GONE

                })
        )
    }

    private fun updateUi(isDataAvailable: Boolean, message: String?) {
        binding.progress.visibility = View.GONE
        if (isDataAvailable) {
            binding.container.visibility = View.VISIBLE
            binding.lytError.visibility = View.GONE
        } else {
            binding.container.visibility = View.GONE
            binding.lytError.visibility = View.VISIBLE
            binding.txtError.text = message
        }
    }

    /**
     *  Check's if the fragment is detached or is being removed from it's parent
     *  Helps to consume code flow so that UI operations are not performed while fragment is removing or already detached.
     */
    fun consumeCallback(): Boolean {
        return isDetached || isRemoving || context == null
    }

    companion object {
        const val TAG = "MatchListingFragment"

        @JvmStatic
        fun getInstance() = MatchListingFragment()
    }

    override fun acceptRequest(id: Long) {
        Observable.fromCallable {
            mDbInstance.shaadiMatchListDao().setAccepted(id)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun declineRequest(id: Long) {
        Observable.fromCallable {
            mDbInstance.shaadiMatchListDao().setRejected(id)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }


    inner class NetworkConnectivityReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (NetworkUtils.isNetworkAvailable(context)) {
                binding.btnRetry.performClick()
            }
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_retry) {
            loadData()
        }
    }
}


