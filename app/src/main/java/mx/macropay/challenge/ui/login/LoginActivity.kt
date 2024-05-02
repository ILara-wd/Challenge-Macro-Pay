package mx.macropay.challenge.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import mx.macropay.challenge.R
import mx.macropay.challenge.data.model.network.Resource
import mx.macropay.challenge.databinding.ActivityLoginBinding
import mx.macropay.challenge.ui.HomeActivity

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val authViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setObserver()
        setView()
    }

    private fun setView() {
        with(binding) {
            tvLogInSignUp.setOnClickListener {
                when (it.contentDescription) {
                    "Login" -> {
                        btnLogin.text = getString(R.string.text_btn_signup)
                        btnLogin.contentDescription = getString(R.string.content_description_signup)

                        tvLogInSignUp.contentDescription =
                            getString(R.string.content_description_signup)
                        tvLogInSignUp.text = getString(R.string.text_login)

                        tvFullName.visibility = View.VISIBLE
                        etFullName.visibility = View.VISIBLE
                    }

                    else -> {
                        btnLogin.text = getString(R.string.text_btn_login)
                        btnLogin.contentDescription = getString(R.string.content_description_login)

                        tvLogInSignUp.contentDescription =
                            getString(R.string.content_description_login)
                        tvLogInSignUp.text = getString(R.string.text_create_account)

                        tvFullName.visibility = View.GONE
                        etFullName.visibility = View.GONE

                    }
                }


            }
            btnLogin.setOnClickListener {
                when (it.contentDescription) {
                    "Login" -> {
                        validateLogin()
                    }

                    else -> {
                        validateSignUp()
                    }
                }
            }
        }
    }

    private fun validateLogin() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        authViewModel.loginUser(email, password)
    }

    private fun validateSignUp() {
        val name = binding.etFullName.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        authViewModel.signupUser(name, email, password)
    }


    private fun setObserver() {
        authViewModel.currentUser?.let {
            sendHome()
        }
        authViewModel.loginFlow.observe(this) {
            viewObserver(it)
        }

        authViewModel.signupFlow.observe(this) {
            viewObserver(it)
        }
        authViewModel.messageError?.observe(this) {
            it.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        }
    }

    private fun sendHome() {
        finish()
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun viewObserver(resource: Resource<FirebaseUser>?) {
        when (resource) {
            is Resource.Failure -> {
                binding.pbLoading.visibility = View.GONE
                Toast.makeText(this, resource.exception.message.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

            is Resource.Loading -> {
                binding.pbLoading.visibility = View.VISIBLE
            }

            is Resource.Success -> {
                binding.pbLoading.visibility = View.GONE
                sendHome()
            }

            else -> Unit
        }
    }
}
