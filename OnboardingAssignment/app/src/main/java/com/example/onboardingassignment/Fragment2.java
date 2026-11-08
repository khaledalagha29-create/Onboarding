package com.example.onboardingassignment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {
    private static final String ARG_NAME = "name";

    public interface OnDetailsSubmittedListener {
        void onDetailsSubmitted(String name, String email, String gender, String university);
    }
    private OnDetailsSubmittedListener listener;

    public static Fragment2 newInstance(String name) {
        Fragment2 f = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        f.setArguments(args);
        return f;
    }

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof OnDetailsSubmittedListener)) throw new IllegalStateException("Host must implement OnDetailsSubmittedListener");
        listener = (OnDetailsSubmittedListener) context;
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment2, parent, false);
        String name = requireArguments().getString(ARG_NAME, "");
        TextView heading = root.findViewById(R.id.detailsHeading);
        heading.setText("Nice to meet you, " + name + "!");
        EditText emailInput = root.findViewById(R.id.emailInput);
        EditText universityInput = root.findViewById(R.id.universityInput);
        Spinner genderSpinner = root.findViewById(R.id.genderSpinner);
        Button continueButton = root.findViewById(R.id.detailsContinueButton);
        continueButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailInput.setError("Enter a valid email"); return;
            }
            String gender = genderSpinner.getSelectedItem().toString();
            String university = universityInput.getText().toString().trim();
            listener.onDetailsSubmitted(name, email, gender, university);
        });
        return root;
    }
    @Override public void onDetach() { listener = null; super.onDetach(); }
}
