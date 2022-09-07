package com.olajide.dot.product.presentation.makeup_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.olajide.dot.databinding.FragmentListBinding
import com.olajide.dot.extension.collectLatestLifecycleFlow
import com.olajide.dot.product.data.model.ProductItem
import com.olajide.dot.network.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class MakeupListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val viewModel: ProductViewModel by activityViewModels()
    private lateinit var adapter: ListAdapter
    private var products = mutableListOf<ProductItem>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListAdapter(products, requireContext())

        //Attach adapter to RecyclerView
        binding.recycler.adapter = adapter

        //Get Latest u
        viewModel.getProductList()
        handleProductResponse()

    }

    //Get Latest update when APi response changes
    private fun handleProductResponse() {

        if(products.size < 1){
            binding.loading.visibility = View.VISIBLE
        }
        collectLatestLifecycleFlow(viewModel.state) { networkResult ->
            when (networkResult) {
                is NetworkResult.Loading -> {
                    Timber.d("Loading.....")
                }
                is NetworkResult.Failure -> {
                    Timber.e("Error Message :a${networkResult.errorText}")

                    binding.loading.visibility = View.GONE
                    Toast.makeText(context, networkResult.errorText, Toast.LENGTH_LONG).show()
                }
                is NetworkResult.Success -> {

                    binding.loading.visibility = View.GONE
                    products.clear()
                    networkResult.data?.let { list ->
                        Timber.tag("ProductSize").d(list.size.toString())

                        list.forEachIndexed { _, product ->
                            Timber.tag("LogBrands").d(product.brand)
                            products.add(product)

                        }
                        adapter.notifyDataSetChanged()
                    }
                }
                is NetworkResult.Exception -> {
                    Timber.e("failure on getting data${networkResult.errorText}")
                    Toast.makeText(context, networkResult.errorText, Toast.LENGTH_LONG).show()
                    binding.loading.visibility = View.GONE
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}