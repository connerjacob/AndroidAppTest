package com.example.roomandapi

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService

import com.example.roomandapi.entity.TeamMember
import com.example.roomandapi.ui.theme.RoomAndApiTheme
import com.example.roomandapi.views.MainView
import com.example.roomandapi.views.appNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//           var TeamMemberList: List<TeamMember>
//            val url: String = "http://localhost:7000/team"
//            if (isNetworkConnected()) {
//                doAsync {
//                    TeamMemberList = Request(url).run()
//                    uiThread { longToast("Request performed") }
//                }
//            }else {
//                AlertDialog.Builder(this).setTitle("No Internet Connection")
//                    .setMessage("Please check your internet connection and try again")
//                    .setPositiveButton(android.R.string.ok) { _, _ -> }
//                    .setIcon(android.R.drawable.ic_dialog_alert).show()
//            }
            RoomAndApiTheme {
                appNavController()
                }
            }
        }

        private fun isNetworkConnected(): Boolean {
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager //1
            val networkInfo = connectivityManager.activeNetworkInfo //2
            return networkInfo != null && networkInfo.isConnected //3
        }
    }



@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RoomAndApiTheme {
        Greeting("Android")
    }
}
