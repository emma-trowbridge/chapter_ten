package com.bignerdranch.android.chapter_ten

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bignerdranch.android.chapter_ten.databinding.ListItemCrimeBinding
import com.bignerdranch.android.chapter_ten.databinding.ListItemCrimePoliceBinding

//CrimeHolder for regular crimes
class CrimeHolder(
    private val binding:ListItemCrimeBinding
): RecyclerView.ViewHolder(binding.root) {

    //binds crime to regular layout and sets up onclick listener
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    //new CrimePoliceHolder for crimes requiring police
    class CrimePoliceHolder(
        private val binding: ListItemCrimePoliceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        //binds crime to police specific layout
        fun bind(crime: Crime) {
            binding.crimeTitle.text = crime.title
            binding.crimeDate.text = crime.date.toString()

            //"contact police" button clicker
            binding.contactPoliceButton.setOnClickListener{
                Toast.makeText(
                    binding.root.context,
                    "Contacting police for ${crime.title}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    //adapter for displaying list of crime items in RecyclerView
    class CrimeListAdapter(
        private val crimes: List<Crime>
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        //companion object defining type constants
        companion object {
            private const val VIEW_TYPE_NORMAL = 0
            private const val VIEW_TYPE_POLICE = 1
        }

        //determines which view type to use based on requiresPolice property
        override fun getItemViewType(position: Int): Int {
            return if (crimes[position].requiresPolice) {
                VIEW_TYPE_POLICE
            } else {
                VIEW_TYPE_NORMAL
            }
        }

        //inflates appropriate ViewHolder based on view type
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
        ): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(parent.context)

            return when (viewType) {
                VIEW_TYPE_POLICE -> {
                    // Inflate police-specific layout for serious crimes
                    val binding = ListItemCrimePoliceBinding.inflate(inflater, parent, false)
                    CrimePoliceHolder(binding) //return custom CrimePoliceHolder
                }

                else -> {
                    //inflate regular layout for non-serious crimes
                    val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
                    CrimeHolder(binding) //return custom CrimeHolder
                }
            }
        }

        //binds appropriate ViewHolder with data from Crime List
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val crime = crimes[position]
            when (holder) {
                is CrimeHolder -> holder.bind(crime) //binds regular crime
                is CrimePoliceHolder -> holder.bind(crime) //binds crime that requires police
            }
        }

        //returns number of items in Crime List
        override fun getItemCount() = crimes.size
    }
}