package at.spengergasse.kai17521.lab02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SecondScreenActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
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
}
