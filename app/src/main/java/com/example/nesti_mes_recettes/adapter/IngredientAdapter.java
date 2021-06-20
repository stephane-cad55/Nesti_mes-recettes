package com.example.nesti_mes_recettes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nesti_mes_recettes.R;
import com.example.nesti_mes_recettes.entity.Ingredient;
import com.example.nesti_mes_recettes.entity.Recipe;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import data.sqlite.TableCart;

public class IngredientAdapter extends ArrayAdapter<Ingredient> {

    /**
     * @param context
     * @param textViewResourceId int
     * @param ingredients
     */

    public IngredientAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Ingredient> ingredients) {
        super(context, textViewResourceId, ingredients);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = convertView;
        if (convertView == null) {
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_ingredient, parent, false);
        }
        Ingredient one_ingredient = getItem(position);

        TextView nameIngredient = result.findViewById(R.id.ingredient_textView_name);
        nameIngredient.setText(one_ingredient.getName());

        TextView quantityIngredient = result.findViewById(R.id.ingredient_textView_quantity);
        quantityIngredient.setText(one_ingredient.getQtx());

        TextView unityIngredient = result.findViewById(R.id.ingredient_textView_unity);
        unityIngredient.setText(one_ingredient.getUnity());

        FloatingActionButton btn = result.findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addItem(one_ingredient);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return result;
    }

    /**
     * Add in database sqlite
     *
     * @param item
     */
    public void addItem(Ingredient item) {
        try {
            TableCart model = new TableCart(getContext());
            model.insertItem(item);
            Toast.makeText(getContext(), item.getName() + " ajouté au panier",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}