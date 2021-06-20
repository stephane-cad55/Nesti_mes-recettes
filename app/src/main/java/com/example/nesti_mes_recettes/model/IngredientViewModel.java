package com.example.nesti_mes_recettes.model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nesti_mes_recettes.R;
import com.example.nesti_mes_recettes.entity.Ingredient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IngredientViewModel extends AndroidViewModel
{
    public IngredientViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<List<Ingredient>> ingredients;


    public LiveData<List<Ingredient>> getIngredients(String idRecipe) {
        if (ingredients == null) {
            ingredients = new MutableLiveData<List<Ingredient>>();
            loadIngredients(idRecipe);
        }
        return ingredients;
    }

    private void loadIngredients(String idRecipe){
        String url = "https://cadeck.needemand.com/realisations/Projet_nesti_shop_CodeIgniter/public/index.php/api/recipes/" + idRecipe + "/ingredient";
        requestApi(url);
    }

    public void requestApi(String url){
        final RequestQueue request_queue = Volley.newRequestQueue(getApplication().getApplicationContext());
        JsonArrayRequest array_request = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JsonArrayToIngredient(response);
                        Log.i("NestiLog", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplication().getApplicationContext(),
                                "Une erreur est survenue à l'interrogation de l'API",
                                Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }
        );
        request_queue.add(array_request);
    }

    /**
     * @param jsonArray
     */
    public void JsonArrayToIngredient(JSONArray jsonArray) {

        ingredients.setValue(new ArrayList<>());
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Ingredient ing = new Ingredient();
                ing.setId(jsonObject.getInt("idIngredient"));
                ing.setName(jsonObject.getString("nameIngredient"));
                ing.setQtx(jsonObject.getString("quantity"));
                ing.setUnity(jsonObject.getString("nameUnit"));
                ingredients.getValue().add(ing);
            }
        } catch (Exception e) {
            Log.e("LogNesti", "Erreur de conversion du Json Ingredient");
            e.printStackTrace();
        }
    }
}
