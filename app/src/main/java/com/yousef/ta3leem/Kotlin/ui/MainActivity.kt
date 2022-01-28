package com.yousef.ta3leem.Kotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.yousef.ta3leem.Kotlin.Model.ChatUser
import com.yousef.ta3leem.Kotlin.ui.Login.LoginFragmentDirections
import com.yousef.ta3leem.R
import io.getstream.chat.android.client.ChatClient

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val client = ChatClient.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app)

        navController = findNavController(R.id.navHostFragment)

        if (navController.currentDestination?.label.toString().contains("login")) {
            val currentUser = client.getCurrentUser()
            if (currentUser != null) {
                val user = ChatUser(currentUser.name, currentUser.id)
                val action = LoginFragmentDirections.actionLoginFragmentToChannelFragment(user)
                navController.navigate(action)
            }
        }

    }
}
