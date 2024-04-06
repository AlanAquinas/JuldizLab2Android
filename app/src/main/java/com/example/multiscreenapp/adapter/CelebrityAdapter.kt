package com.example.multiscreenapp.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.multiscreenapp.databinding.FragmentItemBinding
import com.example.multiscreenapp.model.Celebrity
import java.text.NumberFormat
import java.util.Locale
import java.text.DecimalFormat
import kotlin.math.roundToLong

class CelebrityAdapter() : RecyclerView.Adapter<CelebrityAdapter.ViewHolder>() {

    companion object {
        private const val CELEBRITY_ADAPTER_TAG = "CelebrityAdapter"
    }

    private var celebrityList: ArrayList<Celebrity> = ArrayList()

    fun setData(newList: ArrayList<Celebrity>) {
        val diffCallback = CelebrityDiffCallback(celebrityList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        celebrityList = newList
        diffResult.dispatchUpdatesTo(this)
    }


    class CelebrityDiffCallback(
        private val oldList: List<Celebrity>,
        private val newList: List<Celebrity>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(CELEBRITY_ADAPTER_TAG, "onCreateViewHolder")
        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(CELEBRITY_ADAPTER_TAG, "onBindViewHolder: $position")
        holder.bind(celebrityList[position])
    }

    override fun getItemCount(): Int = celebrityList.size

    inner class ViewHolder(
        private val binding: FragmentItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(celebrity: Celebrity) {
            with(binding) {
                val decimalFormat = DecimalFormat("$ #,###,###")
                celebrityName.text = celebrity.name.split(" ").joinToString(" ") { it.capitalize() }

                worth.text = decimalFormat.format(celebrity.netWorth.toDouble().roundToLong())

                nationality.text = "Nationality: ${celebrity.nationality}"

                age.text = "Age: ${celebrity.age.takeUnless { it == 0 } ?: "undefined"}"

                height.text = "Height: ${celebrity.height.takeUnless { it == 0.0 } ?: "undefined"}"

                occupation1.text = celebrity.occupation?.getOrNull(0)?.replace("_", " ") ?: "-"
                occupation2.text = celebrity.occupation?.getOrNull(1)?.replace("_", " ") ?: "-"
                occupation3.text = celebrity.occupation?.getOrNull(2)?.replace("_", " ") ?: "-"
            }
        }
    }

    fun sortByNetWorth() {
        val sortedList = celebrityList.sortedByDescending { it.netWorth.toDouble() }
        setData(ArrayList(sortedList))
    }
    fun sortByAge() {
        val sortedList = celebrityList.sortedBy { it.age.toInt() }
        setData(ArrayList(sortedList))
    }
}