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

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    //on click methods, switch between activities
    public void Question1A(View view)
    {
        Intent intent = new Intent(this, Question1A.class);
        startActivity(intent);
    }

    public  void Question1B(View view)
    {
        Intent intent = new Intent(this, Question1B.class);
        startActivity(intent);
    }

    public void Question2(View view)
    {
        Intent intent = new Intent(this, Question2.class);
        startActivity(intent);
    }

    public void clickHelp(View view)
    {
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }
}