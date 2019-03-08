package com.sevenpeakssoftware.zawlynn

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.sevenpeakssoftware.zawlynn.adapter.CarListsAdapter
import com.sevenpeakssoftware.zawlynn.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import com.sevenpeakssoftware.zawlynn.util.CommonUtility


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    lateinit var adapter: CarListsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.loading.observe(this@MainActivity, Observer {
            it?.let {
                if (it) {
                    Snackbar.make(layout_main, resources.getString(R.string.requesting), Snackbar.LENGTH_SHORT).show()
                }
            }
        })
        viewModel.cars.observe(this@MainActivity, Observer {
            it?.let { it1 -> adapter.submitList(it) }
        })
        if(CommonUtility.getInstance().isNetworkAvailable(this@MainActivity)){
            viewModel.getAllData()
        } else{
            Snackbar.make(layout_main, resources.getString(R.string.no_internet), Snackbar.LENGTH_SHORT).show()
        }
    }

    fun initUI() {
        adapter = CarListsAdapter()
        recData.layoutManager = LinearLayoutManager(this@MainActivity)
        recData.adapter = adapter
    }

}
