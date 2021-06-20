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
import com.example.nesti_mes_recettes.entity.Preparation;
import com.example.nesti_mes_recettes.entity.Recipe;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import data.sqlite.TableCart;

public class PreparationAdapter extends ArrayAdapter<Preparation> {

    /**
     * @param context
     * @param textViewResourceId int
     * @param preparations
     */

    public PreparationAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Preparation> preparations) {
        super(context, textViewResourceId, preparations);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = convertView;
        if (convertView == null) {
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_preparation, parent, false);
        }
        Preparation one_preparation = getItem(position);

        TextView content = result.findViewById(R.id.preparation_textView_content);
        content.setText(one_preparation.getContent());

        // FloatingActionButton btn = result.findViewById(R.id.ingredient_floatingActionButton_shoppingCart);
        //btn.setOnClickListener(new View.OnClickListener() {
        //  @Override
        // public void onClick(View v) {
        //    try {
        //        addItem(one_ingredient);
        //   } catch (Exception e) {
        //       e.printStackTrace();
        //    }
        //   }
        //  });

        return result;
    }
}