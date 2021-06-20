package com.example.nesti_mes_recettes.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nesti_mes_recettes.IngredientActivity;
import com.example.nesti_mes_recettes.R;
import com.example.nesti_mes_recettes.ResultActivity;
import com.example.nesti_mes_recettes.SearchActivity;
import com.example.nesti_mes_recettes.entity.Recipe;

import java.util.ArrayList;

public class SearchAdapter extends ArrayAdapter<Recipe> {
    public SearchAdapter(@NonNull Context context, int resource, ArrayList<Recipe> recipe) {
        super(context, resource, recipe);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = convertView;
        if (convertView == null) {
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_search, parent, false);
        }
        Recipe one_recipe = getItem(position);

        TextView title = result.findViewById(R.id.title_search);
        title.setText(one_recipe.getTitle());
        Button btn = result.findViewById(R.id.search_btn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), IngredientActivity.class);
                intent.putExtra("idRecipe", String.valueOf(one_recipe.getIdRecipe()));
                getContext().startActivity(intent);
            }
        });
        return result;
    }
}


