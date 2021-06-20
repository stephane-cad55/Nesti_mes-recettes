package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nesti_mes_recettes.adapter.IngredientAdapter;
import com.example.nesti_mes_recettes.adapter.PreparationAdapter;
import com.example.nesti_mes_recettes.entity.Ingredient;
import com.example.nesti_mes_recettes.entity.Preparation;
import com.example.nesti_mes_recettes.model.IngredientViewModel;
import com.example.nesti_mes_recettes.model.PreparationViewModel;

import java.util.ArrayList;

public class PreparationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparation);

        final String idRecipe = getIntent().getStringExtra("idRecipe");

        // pour faire appel au model
        PreparationViewModel prepaViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(PreparationViewModel.class);
        TextView text_view_title = findViewById(R.id.preparation_textView_title);
        //afficher titre recette
        final String recipeName = getIntent().getStringExtra("recipeName");
        text_view_title.setText(recipeName);

        prepaViewModel.getPreparation(idRecipe).observe(this, preparation ->

        {
            ListView list_view = (ListView) findViewById(R.id.preparation_listview);
            PreparationAdapter preparationAdapter = new PreparationAdapter(this, R.layout.line_preparation, (ArrayList<Preparation>) preparation);
            list_view.setAdapter(preparationAdapter);

            list_view.setOnItemClickListener(((parent, view, position, id) -> {
                //to do new intent pour aller ??
            }));

        });
        ;
    }
}