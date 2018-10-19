package at.spengergasse.kai17521.lab03sport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;
import static at.spengergasse.kai17521.lab03sport.Util.SEX_FEMALE;
import static at.spengergasse.kai17521.lab03sport.Util.SEX_MALE;
import static at.spengergasse.kai17521.lab03sport.Util.showError;

public class PulseActivity extends AppCompatActivity {
    private RadioGroup sexRadioGroup;
    private EditText ageEditText;
    private RadioButton sexMaleRadio;
    private RadioButton sexFemaleRadio;
    private RadioButton sexSurpriseRadio;
    private TextView resultText;
    private TextView pulseRekomMax;
    private TextView pulseGa1Min;
    private TextView pulseGa1Max;
    private TextView pulseGa2Min;
    private TextView pulseGa2Max;
    private TextView pulseWsaMin;
    private TextView pulseWsaMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulse);
        sexRadioGroup = findViewById(R.id.sex);
        ageEditText = findViewById(R.id.age);
        sexMaleRadio = findViewById(R.id.sex_male);
        sexFemaleRadio = findViewById(R.id.sex_female);
        sexSurpriseRadio = findViewById(R.id.sex_surprise);
        resultText = findViewById(R.id.result);
        pulseRekomMax = findViewById(R.id.pulse_rekom_max);
        pulseGa1Min = findViewById(R.id.pulse_ga1_min);
        pulseGa1Max = findViewById(R.id.pulse_ga1_max);
        pulseGa2Min = findViewById(R.id.pulse_ga2_min);
        pulseGa2Max = findViewById(R.id.pulse_ga2_max);
        pulseWsaMin = findViewById(R.id.pulse_wsa_min);
        pulseWsaMax = findViewById(R.id.pulse_wsa_max);
    }

    public void onCalculateButtonClicked(View view) {
        try {
            int age = Integer.parseInt(ageEditText.getText().toString());
            if (age < 0) {
                showError(view, getString(R.string.too_small_at_least_zero, "Age"));
                return;
            }

            if (sexSurpriseRadio.isChecked()) {
                sexRadioGroup.check(Math.random() < 0.5f ? R.id.sex_female : R.id.sex_male);
            }

            if (sexFemaleRadio.isChecked()) {
                updateResult(calculatePulse(SEX_FEMALE, age));
            } else if (sexMaleRadio.isChecked()) {
                updateResult(calculatePulse(SEX_MALE, age));
            }

        } catch (NumberFormatException nfe) {
            showError(view, getString(R.string.invalid_format, "Age"));
        }
    }

    public float calculatePulse(boolean sex, int age) {
        return sex == SEX_FEMALE
                ? 226f - age
                : 223f - 0.9f * age;
    }

    private String getPercentage(float percentage) {
        return String.format(Locale.US, "%.2f", percentage);
    }

    private void updateResult(float max) {
        resultText.setText(getString(R.string.pulse_max_frequency, max));
        pulseRekomMax.setText(getPercentage(max * .7f));
        pulseGa1Min.setText(getPercentage(max * .65f));
        pulseGa1Max.setText(getPercentage(max * .8f));
        pulseGa2Min.setText(getPercentage(max * .8f));
        pulseGa2Max.setText(getPercentage(max * .9f));
        pulseWsaMin.setText(getPercentage(max * .9f));
        pulseWsaMax.setText(getPercentage(max));
    }
}
