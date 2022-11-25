package edu.cs371m.reddit.ui.calendars

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import edu.cs371m.reddit.databinding.FragmentRvBinding
import edu.cs371m.reddit.ui.MainViewModel

class Calendars : Fragment() {
    // XXX initialize viewModel
    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentRvBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    lateinit var adapter : CalendarListAdapter

    companion object {
        fun newInstance(): Calendars {
            return Calendars()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //viewModel.setTitlePick()
        //viewModel.setHomeFrag(false)
        Log.d(null,"in subreddit")
        //viewModel.netSubreddits()
        _binding = FragmentRvBinding.inflate(inflater, container, false)
        adapter = CalendarListAdapter(viewModel, this.requireActivity())
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            //viewModel.netSubreddits()
        }
        viewModel.fetchDone.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = false
        }
        return binding.root
    }

    // XXX Write me, onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(javaClass.simpleName, "onViewCreated")
        // XXX Write me

        Log.d(null, "in home fragment")
        //adapter.submitList(viewModel.ge)
        /*viewModel.observeSubs().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }*/
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}