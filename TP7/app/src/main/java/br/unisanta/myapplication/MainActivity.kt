package br.unisanta.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.firebase.ui.auth.FirebaseAuthUI
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val authUI = FirebaseAuthUI.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!authUI.isSignedIn()) {
            voltarParaLogin()
            return
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val user = authUI.getCurrentUser()
        val nome = user?.displayName?.takeIf { it.isNotBlank() } ?: user?.email
        findViewById<TextView>(R.id.tvBoasVindas).text = getString(R.string.boas_vindas, nome)
        findViewById<TextView>(R.id.tvEmail).text = user?.email

        findViewById<Button>(R.id.btnSair).setOnClickListener {
            lifecycleScope.launch {
                authUI.signOut(applicationContext)
                voltarParaLogin()
            }
        }
    }

    private fun voltarParaLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
