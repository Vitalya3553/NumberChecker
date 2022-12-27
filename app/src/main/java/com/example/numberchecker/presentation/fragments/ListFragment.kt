package com.example.numberchecker.presentation.fragments

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.numberchecker.R
import com.example.numberchecker.presentation.MainActivity
import com.example.numberchecker.presentation.NumberViewModel
import com.example.numberchecker.presentation.adapter.NumberListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var adapter: NumberListAdapter
    private lateinit var viewModel : ViewModel

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val list : RecyclerView = view.findViewById(R.id.numberList)
        val button: Button = view.findViewById(R.id.getContactsButton)

        viewModel = ViewModelProvider(this)[NumberViewModel::class.java]
        list.layoutManager = LinearLayoutManager(context)

        setSavedDateInList()

        button.setOnClickListener{
            if(checkContactPermission()){
                lifecycleScope.launch(SupervisorJob()){
                    (viewModel as NumberViewModel).reloadContacts()
                }
            }else{
                requestContactPermission()
            }
        }

        collectLatestFlow((viewModel as NumberViewModel).stateFlow){

            adapter = NumberListAdapter(it)
            numberList.adapter = adapter

            adapter.onItemClick = { contact ->
                val bundle = Bundle()
                bundle.putString("number",contact.number)
                (activity as MainActivity).navController.navigate(R.id.action_listFragment_to_indoFromApiFragment,bundle)
            }
        }
    }

    private fun <T> collectLatestFlow(flow: Flow<T>, collect: suspend (T) -> Unit){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                flow.collectLatest(collect)
            }
        }
    }
    private fun checkContactPermission(): Boolean{
        return  context?.let {
                context -> ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.READ_CONTACTS
                )
        } ==  PackageManager.PERMISSION_GRANTED
    }
    private fun setSavedDateInList(){
        lifecycleScope.launch {
            adapter = NumberListAdapter((viewModel as NumberViewModel).getSavedContacts())
            numberList.adapter = adapter
            Log.e("Tag first", "working")

            adapter.onItemClick = {
                Log.e("Tagg", "working")
                val bundle = Bundle()
                bundle.putString("number", it.number)
                (activity as MainActivity).navController.navigate(
                    R.id.action_listFragment_to_indoFromApiFragment,
                    bundle
                )
            }
        }
    }
    private fun requestContactPermission(){
        requestPermissions(arrayOf(android.Manifest.permission.READ_CONTACTS),0)

    }
}

