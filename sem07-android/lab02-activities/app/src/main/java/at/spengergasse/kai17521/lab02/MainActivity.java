package at.spengergasse.kai17521.lab02;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText age;
    private EditText name;
    private TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LIFE", "Create MainActivity");
        setContentView(R.layout.activity_main);
        age = findViewById(R.id.age);
        name = findViewById(R.id.name);
        errorMessage = findViewById(R.id.errorMessage);
    }

    public void close(View view) {
        finish();
    }

    public void send(View view) {
        if (name.getText().length() < 3) {
            errorMessage.setText("Name must be at least three letters long.");
            return;
        }

        try {
            if (Integer.parseInt(age.getText().toString()) < 0) {
                errorMessage.setText("Age must be at least 0.");
                return;
            }
        } catch (NumberFormatException nfe) {
            errorMessage.setText("Age is invalid.");
            return;
        }

        final Intent intent = new Intent(
                view.getContext(),
                SecondScreenActivity.class
        );
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("age", age.getText().toString());
        startActivity(intent);
    }

    public void startORF(View view) {
        final Intent intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://orf.at")
        );
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LIFE", "Start MainActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LIFE", "Resume MainActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LIFE", "Stop MainActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LIFE","Pause MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LIFE", "Destroy MainActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LIFE", "Restart MainActivity");
    }
}
