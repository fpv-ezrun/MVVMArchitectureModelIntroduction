package com.mindorks.framework.mvvm;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;

import androidx.room.Room;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import data.db.AppDatabase;
import data.db.entities.Training;
import data.remote.services.TrainingServices;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import okhttp3.HttpUrl;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TrainingViewModel model;
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    EditText EditId;
    EditText EditString;
    TextView MonText;
    Training MyTmpTraining;
    Continuation<?super Long> TmpLong;
    Continuation<? super Unit> TmpUnit;
    Continuation<? super List<? extends Training>> TmpList;
    Continuation<? super Training> TmpTraining;
    List<Training> TmpListTraining ;
    List<Training> ListwebTraining ;
    Training TrainingDelete;
    int Oldnumber;
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
        Snackbar SnackbarError = Snackbar.make(pager2,"Saisir un Training Valide !!!",2000); //initialisation des messages erreurs saisie
        Snackbar SnackbarValide = Snackbar.make(pager2,"Enregistrement du Training Réussi !!!",2000);//initialisation des messages d'enregistrement
        Snackbar SnackbarUpdate = Snackbar.make(pager2,"Rien a mettre a jour !!!",2000);
        Snackbar SnackbarSame = Snackbar.make(pager2,"Ce Training existe deja en db !!!, MaJ du Training",2000);
        Snackbar SnackbarIDDelete = Snackbar.make(pager2,"l'id n'existe pas !!!",2000);
        Snackbar SnackbarIDnullDelete = Snackbar.make(pager2,"Saisir un ID !!!",2000);
        Snackbar SnackbarIDDeleteOk = Snackbar.make(pager2,"Suppression du Training réussi !!!",2000);
        Snackbar SnackbarDeleteButton = Snackbar.make(pager2,"Récuperation en cour, merci de patienter",5000);
        Snackbar SnackbarUrlWeb = Snackbar.make(pager2, "Url vide, saisir un url correct", 2000);
        Snackbar SnackbarUrlWebinvalid = Snackbar.make(pager2,"Url invalid, reesayer ",2000);
        tabLayout = findViewById(R.id.tab_layout);
        database = Room.inMemoryDatabaseBuilder(getApplicationContext(),AppDatabase.class).allowMainThreadQueries().build();//initialisation de la db en local //TODO le refactoriser dans une classe d'initialisation
        System.out.println("Database initialisée, nom de la db : database");
        MonText = (TextView) findViewById(R.id.title_add);
        MyTmpTraining = new Training(1,"test");
        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);
        model.init(database.getTrainingDao());

        tabLayout.addTab(tabLayout.newTab().setText("Insert Trainings"));
        tabLayout.addTab(tabLayout.newTab().setText("View Trainings"));
        tabLayout.addTab(tabLayout.newTab().setText("Delete Trainings"));
        tabLayout.addTab(tabLayout.newTab().setText("Trainings From Web"));

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
           final Button delete = (Button) findViewById(R.id.buttonSupprimer);
           final Button getFromWeb = (Button) findViewById(R.id.buttonGetFromWeb);
           final EditText idDelete = (EditText) findViewById(R.id.deleteIdTraining);
           final EditText UrlApi = (EditText) findViewById(R.id.UrlApi);
           final TextView FirstTrainingweb = (TextView) findViewById(R.id.first_training_web);
           final TextView second_Trainingweb = (TextView) findViewById(R.id.second_training_web);
           final TextView third_Trainingweb = (TextView) findViewById(R.id.third_training_web);
           final TextView fourth_Trainingweb = (TextView) findViewById(R.id.fourth_training_web);
           final TextView fifth_Trainingweb = (TextView) findViewById(R.id.fifth_training_web);
           /*if(getFromWeb!=null){
               getFromWeb.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if(!UrlApi.getText().toString().equals("")) {
                           SnackbarDeleteButton.show();
                       }else {
                           SnackbarUrlWeb.show();
                       }
                   }
               });
           }*/
           if (getFromWeb!=null){
               getFromWeb.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if (android.os.Build.VERSION.SDK_INT > 9) {
                           StrictMode.ThreadPolicy policy =
                                   new StrictMode.ThreadPolicy.Builder().permitAll().build();
                           StrictMode.setThreadPolicy(policy);
                       }
                       System.out.println("demande de recuperation des trainings depuis une api sur le lien suivant:" + UrlApi.getText() + "\n");
                       if (UrlApi.getText().toString().contains("http")) {
                           ListwebTraining = (List<Training>) model.getTrainings(true, HttpUrl.get(UrlApi.getText().toString()), TmpList);
                           if (ListwebTraining != null) {
                               if (ListwebTraining.size() >= 1) {
                                   System.out.println("attention au bug " + ListwebTraining.size() + "\n");
                                   FirstTrainingweb.setText("- id: " + ListwebTraining.get(ListwebTraining.size() - 1).getTraining_id() + " name: " + ListwebTraining.get(ListwebTraining.size() - 1).getName());
                               } else {
                                   FirstTrainingweb.setText("");
                               }
                               if (ListwebTraining.size() >= 2) {
                                   second_Trainingweb.setText("- id: " + ListwebTraining.get(ListwebTraining.size() - 2).getTraining_id() + " name: " + ListwebTraining.get(ListwebTraining.size() - 2).getName());
                               } else {
                                   second_Trainingweb.setText("");
                               }
                               if (ListwebTraining.size() >= 3) {
                                   third_Trainingweb.setText("- id: " + ListwebTraining.get(ListwebTraining.size() - 3).getTraining_id() + " name: " + ListwebTraining.get(ListwebTraining.size() - 3).getName());
                               } else {
                                   third_Trainingweb.setText("");
                               }
                               if (ListwebTraining.size() >= 4) {
                                   fourth_Trainingweb.setText("- id: " + ListwebTraining.get(ListwebTraining.size() - 4).getTraining_id() + " name: " + ListwebTraining.get(ListwebTraining.size() - 4).getName());
                               } else {
                                   fourth_Trainingweb.setText("");
                               }
                               if (ListwebTraining.size() >= 5) {
                                   fifth_Trainingweb.setText("- id: " + ListwebTraining.get(ListwebTraining.size() - 5).getTraining_id() + " name: " + ListwebTraining.get(ListwebTraining.size() - 5).getName());
                               } else {
                                   fifth_Trainingweb.setText("");
                               }
                           }
                       }else{
                           SnackbarUrlWebinvalid.show();
                       }
                   }
               });
           }
           if (update!=null){
               update.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if(model.TmpTraining!=null || TmpListTraining!=null) {
                           if(TmpListTraining.size()>=1) {
                               //System.out.println("attention au bug "+ TmpListTraining.size()+"\n");
                               FirstTraining.setText("- id: " + TmpListTraining.get(TmpListTraining.size()-1).getTraining_id() + " name: " + TmpListTraining.get(TmpListTraining.size()-1).getName());
                           }else {
                               FirstTraining.setText("");
                           }
                           if(TmpListTraining.size()>=2) {
                               second_Training.setText("- id: " + TmpListTraining.get(TmpListTraining.size()-2).getTraining_id() + " name: " + TmpListTraining.get(TmpListTraining.size()-2).getName());
                           }else {
                               second_Training.setText("");
                           }
                           if(TmpListTraining.size()>=3) {
                               third_Training.setText("- id: " + TmpListTraining.get(TmpListTraining.size()-3).getTraining_id() + " name: " + TmpListTraining.get(TmpListTraining.size()-3).getName());
                           }else {
                               third_Training.setText("");
                           }
                           if(TmpListTraining.size()>=4) {
                               fourth_Training.setText("- id: " + TmpListTraining.get(TmpListTraining.size()-4).getTraining_id() + " name: " + TmpListTraining.get(TmpListTraining.size()-4).getName());
                           }else {
                               fourth_Training.setText("");
                           }
                           if(TmpListTraining.size()>=5) {
                               fifth_Training.setText("- id: " + TmpListTraining.get(TmpListTraining.size()-5).getTraining_id() + " name: " + TmpListTraining.get(TmpListTraining.size()-5).getName());
                           }else {
                               fifth_Training.setText("");
                           }
                           if(TmpListTraining!=null) {
                               if (TmpListTraining.size() != Oldnumber) {
                                   System.out.println("Affichage des 3, comparaison entre le premier et le dernier"+Oldnumber+cptTraining+TmpListTraining.size()+"\n");//Debug
                                   SnackbarUpdate.show();
                               }
                               Oldnumber++;
                           }
                       }else {
                           SnackbarUpdate.show();
                       }
                   }
               });
           }
           if (delete!=null){
               delete.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if(!idDelete.getText().toString().equals("")){
                           System.out.println("<<<<<<<<<<<<<"+idDelete.getText().toString()+">>>>>>>>>>>>>>");
                       if(model.getLocalTrainingsByIDFromRepository(Integer.parseInt(idDelete.getText().toString()),TmpTraining)!=null){
                           TrainingDelete= (Training) model.getLocalTrainingsByIDFromRepository(Integer.parseInt(idDelete.getText().toString()),TmpTraining);
                           model.deleteLocalTrainingFromRepository(TrainingDelete,TmpUnit);
                           SnackbarIDDeleteOk.show();
                           cptTraining = TmpListTraining.size()-1;//modification pour message maj training
                           Oldnumber = TmpListTraining.size()-1;//modification pour affichage de l'update
                           System.out.println("cptTraining:"+cptTraining+"\n");//Debug
                           TmpListTraining= (List<Training>) model.getTrainings(false,HttpUrl.get("http://localhost:3000/trainings"),TmpList);
                       }else {
                           System.out.println(TmpListTraining);
                           SnackbarIDDelete.show();
                       }
                       }else {
                           SnackbarIDnullDelete.show();
                       }
                   }
               });
           }
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
                                //model.setTrainingListTraining(MyTmpTraining,cptTraining);
                                model.setLocalTrainingFromRepository(MyTmpTraining,TmpUnit);
                                System.out.println("\nObserve"+model.getTrainings(false,HttpUrl.get("http://localhost:3000/trainings"),TmpList)+"\n");
                               TmpListTraining= (List<Training>) model.getTrainings(false,HttpUrl.get("http://localhost:3000/trainings"),TmpList);
                               System.out.println(TmpListTraining.get(0));
                                cptTraining++;
                               System.out.println("Valeur du compteur des trainings : "+cptTraining);
                               //System.out.println("Résultat du livedata depuis le viewmodel avec id: "+model.getTrainingListTraining(0).getTraining_id()+" name: "+model.getTrainingListTraining(0).getName());
                               // model.setTrainingLocal(MyTmpTraining,TmpUnit);
                               //onPageSelected(1);//changement de page vers la page 2
                                System.out.println("En attente du changement de page");
                               Oldnumber = TmpListTraining.size();
                               //FirstTraining.setText("- id: "+model.getTrainingListTraining(0).getTraining_id()+" name: "+model.getTrainingListTraining(0).getName());
                               if (TmpListTraining.size() != cptTraining) {
                                   cptTraining--;
                                   SnackbarSame.show();
                                   System.out.println("compteur avec message:"+cptTraining+"\n");
                               } else {
                                   SnackbarValide.show();
                                   System.out.println("compteur sans message:"+cptTraining+"\n");
                               }

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
    }



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




