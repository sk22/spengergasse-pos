package at.spengergasse.kai17521.lab03sport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToPulse(View view) {
        startActivity(new Intent(view.getContext(), PulseActivity.class));
    }

    public void goToBmi(View view) {
        startActivity(new Intent(view.getContext(), BmiActivity.class));
    }
}
