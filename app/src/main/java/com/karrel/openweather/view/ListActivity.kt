package com.karrel.openweather.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.karrel.openweather.R
import com.karrel.openweather.base.BaseActivity
import com.karrel.openweather.view.adapter.CurrentListAdapter
import com.karrel.openweather.viewmodel.CurrentViewModel
import kotlinx.android.synthetic.main.activity_weather_list.*
import kotlinx.android.synthetic.main.content_weather_list.*

class ListActivity : BaseActivity() {

    companion object {
        private const val REQUEST_LOCATION = 999
    }

    private val listAdapter: CurrentListAdapter by lazy {
        CurrentListAdapter { cityId, cityName ->
            DetailActivity.startActivity(this, cityId, cityName)
        }
    }

    private val viewModel by lazy { CurrentViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)
        setSupportActionBar(toolbar)

        setupRecyclerView()
        observeData()

        startIntro()
        setupSwipeRefresh()

        loadLocation()
    }

    override fun onStart() {
        super.onStart()

        viewModel.loadWeatherListData()
    }

    private fun loadLocation() {
        if (!hasLocationPermission()) return

        viewModel.loadLocation(this)
    }

    private fun hasLocationPermission(): Boolean {
        val locationPermissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        val acceptedPermissions = locationPermissions.all { permission ->
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
        }

        if (acceptedPermissions) {
            return true
        } else {

            val shouldShowRequestPermissionRationale = locationPermissions.all { permission ->
                ActivityCompat.shouldShowRequestPermissionRationale(this, permission)
            }

            val requestPermission : () -> Unit = {ActivityCompat.requestPermissions(this, locationPermissions, REQUEST_LOCATION)}

            if (shouldShowRequestPermissionRationale) {
                // 사용자가 거부한적이 있는 경우
                requestPermission()
                return false
            } else {
                // 처음 요청
                requestPermission()
                return false
            }
        }
    }

    private fun setupSwipeRefresh() {
        swiperRefresh.setColorSchemeResources(
            R.color.colorPrimary,
            R.color.colorAccent
        )

        swiperRefresh.setOnRefreshListener {
            viewModel.loadWeatherListData()
        }
    }

    private fun startIntro() {
        startActivity(Intent(this, IntroActivity::class.java))
        overridePendingTransition(0, 0)
    }

    private fun observeData() {
        viewModel.apply {
            currentList.observe(this@ListActivity, Observer {
                listAdapter.setData(it)
            })
        }
    }

    private fun setupRecyclerView() {
        recyclerWeather.apply {
            adapter = listAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun showProgressDialog() {
//        imageProgress.visibility = View.VISIBLE
    }

    override fun hideProgressDialog() {
        swiperRefresh.isRefreshing = false
    }

    private fun onResultLocationPermission(resultCode: Int) {
        if (resultCode == Activity.RESULT_OK) {
            loadLocation()
        } else {
            // 권한이 없으면 앱을 사용할 수 없습니다.
            Toast.makeText(this, R.string.cannout_use_app_without_permission, Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_LOCATION ){
            onResultLocationPermission(resultCode)
        }
    }


}
