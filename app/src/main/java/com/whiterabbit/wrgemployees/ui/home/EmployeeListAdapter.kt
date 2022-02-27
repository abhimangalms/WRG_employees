package com.whiterabbit.wrgemployees.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.whiterabbit.wrgemployees.R
import com.whiterabbit.wrgemployees.databinding.ItemListBinding
import com.whiterabbit.wrgemployees.local.entity.EmployeeEntity
import com.whiterabbit.wrgemployees.remote.response.EmployeeResponse

class EmployeeListAdapter(
    private val context: Context,
    val onMainItemClicked: (EmployeeResponse?) -> Unit,
    private var list: List<EmployeeResponse>

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var oldData = emptyList<EmployeeResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemListBinding: ItemListBinding =
            ItemListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemListBinding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(list[position])
        var employee: EmployeeResponse = list[position]
        (holder as EmployeeListAdapter.ViewHolder).bind(employee)
    }


    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<EmployeeResponse>){
        list = newData
        notifyDataSetChanged()

    }

    inner class ViewHolder(binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val binding: ItemListBinding = binding
        fun bind(employee: EmployeeResponse): Unit {

            binding.tvName.text = "${employee.name}"
            if (employee.company?.name.isNullOrEmpty()) {
                binding.tvCompanyName.text = "Name empty"
            } else {
                binding.tvCompanyName.text = "${employee.company?.name}"
            }

            if (employee.profile_image.isNullOrEmpty()) {
                binding.ivProfileImage?.let {
                    Glide.with(context).load(R.drawable.ic_baseline_account_circle_24)
                        .centerCrop()
                        .into(it)
                }
            } else {
                binding.ivProfileImage?.let {
                    Glide.with(context).load(employee.profile_image)
                        .centerCrop()
                        .into(it)
                }
            }

            binding.clEmployeeDetails.setOnClickListener { onMainItemClicked.invoke(employee) }


        }
    }


}