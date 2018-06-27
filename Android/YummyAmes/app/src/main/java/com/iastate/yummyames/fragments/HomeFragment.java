package com.iastate.yummyames.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iastate.yummyames.R;


public class HomeFragment extends Fragment {

    private TextView tasteplace;
    private TextView mayhouse;
    private ImageView tastePlace;
    private ImageView mayHouse;
    private ImageView Special;
    private ImageView xiangguo;

    private int[] hintimage = {R.drawable.tasty, R.drawable.xiangguo, R.drawable.may, R.drawable.special};
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        tastePlace = (ImageView) view.findViewById(R.id.image1);
        tastePlace.setImageResource(hintimage[0]);
        xiangguo = (ImageView) view.findViewById(R.id.image2);
        xiangguo.setImageResource(hintimage[1]);
        mayHouse = (ImageView) view.findViewById(R.id.image3);
        mayHouse.setImageResource(hintimage[2]);
        Special = (ImageView) view.findViewById(R.id.image4);
        Special.setImageResource(hintimage[3]);

        tasteplace = (TextView) view.findViewById(R.id.text1);
        tasteplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "MLXG: the most favorite food!", Toast.LENGTH_SHORT).show();
            }
        });

        mayhouse = (TextView) view.findViewById(R.id.text2);
        mayhouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Special: the most favorite food!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}
