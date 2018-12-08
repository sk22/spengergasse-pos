package at.spengergasse.kai17521.lab05recyclerview;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import static java.lang.String.format;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {
  private SparseArray<Student> students = new SparseArray<>();
  private SparseArray<CheckBox> checkboxes = new SparseArray<>();

  StudentsAdapter(List<Student> students) {
    for (int i = 0; i < students.size(); i++) {
      this.students.put(i, students.get(i));
    }
  }

  public SparseArray<Student> getStudents() {
    return students;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View itemView = LayoutInflater.from(viewGroup.getContext())
      .inflate(R.layout.list_row_student, viewGroup, false);
    checkboxes.put(i, (CheckBox) itemView.findViewById(R.id.done));
    return new ViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    checkboxes.put(i, viewHolder.done);
    Student student = students.get(i);
    viewHolder.id.setText(String.valueOf(i));
    viewHolder.done.setChecked(student.isDone());
    viewHolder.name.setText(format("%s %s", student.getFirstName(), student.getLastName()));
    viewHolder.score.setText(String.valueOf(student.getScore()));
  }

  @Override
  public int getItemCount() {
    return students.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    TextView id;
    CheckBox done;
    TextView name;
    TextView score;

    ViewHolder(@NonNull View itemView) {
      super(itemView);
      id = itemView.findViewById(R.id.id);
      done = itemView.findViewById(R.id.done);
      name = itemView.findViewById(R.id.name);
      score = itemView.findViewById(R.id.score);
      DoneClickListener clickListener = new DoneClickListener();
      itemView.setOnClickListener(clickListener);
      done.setOnClickListener(clickListener);
    }

    class DoneClickListener implements View.OnClickListener {
      @Override
      public void onClick(View v) {
        int position = getAdapterPosition();
        Student student = students.get(position);
        boolean newValue = !student.isDone();
        done.setChecked(newValue);
        student.setDone(newValue);

        Log.d("StudentsAdapter", position + " " + students.get(position).getFirstName());
      }
    }
  }
}
