package com.arichafamily.thequizzardofozw.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arichafamily.thequizzardofozw.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    Button btnLogOut;
    FirebaseAuth mAuth;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        btnLogOut = (Button) v.findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        FirebaseAuth.getInstance().signOut();
    }
}
