package at.spengergasse.kai17521.lab05recyclerview;

import android.support.annotation.Nullable;
import android.util.JsonReader;
import android.util.Log;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.List;

public class StudentsIO {
  private final Gson gson;
  private final Type listType = new TypeToken<List<Student>>(){}.getType();

  private File file;

  StudentsIO(File file) {
    this.file = file;
    this.gson = new Gson();
  }

  public List<Student> readStudents() throws IOException, JsonSyntaxException {
    Log.d("StudentsIO", file.getAbsolutePath());
    List<Student> students = gson.fromJson(new FileReader(file), listType);
    if (students == null) throw new EOFException("File was empty");
    return students;
  }

  public void saveStudents(List<Student> students, @Nullable File newFile) throws IOException {
    Log.d("StudentsIO", String.valueOf(students.size()));
    Log.d("StudentsIO", String.valueOf(newFile == null ? file : newFile));
    Writer writer = new FileWriter(newFile == null ? file : newFile);
    gson.toJson(students, listType, writer);
    writer.close();
  }
}
