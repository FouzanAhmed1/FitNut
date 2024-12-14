package com.example.fitnut.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnut.R
import com.example.fitnut.models.Meal

class MealAdapter(private val meals: List<Meal>) :
    RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        holder.foodName.text = meal.food_name // Binds food_name to the TextView
        holder.calories.text = "Calories: ${meal.calories}" // Binds calories to the TextView
    }

    override fun getItemCount(): Int = meals.size

    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodName: TextView = itemView.findViewById(R.id.foodName) // Binds foodName TextView
        val calories: TextView = itemView.findViewById(R.id.calories) // Binds calories TextView
    }
}
