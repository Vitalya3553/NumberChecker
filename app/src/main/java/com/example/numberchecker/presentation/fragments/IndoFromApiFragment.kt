package com.example.numberchecker.presentation.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.numberchecker.R
import com.example.numberchecker.domain.model.NumberInfo
import com.example.numberchecker.presentation.NumberViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_info_from_api.view.*
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException

@AndroidEntryPoint
class IndoFromApiFragment : Fragment() {
    private lateinit var viewModel : NumberViewModel
    private lateinit var result : NumberInfo

    //private var args: ListFragment by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[NumberViewModel::class.java]
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_from_api, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val layout: ConstraintLayout = view.findViewById(R.id.checkNumberContainer)
        layout.visibility = View.GONE

        val progressBar : ProgressBar = view.findViewById(R.id.checkNumberProgressBar)
        progressBar.visibility = View.VISIBLE

        super.onViewCreated(view, savedInstanceState)

        val number = arguments?.getString("number")
        val validValue = view.findViewById<TextView>(R.id.valid_value)
        val localFormatValue = view.findViewById<TextView>(R.id.localFormat_value)
        val formatValue = view.findViewById<TextView>(R.id.format_value)
        val countryValue = view.findViewById<TextView>(R.id.country_value)
        val locationValue = view.findViewById<TextView>(R.id.location_value)
        val carrier = view.findViewById<TextView>(R.id.carrier_value)
        val lineTypeValue = view.findViewById<TextView>(R.id.lineType_value)
        CoroutineScope(SupervisorJob() + Dispatchers.Main).launch {
            try{
                result = viewModel.checkContact(
                    number.toString()
                )
                Log.e("n = " , number.toString())

                layout.visibility = View.VISIBLE
                progressBar.visibility = View.GONE

                validValue.text = result.valid.toString()
                formatValue.text = result.international_format
                localFormatValue.text = result.local_format
                countryValue.text = result.country_code
                locationValue.text = result.location
                carrier.text = result.carrier
                lineTypeValue.text = result.line_type
            }catch (e: HttpException){
                Log.e("ex"," http")
            }
            catch (e:IOException){
                Log.e("ex"," IO")

            }
        }

    }
}


