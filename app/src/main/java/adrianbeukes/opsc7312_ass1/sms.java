/**
 * Created by Adrian - 15002426 on 2017/08/28.
 * OPSC Assignment 1
 * Task is do create a gps tracker,and also make use of the google map api to display locations
 * references used
 * https://www.youtube.com/watch?v=dr0zEmuDuIk
 */
package adrianbeukes.opsc7312_ass1;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sms extends AppCompatActivity
{
    //declarations
    Button bSend;
    String etNumber = "0742252014"; //personal number, for testing
    EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        //binds id
        bSend = (Button) findViewById(R.id.btnSend);
        etMessage = (EditText) findViewById(R.id.edtxtMessage);


        //check permissions, else asks
        if (ContextCompat.checkSelfPermission(sms.this
                , Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(sms.this,
                    android.Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(sms.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            } else {
                ActivityCompat.requestPermissions(sms.this,
                        new String[]{android.Manifest.permission.SEND_SMS}, 1);
            }
        } else {
            //do nothing
        }

        //when send is pressed
        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sms = etMessage.getText().toString();

                try
                {
                    //sets up manager and settinggs
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(etNumber,null, sms,null, null);
                    Toast.makeText(sms.this, "Sent", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(sms.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //permission requests
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode)
        {
            case 1:
            {
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(sms.this,
                            Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
                    {
                        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(this, "No Permission granted", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}

