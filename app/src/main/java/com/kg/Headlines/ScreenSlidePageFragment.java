package com.kg.Headlines;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ScreenSlidePageFragment extends Fragment {




    private static  Button button_tech,button_health,button_sports,button_temp,button_home;


    private FragmentAlistener listener;

    public interface FragmentAlistener{

        void onInputA();

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup v1 = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page1, container, false);


        button_home=v1.findViewById(R.id.button_home);
        button_tech = v1.findViewById(R.id.button_tech);
        button_sports = v1.findViewById(R.id.button_sports);
        button_health = v1.findViewById(R.id.button_health);

        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                button_sports.setPressed(false);
                button_sports.setSelected(false);


                button_tech.setSelected(false);
                button_tech.setPressed(false);

                button_health.setPressed(false);
                button_health.setSelected(false);

                button_home.setPressed(true);
                button_home.setSelected(true);


                button_temp=(Button)button_home;
                listener.onInputA();

                ((MainActivity) getActivity()).retrieveData( "general");
            }
        });



        button_tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                button_home.setPressed(false);
                button_home.setSelected(false);
                button_health.setPressed(false);
                button_health.setSelected(false);

                button_sports.setPressed(false);
                button_sports.setSelected(false);


                button_tech.setSelected(true);
                button_tech.setPressed(true);

                button_temp=(Button)button_tech;
                listener.onInputA();

                ((MainActivity) getActivity()).retrieveData("technology");
            }
        });


        button_health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                button_home.setPressed(false);
                button_home.setSelected(false);

                button_sports.setPressed(false);
                button_sports.setSelected(false);


                button_tech.setSelected(false);
                button_tech.setPressed(false);

                button_health.setPressed(true);
                button_health.setSelected(true);


                button_temp=(Button)button_health;
                listener.onInputA();

                ((MainActivity) getActivity()).retrieveData( "health");
            }
        });

        button_sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                button_home.setPressed(false);
                button_home.setSelected(false);

                button_tech.setSelected(false);
                button_tech.setPressed(false);

                button_health.setPressed(false);
                button_health.setSelected(false);
                button_sports.setPressed(true);
                button_sports.setSelected(true);



                button_temp=(Button)button_sports;
                listener.onInputA();

                ((MainActivity) getActivity()).retrieveData("sports");
            }
        });



        return v1;




    }



  /*  public void update()
    {
        button_health.setPressed(false);
        button_health.setSelected(false);
    }*/




    public void updateA()
    {

        if(button_temp!=null){
            button_temp.setPressed(false);
            button_temp.setSelected(false);}
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);


        listener=(FragmentAlistener) context;


    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }



}
