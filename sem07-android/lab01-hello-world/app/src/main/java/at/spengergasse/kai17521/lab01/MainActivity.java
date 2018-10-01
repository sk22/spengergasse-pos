package at.spengergasse.kai17521.lab01;

import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button myButton1 = findViewById(R.id.button1);
        final TextView textView = findViewById(R.id.text);

        myButton1.setText("Click me for count");
        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                textView.setText("Count: " + counter);
            }
        });

        final Button colorButton = findViewById(R.id.colorButton);
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorButton.setBackgroundColor(Color.rgb(random255(), random255(), random255()));
            }
        });

        final EditText editText = findViewById(R.id.textToSpeak);
        final TextToSpeech tts = new TextToSpeech(getApplicationContext(),
            new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                }
            });
        tts.setLanguage(Locale.US);

        final Button speakButton = findViewById(R.id.speakButton);
        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(editText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);

            }
        });
    }

    private int random255() {
        return (int) Math.floor(Math.random() * 255);
    }
}
