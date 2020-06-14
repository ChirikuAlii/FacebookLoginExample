package info.chirikualii.facebookloginexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var callbackManager : CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
         callbackManager = CallbackManager.Factory.create()

        btn_login.registerCallback(callbackManager,object : FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult?) {
                Log.d(MainActivity::class.java.simpleName,"success")
                tv_result.text=(result?.accessToken?.userId)
                iv_profile.profileId = result?.accessToken?.userId
            }

            override fun onCancel() {

            }

            override fun onError(error: FacebookException?) {
                Log.d(MainActivity::class.java.simpleName,"error ${error?.message}")
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(MainActivity::class.java.simpleName,"activity result")
        callbackManager.onActivityResult(requestCode,resultCode,data)
    }
}
