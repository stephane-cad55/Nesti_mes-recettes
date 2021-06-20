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
import com.example.nesti_mes_recettes.entity.Recipe;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipesViewModel extends AndroidViewModel {

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    private String categoryName;

    private MutableLiveData<List<Recipe>> recipes;

    public RecipesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Recipe>> getRecipes() {
        if (recipes == null) {
            recipes = new MutableLiveData<List<Recipe>>();
            loadRecipes();
        }
        return recipes;
    }

    private void loadRecipes() {
        String url = "https://cadeck.needemand.com/realisations/Projet_nesti_shop_CodeIgniter/public/index.php/api/category/"+ getCategoryName();
        requestApi(url);
    }

    private void requestApi(String url) {

        final RequestQueue request_queue = Volley.newRequestQueue(getApplication().getApplicationContext());
        JsonArrayRequest array_request = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.i("LogNesti", response[0]);
                        ArrayList<Recipe> recipes = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object_JSON = response.getJSONObject(i);
                                Recipe r = new Recipe();
                                r.setIdRecipe(object_JSON.getString("idRecipe"));
                                r.setTitle(object_JSON.getString("title"));
                                r.setDifficulty(object_JSON.getInt("diff"));
                                int z = getRessourceImage("star_" + r.getDifficulty());
                                int x = getRessourceImage(object_JSON.getString("img"));
                                r.setImgId(x);
                                r.setImgStar(z);
                                r.setAuthor(object_JSON.getString("author"));

                                recipes.add(r);
                            }

                        } catch (Exception e) {
                            Log.e("LogNesti", "Erreur de Convertion du Json");
                            e.printStackTrace();
                        }

                        RecipesViewModel.this.recipes.setValue(recipes);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(
                                getApplication().getApplicationContext(),
                                "Une erreur est survenue sur l'interrogation de l'API",
                                Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }
        );
        request_queue.add(array_request);
    }

    private int getRessourceImage(String nameImage) {
        String path = getApplication().getPackageName() + ":drawable/" + nameImage;
        return getApplication().getResources().getIdentifier(path, null, null);
    }
}
