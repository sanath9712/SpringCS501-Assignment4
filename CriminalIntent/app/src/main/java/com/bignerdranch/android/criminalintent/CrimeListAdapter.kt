package com.bignerdranch.android.criminalintent

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimePoliceBinding

abstract class CrimeHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(crime: Crime)
}


class NormalCrimeHolder(private val binding: ListItemCrimeBinding) : CrimeHolder(binding) {
    override fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!!!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class PoliceCrimeHolder(private val binding: ListItemCrimePoliceBinding) : CrimeHolder(binding) {
    override fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked and this requires Police!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<CrimeHolder>() {
    private val VIEW_TYPE_NORMAL = 0
    private val VIEW_TYPE_POLICE = 1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : CrimeHolder {
        return when (viewType) {
            VIEW_TYPE_POLICE -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemCrimePoliceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                PoliceCrimeHolder(binding)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemCrimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NormalCrimeHolder(binding)
            }
        }


    }
    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = crimes[position]
        holder.bind(crime)


    }

    override fun getItemViewType(position: Int): Int {
        val crime = crimes[position]
        return if (crime.requiresPolice) VIEW_TYPE_POLICE else VIEW_TYPE_NORMAL
    }
    override fun getItemCount() = crimes.size
}

