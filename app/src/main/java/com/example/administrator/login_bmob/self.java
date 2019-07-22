package com.example.administrator.login_bmob;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class self extends Fragment {


    public self() {
        // Required empty public constructor
    }

    private TextView name;
    private String self;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        self = ((lendin) activity).getself();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_self,null);
        name=view.findViewById(R.id.self);
        name.setText(self);
        return view;
    }



}
