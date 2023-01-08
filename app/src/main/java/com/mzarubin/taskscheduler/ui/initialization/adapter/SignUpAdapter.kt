package com.mzarubin.taskscheduler.ui.initialization.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.mzarubin.taskscheduler.databinding.ItemSignUpBinding
import com.mzarubin.taskscheduler.datamodel.SignUpFieldDataModel
import com.mzarubin.taskscheduler.datamodel.SignUpFields

class SignUpAdapter(private val signUpFields: List<SignUpFieldDataModel>) :
    RecyclerView.Adapter<SignUpAdapter.SignUpViewHolder>() {
    fun getSignUpFields() = signUpFields

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SignUpViewHolder {
        val binding = ItemSignUpBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SignUpViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: SignUpViewHolder, position: Int) {
        holder.bind(signUpFields[position])
    }

    override fun getItemCount(): Int = SignUpFields.values().size

    inner class SignUpViewHolder(
        private val binding: ItemSignUpBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(field: SignUpFieldDataModel) {
            binding.headerField.text = context.getString(field.info.headerId)
            field.info.inputType?.let { binding.inputField.inputType = it }
            binding.inputField.addTextChangedListener {
                field.data = it.toString()
            }
        }
    }
}