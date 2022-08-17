package com.olajide.dot.list.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.olajide.dot.databinding.ListItemBinding
import com.olajide.dot.list.data.model.ProductItem


class ListAdapter(private val prod: List<ProductItem>, val context: Context) : RecyclerView.Adapter<ListAdapter.ViewHolder>()  {

    inner class ViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(listener: View.OnClickListener, prod: ProductItem) {

            binding.trnxName.text = prod.name
            binding.trnxAmt.text =  prod.price_sign + prod.price
            binding.brand.text = prod.brand
            val url: String = prod.image_link

            Glide
                .with(context)
                .load(url)
                .apply(RequestOptions().override(MATCH_PARENT, 100))
                .centerCrop()
                .into(binding.imageView)

            binding.root.setOnClickListener(listener)
        }
        }
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = prod[position]
        holder.bind(createOnClickListener( currentItem), currentItem)

    }

    private fun createOnClickListener( product: ProductItem): View.OnClickListener {
        return View.OnClickListener {

            val directions =
                ListFragmentDirections.actionFirstFragmentToSecondFragment(product)
            it.findNavController().navigate(directions)
        }
    }
    override fun getItemCount(): Int {
        return prod.size
    }
}