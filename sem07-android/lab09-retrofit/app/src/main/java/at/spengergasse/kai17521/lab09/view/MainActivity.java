package at.spengergasse.kai17521.lab09.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import at.spengergasse.kai17521.lab09.R;
import at.spengergasse.kai17521.lab09.controller.API;
import at.spengergasse.kai17521.lab09.model.Wassersport;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static at.spengergasse.kai17521.lab09.controller.API.BASE_URL;

public class MainActivity extends AppCompatActivity implements Callback<List<Wassersport>> {
  private RecyclerView recyclerView;
  private RecyclerViewAdapter adapter;
  private static final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    this.recyclerView = findViewById(R.id.wassersport_list);
    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    Gson gson = new GsonBuilder().create();
    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build();
    API api = retrofit.create(API.class);
    Call<List<Wassersport>> call = api.loadWassersportList();
    call.enqueue(this);
  }

  @Override
  public void onResponse(Call<List<Wassersport>> call, Response<List<Wassersport>> response) {
    Log.d(TAG, response.toString());
    recyclerView.setAdapter(new RecyclerViewAdapter(response.body()));
  }

  @Override
  public void onFailure(Call<List<Wassersport>> call, Throwable t) {
    Log.e(TAG, t.getMessage());
  }
}
