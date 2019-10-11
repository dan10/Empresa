package com.ioasys.empresas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputEditText
import com.ioasys.empresas.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var model: LoginViewModel
    private lateinit var progressBar: ProgressBar

    private val acessToken: String = "access-token"
    private val client: String = "client"
    private val uid: String = "uid"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailTextInput = findViewById<TextInputEditText>(R.id.tie_email)
        val passwordTextInput = findViewById<TextInputEditText>(R.id.tie_password)

        progressBar = findViewById(R.id.pb_login)
        model = ViewModelProviders.of(this)[LoginViewModel::class.java]

        findViewById<Button>(R.id.bt_login).apply {
            setOnClickListener {
                model.email = emailTextInput.text.toString()
                model.password = passwordTextInput.text.toString()
                model.isLoading.value = true
                model.attemptAuthencantion()
            }
        }

        model.isLoading.observe(this, Observer<Boolean> { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else
                progressBar.visibility = View.GONE
        })

        model.map.observe(this, Observer<Map<String, String>> {
            Log.d("Test", model.map.value.toString())

            if (model.map.value!!.size > 2) {
                kotlin.run {
                    val startMainActivityIntent = Intent(this, MainActivity::class.java)
                    startMainActivityIntent.putExtra(acessToken, model.map.value!![acessToken])
                    startMainActivityIntent.putExtra(client, model.map.value!![client])
                    startMainActivityIntent.putExtra(uid, model.map.value!![uid])
                    startActivity(startMainActivityIntent)
                }
            }
        })
    }
}
