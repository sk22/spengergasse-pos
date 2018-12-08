package at.spengergasse.kai17521.lab05recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Student implements Serializable, Parcelable {
  @SerializedName("first_name")
  private String firstName;

  @SerializedName("last_name")
  private String lastName;

  @SerializedName("score")
  private int score;

  private boolean done;

  protected Student(Parcel in) {
    firstName = in.readString();
    lastName = in.readString();
    score = in.readInt();
    done = in.readByte() != 0;
  }

  public static final Creator<Student> CREATOR = new Creator<Student>() {
    @Override
    public Student createFromParcel(Parcel in) {
      return new Student(in);
    }

    @Override
    public Student[] newArray(int size) {
      return new Student[size];
    }
  };

  public boolean isDone() {
    return done;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getScore() {
    return score;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(firstName);
    dest.writeString(lastName);
    dest.writeInt(score);
    dest.writeByte((byte) (done ? 1 : 0));
  }
}
