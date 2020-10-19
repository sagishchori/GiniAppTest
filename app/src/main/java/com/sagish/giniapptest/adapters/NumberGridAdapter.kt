package com.sagish.giniapptest.adapters

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sagish.giniapptest.models.Number
import com.sagish.giniapptest.R
import com.sagish.giniapptest.databinding.GridItemBinding

class NumberGridAdapter(private var array: ArrayList<Number>?, context: Context) : RecyclerView.Adapter<NumberGridAdapter.IntHolder>() {

    private val dm: DisplayMetrics = context.resources.displayMetrics

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntHolder {
        val binding = DataBindingUtil.inflate<GridItemBinding>(LayoutInflater.from(parent.context),
            R.layout.grid_item,
            parent,
            false)

        return IntHolder(binding, dm)
    }

    override fun onBindViewHolder(holder: IntHolder, position: Int) {
        holder.bind(array?.get(position))
    }

    override fun getItemCount(): Int {
        return array!!.size
    }

    fun setData(array: ArrayList<Number>?) {
        this.array = array
        notifyDataSetChanged()
    }

    class IntHolder(private val binding: GridItemBinding, val dm: DisplayMetrics) : RecyclerView.ViewHolder(binding.root) {

        fun bind(number : Number?) {
            binding.number = number?.number.toString()

            if (number?.hasCouple!!) {
                binding.intContainer.layoutParams.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    150F,
                    dm).toInt()
                binding.intContainer.setBackgroundColor(binding.intContainer.resources.getColor(R.color.red))
            } else {
                binding.intContainer.layoutParams.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    100F,
                    dm).toInt()
                binding.intContainer.setBackgroundColor(binding.intContainer.resources.getColor(R.color.orange))
            }
        }
    }
}