package com.example.kitchenrecipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import retrofit2.Call
import retrofit2.Response
import androidx.core.app.NotificationCompat.getCategory
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNCHECKED_CAST")
class categoryFragment : Fragment() {

    lateinit var adapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCategory(view)
    }

    private fun getCategory(view: View) {

        val progressbar = requireActivity().findViewById<ProgressBar>(R.id.indeterminateBar)
        progressbar.isVisible = true
        val cat: Call<CategoryResponse> = Category_interface.Interface.getCategory(1)
        cat.enqueue(object : retrofit2.Callback<CategoryResponse> {

            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                progressbar.isVisible= false
                Log.e("TAG", "onResponse: " + response.raw().toString())
                val category = response.body()

                if (category != null) {
                    Log.d("PracticeCat", cat.toString())
                    adapter = CategoryAdapter(requireContext(), category.app as List<CategoryItem>)

                    val catitem = view.findViewById<RecyclerView>(R.id.categoryList)
                    catitem.adapter = adapter
                    catitem.layoutManager = GridLayoutManager(requireContext(),2)
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                Log.d("PracticeCat", "Error in Fetching Data", t)
                progressbar.isVisible= false
            }
        })
    }
}



