package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nesti_mes_recettes.adapter.SearchAdapter;
import com.example.nesti_mes_recettes.entity.Recipe;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // réception du terme à rechercher
        String term = this.getIntent().getStringExtra("term");

        // Modification du textView
         TextView text_view = findViewById(R.id.result_txtview_term);
         text_view.setText(term);
         String url = "http://cadeck.needemand.com/realisations/Projet_nesti_shop_CodeIgniter/public/index.php/api/search/"+ term ;
         requestApi(url);
    }

    private void requestApi(String url) {
        //Log.i("LogNesti", "toto");
        final RequestQueue request_queue = Volley.newRequestQueue(getApplication().getApplicationContext());
        JsonArrayRequest array_request = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.i("LogNesti",  response.toString() );

                        ArrayList<Recipe> recipes = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object_JSON = response.getJSONObject(i);
                                Recipe r = new Recipe();
                                r.setIdRecipe(object_JSON.getString("idRecipe"));
                                r.setTitle(object_JSON.getString("title"));
                                r.setAuthor(object_JSON.getString("author"));
                                recipes.add(r);
                            }

                            ListView list_view = findViewById(R.id.result_listView);
                            SearchAdapter search_adapter = new SearchAdapter( ResultActivity.this, R.layout.line_search, recipes);
                            list_view.setAdapter(search_adapter);

                        } catch (Exception e) {
                            Log.e("LogNesti", "Erreur de Convertion du Json");
                            e.printStackTrace();
                        }
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
}

