package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nesti_mes_recettes.adapter.RecipeAdapter;
import com.example.nesti_mes_recettes.entity.Recipe;
import com.example.nesti_mes_recettes.model.RecipesViewModel;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        String categoryName = getIntent().getStringExtra("CategoryName");
        TextView textView_title = (TextView) findViewById(R.id.category_textView_title);
        //int titleId =  getResources().getIdentifier("txttitle"+ categoryName,"string", getApplicationContext().getPackageName());
        textView_title.setText(categoryName);

        RecipesViewModel viewModel = new ViewModelProvider
                .AndroidViewModelFactory(this.getApplication())
                .create(RecipesViewModel.class);
        viewModel.setCategoryName(categoryName);
        viewModel.getRecipes().observe(this, recipes -> {
            // update UI
            ListView list_listView = (ListView) findViewById(R.id.category_listView);
            RecipeAdapter category_listView = new RecipeAdapter(this, R.layout.line_recipe, (ArrayList<Recipe>) recipes);
            list_listView.setAdapter(category_listView);

            list_listView.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent = new Intent(CategoryActivity.this, IngredientActivity.class);
                // on récupére la recette en cours
                final Recipe recipe = (Recipe) parent.getItemAtPosition(position);

                //on envoie l'id
                intent.putExtra("idRecipe", String.valueOf(recipe.getIdRecipe()));
                intent.putExtra("recipeName", String.valueOf(recipe.getTitle()));
                startActivity(intent);

            });
        });

        final Button btnBack = (Button) findViewById(R.id.btn_category_back);
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CategoryActivity.this.finish();
            }
        });
    }
}
