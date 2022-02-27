package com.whiterabbit.wrgemployees.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.whiterabbit.wrgemployees.R
import com.whiterabbit.wrgemployees.databinding.FragmentEmployeeDetailsBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentEmployeeDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEmployeeDetailsBinding.inflate(inflater, container, false)
        setUiData()
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()

        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun setUiData() {
        args.employeeDetails.let { employee ->

            binding.tvEmployeeName.text = "${employee.name}"
            binding.tvEmployeeUSerName.text = "${employee.username}"
            binding.tvEmployeeEmail.text = "${employee.email}"
            binding.tvEmployeeAddress.text =
                "${employee.address?.city} , ${employee.address?.street} , ${employee.address?.zipcode}"
            binding.tvEmployeePhone.text = "${employee.phone}"
            binding.tvEmployeeWebsite.text = "${employee.website}"
            binding.tvEmployeeCompany.text = "${employee.company?.name}"


            binding.ivEmployeeImage.let {

                if (employee.profile_image.isNullOrEmpty()) {
                    Glide.with(requireContext()).load(R.drawable.ic_baseline_account_circle_24)
                        .centerCrop()
                        .into(it)
                } else {
                    Glide.with(requireContext()).load(employee.profile_image)
                        .centerCrop()
                        .into(it)
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}