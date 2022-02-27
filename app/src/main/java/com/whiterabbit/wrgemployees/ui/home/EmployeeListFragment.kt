package com.whiterabbit.wrgemployees.ui.home

import android.os.Bundle
import android.text.BoringLayout
import android.view.*
import androidx.annotation.Nullable
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.whiterabbit.wrgemployees.R
import com.whiterabbit.wrgemployees.databinding.FragmentEmployeeListBinding
import com.whiterabbit.wrgemployees.remote.response.EmployeeResponse
import com.whiterabbit.wrgemployees.utils.ListPaddingDecoration
import com.whiterabbit.wrgemployees.utils.Utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EmployeeListFragment : Fragment() {

    private var _binding: FragmentEmployeeListBinding? = null
    private var adapter: EmployeeListAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: ListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEmployeeListBinding.inflate(inflater, container, false)
        setupObservables()

        setHasOptionsMenu(true) // It's important here

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservables() {
        viewModel.categoryList.asLiveData().observe(viewLifecycleOwner) {
            adapter = EmployeeListAdapter(
                requireContext(),
                this::onRecyclerMainItemClicked, it
            )
            binding.rvEmployeeList.layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL, false
            )
            binding.rvEmployeeList.addItemDecoration(
                ListPaddingDecoration(
                    requireContext(),
                    5,
                    0
                )
            )
            binding.rvEmployeeList.adapter = adapter
        }

    }

    private fun onRecyclerMainItemClicked(employee: EmployeeResponse?) {
        employee?.let {
            openDetailPage(it)
        }
    }

    private fun openDetailPage(
        employee: EmployeeResponse
    ) {
        safeNavigate(
            EmployeeListFragmentDirections.actionEmployeeListToEmployeeDetails(employee)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();

        inflater.inflate(R.menu.main_menu, menu)


        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView

        search.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)

        searchView?.isSubmitButtonEnabled = true
//        searchView?.setOnQueryTextListener(this)

        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query != null) {
                    searchDatabase(query)
                }
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query != null) {
                    searchDatabase(query)
                }
                return true
            }
        })

    }


    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        viewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {


                adapter = EmployeeListAdapter(
                    requireContext(),
                    this::onRecyclerMainItemClicked, it as List<EmployeeResponse>
                )


                adapter!!.setData(it)
            }
        })
    }
}