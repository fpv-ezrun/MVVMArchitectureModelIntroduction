package com.mindorks.framework.mvvm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import data.daos.TrainingDao;
import data.db.entities.Training;
import data.remote.services.TrainingServices;
import data.repository.TrainingRepository;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TestViewModel model;
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    EditText EditId;
    EditText EditString;
    TextView MonText;
    Training MyTmpTraining;
    TrainingRepository MyTmpRepository;
    TrainingDao local;
    TrainingServices remote;
    Continuation<?super Long> TmpLong;
    Continuation<? super Unit> TmpUnit;
    Continuation<? super LiveData<List<Training>>> TmpLive;
    LiveData<List<Training>> TmpLivedata;
    List<Training> TmpListTraining = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        local = new TrainingDao() {
            @Override
            public long updateInsert(@NotNull Training training) {
                return 0;
            }

            @Override
            public int delete(@NotNull Training training) {
                return 0;
            }

            @NotNull
            @Override
            public LiveData<List<Training>> getAllTraining() {
                return null;
            }

            @NotNull
            @Override
            public LiveData<Training> gettrainingid(int trainingId) {
                return null;
            }
        };
        remote = new TrainingServices() {
            @NotNull
            @Override
            public String TestFetch(@NotNull String String) {
                return null;
            }

            @NotNull
            @Override
            public String FetchTraining(@NotNull OkHttpClient client, @NotNull HttpUrl base) throws IOException {
                return null;
            }
        };

        MyTmpRepository = new TrainingRepository(local, remote);
        model = new ViewModelProvider(this).get(TestViewModel.class); // Pour initialiser ma couche viewmodel
        final Button btn1search = (Button) findViewById(R.id.buttonChercher);
        Snackbar SnackbarError = Snackbar.make(findViewById(R.id.view_pager2),"Saisir un Training Valide !!!",3000); //initialisation des messages erreurs saisie
        Snackbar SnackbarValide = Snackbar.make(findViewById(R.id.view_pager2),"Enregistrement du Training Réussi !!!",3000);//initialisation des messages d'enregistrement
        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);
        MonText = (TextView) findViewById(R.id.title_add);
        MyTmpTraining = new Training(1,"test");
        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Insert Trainings"));
        tabLayout.addTab(tabLayout.newTab().setText("View Trainings"));
        tabLayout.addTab(tabLayout.newTab().setText("Others"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
       @Override
       public void onPageSelected(int position) {
           System.out.println("Changement de fenêtre, page :"+(position+1));
           tabLayout.selectTab(tabLayout.getTabAt(position));
           final Button btn1search = (Button) findViewById(R.id.buttonChercher);
           final TextView FirstTraining = (TextView) findViewById(R.id.first_training);
            if (btn1search!=null ){
           btn1search.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   switch (v.getId()) {
                       case R.id.buttonChercher:
                           System.out.println("click sur le boutton d enregistrement");
                           EditString = (EditText) findViewById(R.id.TitreTraining);
                           EditId = (EditText) findViewById(R.id.IdTraining);
                           if (!"".equals(EditId.getText().toString()) && !"".equals(EditString.getText().toString())) {
                               System.out.println("Id du Training enregistrer :" + EditId.getText().toString());
                               System.out.println("Description du Training :" + EditString.getText().toString());
                               MyTmpTraining.setTraining_id(Integer.parseInt(EditId.getText().toString()));
                               MyTmpTraining.setName(EditString.getText().toString());
                              // MyTmpRepository.insertUpdate(MyTmpTraining,TmpLong);
                               TmpListTraining.add(MyTmpTraining);
                               //model.setTestlateinit(TmpListTraining);
                               //model.testlateinit.add(0,MyTmpTraining);
                               System.out.println("Training enregistré en db :"+"Description :"+MyTmpTraining.getName()+" id :"+MyTmpTraining.getTraining_id());
                               System.out.println("Résultat du Livedata:"+TmpListTraining.get(0));
                                model.setTrainingListTraining(MyTmpTraining,0);
                               System.out.println("Résultat du livedata depuis le viewmodel avec id: "+model.getTrainingListTraining(0).getTraining_id()+" name: "+model.getTrainingListTraining(0).getName());
                               // model.setTrainingLocal(MyTmpTraining,TmpUnit);
                               FirstTraining.setText("- id: "+model.getTrainingListTraining(0).getTraining_id()+" name: "+model.getTrainingListTraining(0).getName());
                               SnackbarValide.show();
                           } else {

                               SnackbarError.show();
                           }

                           break;
                       case R.id.update_training:
                           FirstTraining.setText("- id: "+model.getTrainingListTraining(0).getTraining_id()+" name: "+model.getTrainingListTraining(0).getName());

                   }
               }
           });

       }

       }
        });
    }
    private int getItemofviewpager(int i) {
        return pager2.getCurrentItem() + i;
    } // TODO pour enlever le if

    @Override
    public void onClick(View v) {

        System.out.println("entrer dans le onclick");

        switch (v.getId()) {
            case R.id.buttonChercher:
                pager2.setCurrentItem(getItemofviewpager(0), true);
                break;
        }
    }
}




