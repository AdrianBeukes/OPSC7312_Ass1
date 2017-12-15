/**
 * Created by Adrian - 15002426 on 2017/08/28.
 * OPSC Assignment 1
 * Task is do create a gps tracker,and also make use of the google map api to display locations
 * references used
 * https://www.youtube.com/watch?v=dr0zEmuDuIk
 */
package adrianbeukes.opsc7312_ass1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Question2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);
    }


    //switch activities
    public void ShowMap(View view)
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void clickSMS(View view)
    {
        Intent intent = new Intent(this, sms.class);
        startActivity(intent);
    }
}
