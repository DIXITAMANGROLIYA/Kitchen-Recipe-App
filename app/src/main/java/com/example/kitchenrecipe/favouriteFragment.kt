package com.example.kitchenrecipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchenrecipe.RecipeAdapter.getData

class favouriteFragment : Fragment() {

    lateinit var adapter: RecipeAdapter
    lateinit var databaseHelper: DatabaseHelper
    private  var listofdata: List<AppItem>? = null
    //lateinit var binding: InputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        databaseHelper =DatabaseHelper.getInstance(requireContext())
        listofdata = databaseHelper.UserDao()?.all


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fav = view.findViewById<RecyclerView>(R.id.likeList)
        fav.layoutManager = LinearLayoutManager(requireContext())

        Log.d("Practicefav", listofdata.toString())

        adapter = RecipeAdapter(requireContext(),listofdata, object : getData{
            override fun onLike(position: Int) {
                val name: String = listofdata?.get(position)?.recipeName.toString()
                val image: String = listofdata?.get(position)?.image.toString()
                val ingredients: String =  listofdata?.get(position)?.ingredient.toString()
                val method: String =  listofdata?.get(position)?.method.toString()
                val recipeID: Int =  listofdata?.get(position)?.recipeID!!.toInt()


                val appItem =
                    AppItem(0, name, ingredients, recipeID, method, image)

                //insert into database
                databaseHelper.UserDao()!!.insert(appItem)
            }

        })
      fav.adapter=adapter

    }

}