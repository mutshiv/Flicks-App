package za.gov.parliament.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import za.gov.parliament.flicks.adapters.MovieAdapter;
import za.gov.parliament.flicks.models.ApiResult;
import za.gov.parliament.flicks.models.Movie;

public class MainActivity extends AppCompatActivity {

    private String url;
    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView mRecyclerView;
    MovieAdapter mAdapater;
    ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_movies);
        mAdapater = new MovieAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapater);

        pbLoading = (ProgressBar)findViewById(R.id.pb_loading);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RequestQueue queue = Volley.newRequestQueue(this);
        url = "https://parse.peruzal.com/parse/classes/Movie";

        pbLoading.setVisibility(View.VISIBLE);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               // Log.d(TAG, response.toString());
                pbLoading.setVisibility(View.INVISIBLE);
                ApiResult apiResult = new Gson().fromJson(response.toString(), ApiResult.class);

                updateUi(apiResult.movies);
                Log.d(TAG, "Total Movies : " + apiResult.movies.size());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pbLoading.setVisibility(View.INVISIBLE);
                Log.d(TAG, error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();

                params.put("X-Parse-Application-Id", "70Ua6d7FXrD9xyUHHzlb2y7dI1V50G0G");
                return params;
            }
        };

        queue.add(jsonObjectRequest);
    }

    private void updateUi(List<Movie> movies) {
        mAdapater.addMovies(movies);
    }
}
