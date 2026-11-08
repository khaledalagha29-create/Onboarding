package com.example.onboardingassignment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    public interface OnNameSubmittedListener { void onNameSubmitted(String name); }
    private OnNameSubmittedListener listener;

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof OnNameSubmittedListener)) throw new IllegalStateException("Host must implement OnNameSubmittedListener");
        listener = (OnNameSubmittedListener) context;
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment1, parent, false);
        EditText name = root.findViewById(R.id.nameInput);
        Button next = root.findViewById(R.id.toDetailsButton);
        next.setOnClickListener(v -> {
            String value = name.getText().toString().trim();
            if (value.isEmpty()) { name.setError("Please enter your name"); return; }
            listener.onNameSubmitted(value);
        });
        return root;
    }

    @Override public void onDetach() { listener = null; super.onDetach(); }
}
