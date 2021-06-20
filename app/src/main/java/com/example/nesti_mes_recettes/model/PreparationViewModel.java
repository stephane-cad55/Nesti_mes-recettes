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
import com.example.nesti_mes_recettes.entity.Preparation;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PreparationViewModel extends AndroidViewModel
{
    public PreparationViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<List<Preparation>> preparations;


    public LiveData<List<Preparation>> getPreparation(String idRecipe) {
        if (preparations == null) {
            preparations = new MutableLiveData<List<Preparation>>();
            loadPreparation(idRecipe);
        }
        return preparations;
    }

    private void loadPreparation(String idRecipe){
        String url = "https://cadeck.needemand.com/realisations/Projet_nesti_shop_CodeIgniter/public/index.php/api/recipes/" + idRecipe + "/preparation";
        requestApi(url);
    }

    public void requestApi(String url){
        final RequestQueue request_queue = Volley.newRequestQueue(getApplication().getApplicationContext());
        JsonArrayRequest array_request = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JsonArrayToPreparation(response);
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
    public void JsonArrayToPreparation(JSONArray jsonArray) {

        preparations.setValue(new ArrayList<>());
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Preparation preparation = new Preparation();

                preparation.setContent(jsonObject.getString("content"));
                preparations.getValue().add(preparation);
            }
        } catch (Exception e) {
            Log.e("LogNesti", "Erreur de conversion du Json Preparation");
            e.printStackTrace();
        }


        //      ListView listView = (ListView) getApplication().findViewById(R.id.gluten_listView);

        //       RecipeAdapter adapter = new RecipeAdapter(this, R.layout.line_recipe, recipes);
//        listView.setAdapter(adapter);
    }
}
