package com.mzarubin.taskscheduler.ui.initialization.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.mzarubin.taskscheduler.databinding.ItemSignUpBinding
import com.mzarubin.taskscheduler.datamodel.SignUpFields

class SignUpAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemSignUpBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SignUpViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SignUpViewHolder).bind(SignUpFields.values()[position])
    }

    override fun getItemCount(): Int = SignUpFields.values().size

    inner class SignUpViewHolder(
        private val binding: ItemSignUpBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(field: SignUpFields) {
            binding.headerField.text = context.getString(field.headerId)
            field.inputType?.let { binding.inputField.inputType = it }
            binding.inputField.addTextChangedListener {
                field.input = it.toString()
            }
        }
    }
}