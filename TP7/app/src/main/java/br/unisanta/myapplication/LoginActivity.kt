package br.unisanta.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.FirebaseAuthUI
import com.firebase.ui.auth.configuration.PasswordRule
import com.firebase.ui.auth.configuration.auth_provider.AuthProvider
import com.firebase.ui.auth.configuration.authUIConfiguration

class LoginActivity : AppCompatActivity() {

    private val authUI = FirebaseAuthUI.getInstance()

    private val authLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result.resultCode == RESULT_OK && authUI.isSignedIn()) {
            abrirMain()
        } else {
            Toast.makeText(this, R.string.login_cancelado, Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (authUI.isSignedIn()) {
            abrirMain()
            return
        }

        if (savedInstanceState == null) {
            iniciarLogin()
        }
    }

    private fun iniciarLogin() {
        val configuration = authUIConfiguration {
            context = applicationContext
            providers {
                provider(
                    AuthProvider.Email(
                        isDisplayNameRequired = true,
                        isNewAccountsAllowed = true,
                        emailLinkActionCodeSettings = null,
                        passwordValidationRules = listOf(PasswordRule.MinimumLength(6)),
                    )
                )
            }
        }

        val controller = authUI.createAuthFlow(configuration)
        authLauncher.launch(controller.createIntent(this))
    }

    private fun abrirMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
