package com.whiterabbit.wrgemployees.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import com.whiterabbit.wrgemployees.R
import com.whiterabbit.wrgemployees.databinding.ActivityHomeBinding
import com.whiterabbit.wrgemployees.databinding.ActivityLaunchBinding
import com.whiterabbit.wrgemployees.ui.home.HomeActivity
import com.whiterabbit.wrgemployees.utils.Utils.startNewActivity
import com.whiterabbit.wrgemployees.utils.network.Resource

import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LaunchActivity : AppCompatActivity() {
    private val viewModel: LaunchViewModel by viewModels()
    private lateinit var binding: ActivityLaunchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservables()
        viewModel.fetchEmployeeData()


    }

    private fun setupObservables() {
        viewModel.fetchDataApiState.asLiveData().observe(this) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(
                        this,
                        getString(R.string.network_error),
                        Toast.LENGTH_SHORT
                    ).show()

                }
                is Resource.Loading -> {
                    Toast.makeText(this, getString(R.string.data_loading), Toast.LENGTH_SHORT).show()

                }
                is Resource.Success -> {
                    Timber.d("Success")
                }

                is Resource.Idle -> Timber.d("Idle")
            }
        }

        viewModel.navigateState.asLiveData().observe(this) {
            when (it) {
                true -> navigateToHome()
                false -> Timber.d("Do nothing")
            }
        }
    }

     private fun navigateToHome() {
         startNewActivity(HomeActivity::class.java)
         finish()
     }
}