package com.example.numberchecker.presentation

import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.Window.FEATURE_NO_TITLE
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.numberchecker.R
import com.example.numberchecker.presentation.adapter.NumberListAdapter
import com.example.numberchecker.presentation.receiver.InternetConnectionReceiver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //private lateinit var receiver: InternetConnectionReceiver
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        requestWindowFeature(FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)

        /*receiver = InternetConnectionReceiver()
        IntentFilter(ConnectivityManager.****).also {
            registerReceiver(receiver,it)
        }*/

        navController = Navigation.findNavController(this,R.id.nav_host)

    }
   /* override fun onStop() {

        unregisterReceiver(receiver)
        super.onStop()
    }*/
}