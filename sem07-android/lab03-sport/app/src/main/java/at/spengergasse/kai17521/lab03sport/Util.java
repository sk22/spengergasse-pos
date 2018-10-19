package at.spengergasse.kai17521.lab03sport;

import android.view.View;
import android.widget.Toast;

public class Util {
    public static boolean SEX_FEMALE = true;
    public static boolean SEX_MALE = false;

    public static void showError(View view, String text) {
        Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
