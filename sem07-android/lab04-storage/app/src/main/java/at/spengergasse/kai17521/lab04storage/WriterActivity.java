package at.spengergasse.kai17521.lab04storage;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;

import static android.os.Environment.getExternalStorageDirectory;

public class WriterActivity extends AppCompatActivity {
  private final String TAG = "Writer";
  private EditText textInput;
  private SharedPreferences prefs;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_writer);
    prefs = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
    textInput = findViewById(R.id.text_input);
  }

  public void onClickReader(View view) {
    startActivity(new Intent(this, ReaderActivity.class));
  }

  public void onClickSaveInternal(View view) {
    String filename = getString(R.string.filename);
    String text = textInput.getText().toString();
    FileOutputStream outputStream;
    Log.d(TAG, "Saving '" + text + "'");

    try {
      outputStream = openFileOutput(filename, MODE_PRIVATE);
      outputStream.write(text.getBytes());
      outputStream.close();
      Log.d(TAG, "Saved to internal storage. Output stream closed");
      Snackbar.make(view, R.string.saved_to_internal, Snackbar.LENGTH_SHORT).show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void onClickSaveExternal(View view) {
    final int writePermission = ContextCompat
      .checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    final int readPermission = ContextCompat
      .checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

    if (writePermission != PackageManager.PERMISSION_GRANTED
      || readPermission != PackageManager.PERMISSION_GRANTED) {
      final int PERMISSIONS_EXTERNAL_STORAGE = 1;
      ActivityCompat.requestPermissions(this, new String[] {
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
      }, PERMISSIONS_EXTERNAL_STORAGE);
    }

    String text = textInput.getText().toString();
    FileOutputStream outputStream;
    Log.d(TAG, "Saving '" + text + "'");

    try {
      File file = new File(getExternalStorageDirectory(), getString(R.string.filename));
      outputStream = new FileOutputStream(file);
      outputStream.write(text.getBytes());
      outputStream.close();
      Log.d(TAG, "Saved to external storage. Output stream closed");
      Snackbar.make(view, R.string.saved_to_external, Snackbar.LENGTH_SHORT).show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void onClickSavePreferences(View view) {
    String text = textInput.getText().toString();
    Log.d(TAG, "Saving '" + text + "'");
    prefs.edit().putString("text", text).apply();
    Log.d(TAG, "Saved preference");
    Snackbar.make(view, R.string.saved_to_preferences, Snackbar.LENGTH_SHORT).show();
  }
}
