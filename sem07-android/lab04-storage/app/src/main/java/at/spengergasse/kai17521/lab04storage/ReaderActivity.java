package at.spengergasse.kai17521.lab04storage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static android.os.Environment.getExternalStorageDirectory;

public class ReaderActivity extends AppCompatActivity {
  private SharedPreferences prefs;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reader);
    prefs = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);

    final EditText internalView = findViewById(R.id.internal_storage);
    final EditText externalView = findViewById(R.id.external_storage);
    final EditText prefsView = findViewById(R.id.preferences_data);

    try {
      String internalString = readFile(openFileInput(getString(R.string.filename)));
      internalView.setText(internalString);
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      File f = new File(getExternalStorageDirectory(), getString(R.string.filename));
      String externalString = readFile(new FileInputStream(f));
      externalView.setText(externalString);
    } catch (IOException e) {
      e.printStackTrace();
    }

    prefsView.setText(prefs.getString("text", null));
  }

  private String readFile(FileInputStream fis) throws IOException {
    StringBuilder sb = new StringBuilder();
    InputStreamReader isr = new InputStreamReader(fis);
    BufferedReader bufferedReader = new BufferedReader(isr);
    String line = bufferedReader.readLine();
    while (line != null) {
      sb.append(line);
      line = bufferedReader.readLine();
      if (line != null) sb.append('\n');
    }
    return sb.toString();
  }

}
