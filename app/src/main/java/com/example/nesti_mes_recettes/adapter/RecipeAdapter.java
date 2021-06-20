package com.example.nesti_mes_recettes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nesti_mes_recettes.R;

import com.example.nesti_mes_recettes.entity.Recipe;

import java.util.ArrayList;

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    /**
     * @param context
     * @param textViewResourceId int
     * @param recipes
     */

    public RecipeAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Recipe> recipes) {
        super(context, textViewResourceId, recipes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = convertView;
        if (convertView == null) {
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_recipe, parent, false);
        }
        Recipe one_recipe = getItem(position);

        TextView title = result.findViewById(R.id.txt_title_recipe);
        title.setText(one_recipe.getTitle());

        TextView author = result.findViewById(R.id.txt_author_recipe);
        author.setText(one_recipe.getAuthor());

        ImageView imageView = result.findViewById(R.id.img_recipe);
        imageView.setImageResource(one_recipe.getImgId());

        ImageView imageStar = result.findViewById(R.id.img_star);
        imageStar.setImageResource(one_recipe.getImgStar());

        return result;

    }
}