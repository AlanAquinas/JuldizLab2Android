package com.example.multiscreenapp.fragment;

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.multiscreenapp.adapter.CelebrityAdapter
import com.example.multiscreenapp.databinding.FragmentItemListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.navigation.fragment.navArgs
import com.example.multiscreenapp.R
import com.example.multiscreenapp.model.Celebrity


/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!
    private var adapter: CelebrityAdapter? = null
    private var fullList: ArrayList<Celebrity>? = null
    private val args by navArgs<ItemFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CelebrityAdapter()
        binding.recyclerView.adapter = adapter

        fetchData()
//        binding.editText.addTextChangedListener { editable ->
//            val searchQuery = editable.toString().trim()
//            filterList(searchQuery)
//        }
        binding.sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.sort_by_net_worth -> {
                    adapter!!.sortByNetWorth()
                }
                R.id.sort_by_age -> {
                    adapter!!.sortByAge()
                }
            }
        }
    }

    private fun fetchData() {
        val client = ApiClient.instance
        val response = client.fetchCelebrityList(args.searchText)

        response.enqueue(object : Callback<ArrayList<Celebrity>> {
            override fun onResponse(
                call: Call<ArrayList<Celebrity>>,
                response: Response<ArrayList<Celebrity>>
            ) {
                if (response.isSuccessful) {
                    println(response.body())
                    response.body()?.let {
                        val celebrityList = it
                        fullList = celebrityList
                        println(celebrityList)
                        adapter?.setData(celebrityList)
                    }
                } else {
                    println("API call failed with error code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<Celebrity>>, t: Throwable) {
                println("API call failed: ${t.message}")
            }
        })
    }


//    private fun filterList(searchQuery: String) {
//        fullList?.let { fullList ->
//            val filteredList = if (searchQuery.isEmpty()) {
//                fullList
//            } else {
//                fullList.filter { celebrity ->
//                    celebrity.name.contains(searchQuery, ignoreCase = true)
//                }
//            }
//            adapter?.setData(ArrayList(filteredList))
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
