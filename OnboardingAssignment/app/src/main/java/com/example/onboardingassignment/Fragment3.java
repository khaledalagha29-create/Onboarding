package com.example.onboardingassignment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {
    private static final String ARG_NAME = "name";
    public interface OnConfirmationChangedListener { void onConfirmationChanged(boolean checked); }
    private OnConfirmationChangedListener listener;
    private Button continueButton;

    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof OnConfirmationChangedListener)) throw new IllegalStateException("Host must implement OnConfirmationChangedListener");
        listener = (OnConfirmationChangedListener) context;
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment3, parent, false);
        TextView nameText = root.findViewById(R.id.confirmNameText);
        nameText.setText("Welcome, " + requireArguments().getString(ARG_NAME, "") + "!");
        CheckBox checkbox = root.findViewById(R.id.confirmCheckbox);
        continueButton = root.findViewById(R.id.finishButton);
        updateContinueButton(false); // Initially disabled, text = Continue.
        checkbox.setOnCheckedChangeListener((buttonView, checked) -> {
            if (listener != null) listener.onConfirmationChanged(checked);
        });
        // Per assignment, clicking Finish requires no further action.
        return root;
    }

    public void updateContinueButton(boolean confirmed) {
        if (continueButton != null) {
            continueButton.setEnabled(confirmed);
            continueButton.setText(confirmed ? "Finish" : "Continue");
        }
    }

    @Override public void onDestroyView() { continueButton = null; super.onDestroyView(); }
    @Override public void onDetach() { listener = null; super.onDetach(); }
}
