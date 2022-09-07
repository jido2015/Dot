package com.olajide.dot.product.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.olajide.dot.databinding.FragmentDetailsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

class MakeupDetailFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val args :MakeupDetailFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productName.text = args.product.name
        binding.productDesc.text = args.product.description
        (args.product.price_sign+  args.product.price).also { binding.price.text = it }
        Glide
            .with(this)
            .load(args.product.image_link)
            .apply(RequestOptions().override(ViewGroup.LayoutParams.MATCH_PARENT, 100))
            .centerCrop()
            .into(binding.productImg)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}