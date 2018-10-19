package at.spengergasse.kai17521.lab03sport;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static at.spengergasse.kai17521.lab03sport.Util.SEX_FEMALE;
import static at.spengergasse.kai17521.lab03sport.Util.SEX_MALE;
import static at.spengergasse.kai17521.lab03sport.Util.showError;

public class BmiActivity extends AppCompatActivity {
  private RadioGroup sexRadioGroup;
  private EditText heightEditText;
  private EditText weightEditText;
  private RadioButton sexMaleRadio;
  private RadioButton sexFemaleRadio;
  private RadioButton sexSurpriseRadio;
  private TextView resultText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bmi);
    sexRadioGroup = findViewById(R.id.sex);
    heightEditText = findViewById(R.id.height);
    weightEditText = findViewById(R.id.weight);
    sexMaleRadio = findViewById(R.id.sex_male);
    sexFemaleRadio = findViewById(R.id.sex_female);
    sexSurpriseRadio = findViewById(R.id.sex_surprise);
    resultText = findViewById(R.id.result);
  }

  public float calculateBmi(int height, int weight) {
    float meter = height / 100f;
    return (float) weight / (meter * meter);
  }

  public void onCalculateButtonClicked(View view) {
    try {
      int height = Integer.parseInt(heightEditText.getText().toString());
      int weight = Integer.parseInt(weightEditText.getText().toString());

      if (height < 0) {
        showError(view, getString(R.string.too_small_at_least_zero, "Height"));
        return;
      }

      if (weight < 0) {
        showError(view, getString(R.string.too_small_at_least_zero, "Weight"));
        return;
      }


      if (sexSurpriseRadio.isChecked()) {
        sexRadioGroup.check(Math.random() < 0.5f ? R.id.sex_female : R.id.sex_male);
      }

      updateResult(
        calculateBmi(height, weight),
        sexFemaleRadio.isChecked() ? SEX_FEMALE : SEX_MALE
      );

    } catch (NumberFormatException nfe) {
      showError(view, getString(R.string.invalid_format, "Age"));
    }
  }

  private String getBmiString(float bmi, boolean sex) {
    if (bmi < (sex == SEX_FEMALE ? 19 : 20)) {
      return getString(R.string.untergewicht);
    }
    if (bmi <= (sex == SEX_FEMALE ? 24 : 25)) {
      return getString(R.string.normalgewicht);
    }
    if (bmi <= 30) {
      return getString(R.string.uebergewicht);
    }
    if (bmi <= 40) {
      return getString(R.string.adipositas);
    }
    else return getString(R.string.starke_adipositas);
  }

  private void updateResult(float bmi, boolean sex) {
    resultText.setText(
      getString(R.string.bmi_result, bmi, getBmiString(bmi, sex))
    );
  }
}
