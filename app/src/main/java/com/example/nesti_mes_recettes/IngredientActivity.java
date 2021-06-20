package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nesti_mes_recettes.adapter.IngredientAdapter;
import com.example.nesti_mes_recettes.entity.Ingredient;
import com.example.nesti_mes_recettes.model.IngredientViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class IngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);

        //je la récupère car envoyée depuis category activity
        final String idRecipe = getIntent().getStringExtra("idRecipe");

        // pour faire appel au model
        IngredientViewModel ingViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(IngredientViewModel.class);
        TextView text_view_title = findViewById(R.id.ingredient_textView_title);
        final String recipeName = getIntent().getStringExtra("recipeName");
        text_view_title.setText(recipeName);

        ingViewModel.getIngredients(idRecipe).observe(this, ingredients ->

        {
            ListView list_view = (ListView) findViewById(R.id.ingredient_listView);
            IngredientAdapter ingredientAdapter = new IngredientAdapter(this, R.layout.line_ingredient, (ArrayList<Ingredient>) ingredients);
            list_view.setAdapter(ingredientAdapter);

            list_view.setOnItemClickListener(((parent, view, position, id) -> {
                //to do new intent pour aller ??
            }));

        });
        ;

        final Button btnPreparation = (Button) findViewById(R.id.ingredient_btn_preparation);
        btnPreparation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IngredientActivity.this, PreparationActivity.class);//lancer activité preparation
                //on envoie l'id
                intent.putExtra("idRecipe", idRecipe);
                intent.putExtra("recipeName", recipeName);
                startActivity(intent);
            }
        });

        final FloatingActionButton btnShoppingCart = (FloatingActionButton) findViewById(R.id.ingredient_floatingActionButton_shoppingCart);
        btnShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IngredientActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });

    }
}