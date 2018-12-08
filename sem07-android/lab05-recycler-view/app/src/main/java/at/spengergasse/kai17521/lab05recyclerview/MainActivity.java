package at.spengergasse.kai17521.lab05recyclerview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private Spinner spinner;
  private File[] files;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getPermissions(findViewById(R.id.spinner));
    updateFiles();
  }

  @Override
  protected void onResume() {
    super.onResume();
    updateFiles();
  }

  private void updateFiles() {
    String path = Environment.getExternalStorageDirectory().toString() + "/random-students";
    Log.d("Files", "Path: " + path);
    File directory = new File(path);
    files = directory.listFiles();
    Log.d("Files", "Length: " + files.length);

    spinner = findViewById(R.id.spinner);
    List<String> fileStrings = new ArrayList<>();
    for (File f : files) {
      fileStrings.add(f.getName());
    }
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
      this, android.R.layout.simple_spinner_dropdown_item, fileStrings);
    spinner.setAdapter(arrayAdapter);
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

  private void getPermissions(View view) {
    final int writePermission = ContextCompat
      .checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    final int readPermission = ContextCompat
      .checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

    if (writePermission != PackageManager.PERMISSION_GRANTED
      || readPermission != PackageManager.PERMISSION_GRANTED) {
      final int PERMISSIONS_EXTERNAL_STORAGE = 1;
      ActivityCompat.requestPermissions(this, new String[]{
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
      }, PERMISSIONS_EXTERNAL_STORAGE);
    }
  }

  public void onClickLoad(View view) {
    Intent intent = new Intent(this, RandomSelectionActivity.class);
    intent.putExtra("filename", files[spinner.getSelectedItemPosition()].getAbsolutePath());
    startActivity(intent);
  }
}
