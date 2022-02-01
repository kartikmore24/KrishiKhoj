package com.example.bottomnav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    private MeowBottomNavigation bnv_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        bnv_main = findViewById(R.id.bnv_main);
        bnv_main.add(new MeowBottomNavigation.Model(1,R.drawable.ic_launcher_background1));
        bnv_main.add(new MeowBottomNavigation.Model(2,R.drawable.ic_search));
        bnv_main.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_phone_24));


        bnv_main.show(1,true);
       // bnv_main.setCount(3,"8");
        replace(new HomeFragment());
        bnv_main.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        replace(new HomeFragment());

                        break;
                    case 2:
                        replace(new SearchFragment());

                        break;
                    case 3:
                        replace(new BookmarkFragment());

                        break;

                }
                return null;
            }
        });
    }

    private void replace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }
}