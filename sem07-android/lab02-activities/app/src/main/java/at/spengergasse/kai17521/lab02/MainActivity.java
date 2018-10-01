package at.spengergasse.kai17521.lab02;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        if (Integer.parseInt(age.getText().toString()) < 0) {
            errorMessage.setText("Age must be at least 0.");
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
}
