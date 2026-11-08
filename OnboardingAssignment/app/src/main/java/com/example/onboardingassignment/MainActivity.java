package com.example.onboardingassignment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements
        Fragment1.OnNameSubmittedListener,
        Fragment2.OnDetailsSubmittedListener,
        Fragment3.OnConfirmationChangedListener {

    private String userName = "";

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            userName = savedInstanceState.getString("userName", "");
        } else {
            show(new Fragment1());
        }
    }

    private void show(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override public void onNameSubmitted(String name) {
        userName = name;
        show(Fragment2.newInstance(name));
    }

    @Override public void onDetailsSubmitted(String name, String email, String gender, String university) {
        // Fragment2 -> Host Activity -> Fragment3 (via Bundle arguments).
        userName = name;
        show(Fragment3.newInstance(userName));
    }

    @Override public void onConfirmationChanged(boolean isChecked) {
        // Interface callback: the Host instructs Fragment3 to refresh its button.
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (current instanceof Fragment3) {
            ((Fragment3) current).updateContinueButton(isChecked);
        }
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        outState.putString("userName", userName);
        super.onSaveInstanceState(outState);
    }
}
