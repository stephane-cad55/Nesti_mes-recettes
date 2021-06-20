package com.example.nesti_mes_recettes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btnEasy = (Button) findViewById(R.id.btn_easy);
        btnEasy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Facile à faire");
                alertDialog.setMessage("Voulez vous choisir une recette facile à faire ?");
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(), btnEasy.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,  CategoryActivity.class);
                        intent.putExtra("CategoryName", "easy");
                        startActivity(intent);

                    }
                });
                alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //rien
                    }
                });
                alertDialog.show();
            }
        });

        final Button btnTrad = (Button) findViewById(R.id.btn_trad);
        btnTrad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Traditionnelle");
                alertDialog.setMessage("Voulez vous choisir une recette traditionnelle à faire ?");
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Toast.makeText(getApplicationContext(), btnTrad.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,  CategoryActivity.class);
                        intent.putExtra("CategoryName", "tradition");
                        startActivity(intent);

                    }
                });
                alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //rien
                    }
                });
                alertDialog.show();
            }
        });
        final Button btnSeason = (Button) findViewById(R.id.btn_season);
        btnSeason.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Recette de saison");
                alertDialog.setMessage("Voulez vous choisir une recette de saison  à faire ?");
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(), btnSeason.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,  CategoryActivity.class);
                        intent.putExtra("CategoryName", "season");
                        startActivity(intent);

                    }
                });
                alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //rien
                    }
                });
                alertDialog.show();
            }
        });
        final Button btnGluten = (Button) findViewById(R.id.btn_gluten);
        btnGluten.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Sans gluten");
                alertDialog.setMessage("Voulez vous choisir une recette sans gluten à faire ?");
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(), btnGluten.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                        intent.putExtra("CategoryName", "gluten");
                        startActivity(intent);

                    }
                });
                alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //rien
                    }
                });
                alertDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu pMenu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_general, pMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem pItem) {
        switch (pItem.getItemId()) {
            case R.id.menu_search:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_list:
                Intent listIntent = new Intent(MainActivity.this, ShoppingCartActivity.class);
                startActivity(listIntent);
                Toast t_list = Toast.makeText(this, "Menu : liste de course", Toast.LENGTH_SHORT);
                t_list.show();
                break;
            case R.id.menu_contact:
                Intent contactIntent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(contactIntent);
                Toast t_contact = Toast.makeText(this, "Menu : Contact", Toast.LENGTH_SHORT);
                t_contact.show();
                break;
            case R.id.menu_team:
                Intent teamIntent = new Intent(MainActivity.this, TeamActivity.class);
                startActivity(teamIntent);
                Toast t_team = Toast.makeText(this, "Menu : Equipe", Toast.LENGTH_SHORT);
                t_team.show();
                break;
            case R.id.menu_project:
                Intent projectIntent = new Intent(MainActivity.this, ProjectActivity.class);
                startActivity(projectIntent);
                Toast t_project = Toast.makeText(this, "Menu : projet", Toast.LENGTH_SHORT);
                t_project.show();
                break;
        }
        return true;
    }
}