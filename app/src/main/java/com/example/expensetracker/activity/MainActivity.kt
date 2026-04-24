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
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.expensetracker.viewmodel.Userincome_vmodel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private  val userviewmodel: Userincome_vmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){

            if (ContextCompat.checkSelfPermission(this,Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {

                ActivityCompat.requestPermissions(this,

                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),101)
            }
        }

        notificationchannercreate(this)

       userviewmodel.let { it->

           it.getuseramount()

           it.get_total_expense()

           it.get_expense_amount.observe(this){expense->

               it.get_amount_livedata.observe(this){amount->

                  if (expense.toInt()>amount.toInt()){

                      shownotification("WARNING","your monthly income almost finished")

                  }

               }

           }

       }

    }


    @SuppressLint("MissingPermission")
    private fun shownotification(warning: String, message: String) {

        val builder = NotificationCompat.Builder(this, "Notify")
            .setSmallIcon(R.drawable.exclamation_triangle_icon)
            .setContentTitle(warning)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val manager = NotificationManagerCompat.from(this)
        manager.notify(1, builder.build())
    }




    private fun notificationchannercreate(context: MainActivity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val title = "Notification Alert"

            val channel = NotificationChannel("Notify", title,
                NotificationManager.IMPORTANCE_HIGH)

           channel.description = "description here"

            val manager = context.getSystemService(NotificationManager::class.java)

            manager.createNotificationChannel(channel)

    }

    }



}


