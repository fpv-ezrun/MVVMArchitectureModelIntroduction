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

import androidx.room.Room;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.daos.TrainingDao;
import data.db.AppDatabase;
import data.db.entities.Training;
import data.remote.services.TrainingServices;
import data.repository.TrainingRepository;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TrainingViewModel model;
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    EditText EditId;
    EditText EditString;
    TextView MonText;
    Training MyTmpTraining;
    TrainingRepository MyTmpRepository;
    TrainingDao local;
    TrainingServices remote = new TrainingServices() {
        @NotNull
        @Override
        public String TestFetch(@NotNull String String) {
            return null;
        }

        @NotNull
        @Override
        public String FetchTraining(@NotNull OkHttpClient client, @NotNull HttpUrl base) throws IOException {
            return null; //TODO temporairement les webservices ne sont pas encore prix en compte par l'application
        }
    };
    Continuation<?super Long> TmpLong;
    Continuation<? super Unit> TmpUnit;
    Continuation<? super LiveData<List<? extends Training>>> TmpLivetest;
    Continuation<? super LiveData<List<? extends Training>>> TmpLive;
    LiveData<List<Training>> TmpLivedata;
    List<Training> TmpListTraining = new ArrayList<>();
    int cptTraining = 0;
    AppDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //MyTmpRepository = new TrainingRepository(local, remote);
        model = new ViewModelProvider(this).get(TrainingViewModel.class); // Pour initialiser ma couche viewmodel

        pager2 = findViewById(R.id.view_pager2);
        final Button btn1search = (Button) findViewById(R.id.buttonChercher);
<<<<<<< HEAD
        Snackbar SnackbarError = Snackbar.make(pager2,"Saisir un Training Valide !!!",2000); //initialisation des messages erreurs saisie
        Snackbar SnackbarValide = Snackbar.make(pager2,"Enregistrement du Training Réussi !!!",2000);//initialisation des messages d'enregistrement
        Snackbar SnackbarUpdate = Snackbar.make(pager2,"Rien a mettre a jour !!!",2000);
        Snackbar SnackbarSame = Snackbar.make(pager2,"Ce Training existe deja en db !!!, MaJ du Training",2000);
        Snackbar SnackbarIDDelete = Snackbar.make(pager2,"l'id n'existe pas !!!",2000);
=======
        Snackbar SnackbarError = Snackbar.make(pager2,"Saisir un Training Valide !!!",3000); //initialisation des messages erreurs saisie
        Snackbar SnackbarValide = Snackbar.make(pager2,"Enregistrement du Training Réussi !!!",3000);//initialisation des messages d'enregistrement
        Snackbar SnackbarUpdate = Snackbar.make(pager2,"Rien a mettre a jour !!!",3000);
>>>>>>> parent of 108e3f8 (with ui)
        tabLayout = findViewById(R.id.tab_layout);
        database = Room.inMemoryDatabaseBuilder(getApplicationContext(),AppDatabase.class).allowMainThreadQueries().build();//initialisation de la db en local //TODO le refactoriser dans une classe d'initialisation
        System.out.println("Database initialisée, nom de la db : database");
        MonText = (TextView) findViewById(R.id.title_add);
        MyTmpTraining = new Training(1,"test");
        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);
        model.init(database.getTrainingDao(),remote);

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
           final TextView second_Training = (TextView) findViewById(R.id.second_training);
           final TextView third_Training = (TextView) findViewById(R.id.third_training);
           final TextView fourth_Training = (TextView) findViewById(R.id.fourth_training);
           final TextView fifth_Training = (TextView) findViewById(R.id.fifth_training);
           final Button update = (Button) findViewById(R.id.update_training);
           if (update!=null){
               update.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if(model.TmpTraining!=null) {
                           if(model.getTrainingListTraining(0).getTraining_id()!=null) {
                               FirstTraining.setText("- id: " + model.getTrainingListTraining(0).getTraining_id() + " name: " + model.getTrainingListTraining(0).getName());
                           }
                           if(model.getTrainingListTraining(1).getTraining_id()!=null) {
                               second_Training.setText("- id: " + model.getTrainingListTraining(1).getTraining_id() + " name: " + model.getTrainingListTraining(1).getName());
                           }
                           if(model.getTrainingListTraining(2).getTraining_id()!=null) {
                               third_Training.setText("- id: " + model.getTrainingListTraining(2).getTraining_id() + " name: " + model.getTrainingListTraining(2).getName());
                           }
                           if(model.getTrainingListTraining(3).getTraining_id()!=null) {
                               fourth_Training.setText("- id: " + model.getTrainingListTraining(3).getTraining_id() + " name: " + model.getTrainingListTraining(3).getName());
                           }
<<<<<<< HEAD
                           if(TmpListTraining!=null) {
                               if (TmpListTraining.size() != Oldnumber) {
                                   SnackbarUpdate.show();
                               }
                               Oldnumber++;
=======
                           if(model.getTrainingListTraining(4).getTraining_id()!=null) {
                               fifth_Training.setText("- id: " + model.getTrainingListTraining(4).getTraining_id() + " name: " + model.getTrainingListTraining(4).getName());
>>>>>>> parent of 108e3f8 (with ui)
                           }
                       }else {
                           SnackbarUpdate.show();
                       }
                   }
               });
           }
