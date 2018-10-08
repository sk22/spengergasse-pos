package at.spengergasse.kai17521.lab02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LIFE", "Create SecondScreenActivity");

        setContentView(R.layout.activity_second);
        final TextView age = findViewById(R.id.ageView);
        final TextView name = findViewById(R.id.nameView);

        Bundle params = getIntent().getExtras();
        if (params == null) return;

        String ageValue = params.getString("age");
        String nameValue = params.getString("name");

        if (ageValue != null && nameValue != null) {
            age.setText("Age: " + ageValue);
            name.setText(nameValue);
        }
    }

    public void close(View view) {
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LIFE", "Start SecondScreenActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LIFE", "Resume SecondScreenActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LIFE", "Stop SecondScreenActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LIFE","Pause SecondScreenActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LIFE", "Destroy SecondScreenActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LIFE", "Restart SecondScreenActivity");
    }
}
