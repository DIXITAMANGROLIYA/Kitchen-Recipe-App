package com.example.kitchenrecipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response

@Suppress("NAME_SHADOWING")
class latestFragment : Fragment() {

    lateinit var adapter: RecipeAdapter
    lateinit var databaseHelper: DatabaseHelper

    private fun getRecipe(view: View) {
        val recipe: Call<RecipeResponse> = Recipe_Data.recipeInterface.getRecipe(1)
        recipe.enqueue(object : retrofit2.Callback<RecipeResponse> {

            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ) {
                Log.e("TAG", "onResponse: " + response.raw().toString())
                val recipe = response.body()

                if (recipe != null) {
                   // Log.d("PracticeApp", recipe.toString())
                    adapter =
                        RecipeAdapter(requireContext(), recipe.app, object : RecipeAdapter.getData  {
                            override fun onLike(position: Int) {
                                val name: String = recipe.app?.get(position)?.recipeName.toString()
                                val image: String = recipe.app?.get(position)?.image.toString()
                                val ingredients: String = recipe.app?.get(position)?.ingredient.toString()
                                val method: String = recipe.app?.get(position)?.method.toString()
                                val recipeID: Int = recipe.app?.get(position)?.recipeID!!.toInt()

                                val appItem =
                                    AppItem(0, name, ingredients, recipeID, method, image)

                                //insert into database
                               databaseHelper.UserDao()!!.insert(appItem)
                            }

                        })

                    val kitchen = view.findViewById<RecyclerView>(R.id.recipeList)
                    kitchen?.adapter = adapter
                    kitchen?.layoutManager = LinearLayoutManager(requireContext())
                }
            }

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                Log.d("PracticeApp", "Error in Fetching Data", t)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_latest, container, false)
        databaseHelper = DatabaseHelper.getInstance(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val l = getRecipe(view)

         val progressbar = requireActivity().findViewById<ProgressBar>(R.id.indeterminateBar)
        if (l == null) {
            progressbar.isShown
        } else {
            progressbar.isGone
        }
    }


}