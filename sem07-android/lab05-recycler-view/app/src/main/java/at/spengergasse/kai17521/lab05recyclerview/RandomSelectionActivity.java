package at.spengergasse.kai17521.lab05recyclerview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.lang.Math.random;
import static java.lang.String.format;

public class RandomSelectionActivity extends AppCompatActivity {

  private static final String TAG = "RandomSelectionActivity";
  private static final int SCORE_MAX = 3;
  private RecyclerView recyclerView;
  private LinearLayout infoView;
  private TextView randomStudentView;
  private TextView scoreView;
  private TextView scoreMaxView;
  private SparseArray<Student> students;
  private boolean isTemplateFile;
  private StudentsIO reader;
  private String fileName;

  @Override @SuppressWarnings("unchecked")
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    List<Student> restored = null;
    if (savedInstanceState != null) {
      restored = savedInstanceState.getParcelableArrayList("students");
    }

    setContentView(R.layout.activity_random_selection);
    Bundle params = getIntent().getExtras();
    assert params != null;
    fileName = params.getString("filename");
    infoView = findViewById(R.id.info);
    randomStudentView = findViewById(R.id.random_student);
    scoreView = findViewById(R.id.score);
    scoreMaxView = findViewById(R.id.score_max);
    Log.d(TAG, fileName);

    recyclerView = findViewById(R.id.students_recycler);
    assert fileName != null;
    reader = new StudentsIO(new File(fileName));

    try {
      List<Student> studentsList = restored == null ? reader.readStudents() : restored;
      isTemplateFile = determineTemplateFile(studentsList);
      StudentsAdapter adapter = new StudentsAdapter(studentsList);
      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
      recyclerView.setLayoutManager(layoutManager);
      recyclerView.setItemAnimator(new DefaultItemAnimator());
      recyclerView.setAdapter(adapter);
      this.students = adapter.getStudents();

    } catch (IOException e) {
      Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
      e.printStackTrace();
    } catch (JsonSyntaxException e) {
      Toast.makeText(this, "File was in an invalid format", Toast.LENGTH_LONG).show();
      e.printStackTrace();
    }

  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putParcelableArrayList("students", studentsList());
  }

  private boolean determineTemplateFile(List<Student> studentsList) {
    int sum = 0;
    for (Student s : studentsList) sum += s.getScore();
    return sum == 0;
  }

  public void onClickRandom(View view) {
    List<Student> applicableStudents = new ArrayList<>();
    for (int i = 0; i < students.size(); i++) {
      Student s = students.get(i);
      if (!s.isDone() && s.getScore() < SCORE_MAX) {
        applicableStudents.add(s);
      }
    }

    int size = applicableStudents.size();
    if (size == 0) {
      randomStudentView.setText(R.string.none_left);
      return;
    }

    Student randomStudent = applicableStudents.get((int) (random() * size));

    int newScore = randomStudent.getScore() + 1;
    randomStudent.setScore(newScore);
    if (newScore == SCORE_MAX) randomStudent.setDone(true);
    StudentsAdapter adapter = (StudentsAdapter) recyclerView.getAdapter();
    if (adapter != null) {
      recyclerView.getAdapter().notifyDataSetChanged();
    }

    scoreView.setText(String.valueOf(randomStudent.getScore()));
    scoreMaxView.setText(String.valueOf(SCORE_MAX));
    randomStudentView.setText(
      format("%s %s", randomStudent.getFirstName(), randomStudent.getLastName())
    );
    infoView.setVisibility(View.VISIBLE);
  }

  private ArrayList<Student> studentsList() {
    ArrayList<Student> studentsList = new ArrayList<>();
    for (int i = 0; i < students.size(); i++) {
      studentsList.add(students.get(i));
    }
    return studentsList;
  }

  public void save(File file) {
    try {
      reader.saveStudents(studentsList(), file);
    } catch (IOException e) {
      Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
  }

  public void onClickSave(View view) {
    String fileNameWithoutExt = fileName.replaceFirst("\\.[^.]+$", "");
    Log.d("RandomSelectionActivity", fileNameWithoutExt);
    Log.d("RandomSelectionActivity", fileName);
    String fileNameExt = fileName
      .substring(fileNameWithoutExt.length(), fileName.length());

    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat outputFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    File newFile = isTemplateFile
      ? new File(fileNameWithoutExt + '-' + outputFmt.format(date) + fileNameExt)
      : new File(fileName);
    promptFileName(view, newFile.getName(), newFile.getParentFile());
  }

  private void promptFileName(View view, String fileName, final File parentFile) {
    @SuppressLint("InflateParams") View viewInflated = LayoutInflater.from(this)
      .inflate(R.layout.text_input, null);

    final EditText input = viewInflated.findViewById(R.id.input);
    input.setText(fileName);

    new AlertDialog.Builder(this)
      .setTitle("Enter file name")
      .setView(viewInflated)
      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          save(new File(parentFile, input.getText().toString()));
        }
      })
      .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          dialog.cancel();
        }
      })
      .show();
    }
}
