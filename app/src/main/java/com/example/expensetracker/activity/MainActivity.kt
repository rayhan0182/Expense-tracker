package com.example.expensetracker.activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.expensetracker.R
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import android.widget.Toast
import java.security.Permission

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


         user_request_permissions()


    }

    private fun user_request_permissions() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)

                != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.
                requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS)
                    ,101)
            }
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)

        if (requestCode==0){

            if (permissions.isNotEmpty()&&grantResults[0]== PackageManager.PERMISSION_GRANTED){


                Toast.makeText(this,"yes,permission ok", Toast.LENGTH_LONG).show()

            }else{

                Toast.makeText(this,"permission denied", Toast.LENGTH_LONG).show()

            }
        }
    }
}

