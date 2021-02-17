package com.kg.Headlines;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ScreenSlidePageFragmentB extends Fragment {


    private static Button  button_ent,button_saved,  button_science, button_business,button_temp;
    private FragmentBlistener listener;
    public interface FragmentBlistener{

        void onInputB();
        void intentClick();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        ViewGroup v2 = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page2, container, false);


        button_ent = v2.findViewById(R.id.button_ent);
        button_science = v2.findViewById(R.id.button_science);
        button_business = v2.findViewById(R.id.button_business);
        button_saved=v2.findViewById(R.id.button_saved);




        button_science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                button_ent.setPressed(false);
                button_ent.setSelected(false);


                button_business.setPressed(false);
                button_business.setSelected(false);
                button_science.setPressed(true);
                button_science.setSelected(true);

                button_temp=(Button)button_science;
                listener.onInputB();


                ((MainActivity) getActivity()).retrieveData("science");
            }
        });


        button_ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                button_science.setPressed(false);
                button_science.setSelected(false);
                button_business.setPressed(false);
                button_business.setSelected(false);
                button_ent.setPressed(true);
                button_ent.setSelected(true);


                button_temp=(Button)button_ent;
                listener.onInputB();

                ((MainActivity) getActivity()).retrieveData("entertainment");
            }
        });


        button_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                button_ent.setPressed(false);
                button_ent.setSelected(false);
                button_science.setPressed(false);
                button_science.setSelected(false);
                button_business.setPressed(true);
                button_business.setSelected(true);


                button_temp=(Button)button_business;
                listener.onInputB();

                ((MainActivity) getActivity()).retrieveData("business");
            }
        });

        button_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.intentClick();
            }
        });

        return v2;
    }





    public void updateB()
    {

        if(button_temp!=null){
            button_temp.setPressed(false);
            button_temp.setSelected(false);}


    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);


        listener=(ScreenSlidePageFragmentB.FragmentBlistener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

}
