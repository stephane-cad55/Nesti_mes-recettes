package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.nesti_mes_recettes.adapter.ShoppingCartAdapter;
import com.example.nesti_mes_recettes.entity.Ingredient;

import java.util.ArrayList;

import data.sqlite.TableCart;

public class ShoppingCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        TableCart model = new TableCart(this);
        ArrayList<Ingredient> items = model.getAllItems();

        ListView list_view = (ListView) this.findViewById(R.id.cart_listview_content);
        ShoppingCartAdapter adapter = new ShoppingCartAdapter(this, R.layout.line_shoppingcart,
                (ArrayList<Ingredient>) items);
        list_view.setAdapter(adapter);

        final Button btnEmpty = (Button) findViewById(R.id.cart_button_empty);
        btnEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ShoppingCartActivity.this);
                alertDialog.setTitle("Vider la liste");
                alertDialog.setMessage("Vous allez vider votre liste de course.");
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        model.removeAllItem();
                        list_view.removeAllViewsInLayout();
                    }
                });
                alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.show();
            }
        });
    }
}