<<<<<<< HEAD
           if (delete!=null){
               delete.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if(idDelete.getText().toString()!=null){
                           System.out.println("<<<<<<<<<<<<<"+idDelete.getText().toString()+">>>>>>>>>>>>>>");
                       if(model.getLocalTrainingsByIDFromRepository(Integer.parseInt(idDelete.getText().toString()),TmpTraining)!=null){
                           TrainingDelete= (Training) model.getLocalTrainingsByIDFromRepository(Integer.parseInt(idDelete.getText().toString()),TmpTraining);
                           model.deleteLocalTrainingFromRepository(TrainingDelete,TmpUnit);
                           cptTraining = TmpListTraining.size()-1;//TODO
                           TmpListTraining= (List<Training>) model.getLocalTrainingsFromRepository(TmpList);
                       }else {
                           System.out.println(TmpListTraining);
                           SnackbarIDDelete.show();
                       }
                       }
                   }
               });
           }
=======
>>>>>>> parent of 108e3f8 (with ui)
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
                              // TmpListTraining.add(MyTmpTraining);
                               //model.setTestlateinit(TmpListTraining);
                               //model.testlateinit.add(0,MyTmpTraining);
                               System.out.println("Training enregistré en db :"+"Description :"+MyTmpTraining.getName()+" id :"+MyTmpTraining.getTraining_id());
                              // System.out.println("Résultat du Livedata:"+TmpListTraining.get(0));
                                model.setTrainingListTraining(MyTmpTraining,cptTraining);
                                model.setLocalTrainingFromRepository(MyTmpTraining,TmpUnit);
                                System.out.println("Observe"+model.getLocalTrainingsFromRepository());
                               cptTraining++;
                               //System.out.println("ATTTTTTTTTTTTTTTTTTTTTTTT : "+model.getTrainingListTraining(0));
                               System.out.println("Valeur du compteur des trainings : "+cptTraining);
                               //System.out.println("Résultat du livedata depuis le viewmodel avec id: "+model.getTrainingListTraining(0).getTraining_id()+" name: "+model.getTrainingListTraining(0).getName());
                               // model.setTrainingLocal(MyTmpTraining,TmpUnit);
                               //onPageSelected(1);//changement de page vers la page 2
                                System.out.println("En attente du changement de page");
                               //FirstTraining.setText("- id: "+model.getTrainingListTraining(0).getTraining_id()+" name: "+model.getTrainingListTraining(0).getName());
<<<<<<< HEAD
                               if (TmpListTraining.size() != cptTraining) {
                                   cptTraining--;
                                   SnackbarSame.show();
                               } else {
                                   SnackbarValide.show();
                               }
=======
>>>>>>> parent of 108e3f8 (with ui)

                               SnackbarValide.show();
                           } else {

                               SnackbarError.show();
                           }

                           break;

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




