package com.example.nesti_mes_recettes.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nesti_mes_recettes.R;
import com.example.nesti_mes_recettes.entity.Ingredient;

import java.util.ArrayList;

import data.sqlite.TableCart;

public class ShoppingCartAdapter extends ArrayAdapter<Ingredient> {

    public ShoppingCartAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Ingredient> ingredients) {
        super(context, textViewResourceId, ingredients);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = convertView;
        if (convertView == null) {
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_shoppingcart, parent, false);
        }
        Ingredient ingredient = getItem(position);


        //    TextView nameIngredient = result.findViewById(R.id.line_cart_txtView_ingredient);
        //   nameIngredient.setText(ingredient.getName());

        CheckedTextView checkedTextView = (CheckedTextView) result.findViewById(R.id.cart_checkedTextView);
        checkedTextView.setText(ingredient.getName());

        if (ingredient.getCheck() == 0) {
            Log.i("checkedTextView", "checkedTextView");
            checkedTextView.setChecked(false);
        } else {
            Log.i("checkedTextView else", "else");
            checkedTextView.setChecked(true);
        }

        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableCart tableCart = new TableCart(getContext());

                try {
                    if (checkedTextView.isChecked()) {

                        ingredient.setCheck(0);
                        tableCart.updateById(ingredient);
                        checkedTextView.setChecked(false);
                    } else {
                        ingredient.setCheck(1);
                        tableCart.updateById(ingredient);
                        checkedTextView.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return result;
    }
}
