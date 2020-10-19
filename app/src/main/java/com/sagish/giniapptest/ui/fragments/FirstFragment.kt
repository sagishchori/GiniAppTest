package com.sagish.giniapptest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sagish.giniapptest.R
import com.sagish.giniapptest.adapters.NumberGridAdapter
import com.sagish.giniapptest.databinding.FragmentFirstBinding
import com.sagish.giniapptest.models.Number
import com.sagish.giniapptest.ui.viewmodels.MainViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()
    private lateinit var firstBinding : FragmentFirstBinding
    private lateinit var gridAdapter : NumberGridAdapter

    companion object {
        fun newInstance(args: Bundle?) : FirstFragment {
            val frag = FirstFragment()
            frag.arguments = args
            return frag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        firstBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)
        // Inflate the layout for this fragment
        return firstBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.intArray.observe(requireActivity(), object : Observer<ArrayList<Number>> {

            override fun onChanged(t: ArrayList<Number>?) {
                gridAdapter.setData(t)
            }
        })

        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        firstBinding.recyclerView.layoutManager = layoutManager

        gridAdapter = NumberGridAdapter(arrayListOf(), requireContext())
        firstBinding.recyclerView.adapter = gridAdapter

        viewModel.getIntArrayFromServer()
    }
}