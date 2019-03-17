package at.spengergasse.kai17521.lab09.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import at.spengergasse.kai17521.lab09.R;
import at.spengergasse.kai17521.lab09.controller.API;
import at.spengergasse.kai17521.lab09.model.Wassersport;
import at.spengergasse.kai17521.lab09.model.WassersportDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static at.spengergasse.kai17521.lab09.controller.API.BASE_URL;

public class DetailActivity extends AppCompatActivity implements Callback<WassersportDetail> {
  private static final String TAG = "DetailActivity";
  private TextView title;
  private TextView description;
  private TextView download;
  private ImageView image;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    Bundle params = getIntent().getExtras();
    if (params == null) return;
    String id = params.getString("id");

    Gson gson = new GsonBuilder().create();
    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build();
    API api = retrofit.create(API.class);
    Call<WassersportDetail> call = api.loadWassersportDetail(id);
    call.enqueue(this);

    title = findViewById(R.id.title);
    description = findViewById(R.id.description);
    download = findViewById(R.id.download);
    image = findViewById(R.id.image);
  }

  @Override
  public void onResponse(Call<WassersportDetail> call, Response<WassersportDetail> response) {
    WassersportDetail detail = response.body();
    setTitle(detail.getSportart());
    title.setText(String.format("%S: %s", detail.getDisziplin(), detail.getKurzbez()));
    description.setText(detail.getBeschreibung());
    new DownloadImage(image, download)
      .execute("http://www.kalliany.at/SPG/img/" + detail.getBild());
  }


  @Override
  public void onFailure(Call<WassersportDetail> call, Throwable t) {
    Log.e(TAG, t.getMessage());
  }

  public static class DownloadImage extends AsyncTask<String, Integer, Bitmap> {
    private ImageView imageView;
    private TextView downloadText;

    DownloadImage(ImageView imageView, TextView downloadText) {
      this.imageView = imageView;
      this.downloadText = downloadText;
    }

    @Override
    protected Bitmap doInBackground(String... args) {
      String urlString = args[0];
      try {
        URL url = new URL(urlString);
        InputStream in = url.openStream();
        BufferedInputStream buf = new BufferedInputStream(in);
        Bitmap bitmap = BitmapFactory.decodeStream(buf);
        in.close();
        buf.close();

        return bitmap;

      } catch (Exception e) {
        Log.e(TAG, e.toString());
      }

      return null;
    }

    protected void onPostExecute(Bitmap bitmap) {
      imageView.setImageBitmap(bitmap);
      downloadText.setText(String.format("Downloaded file size: %d bytes", bitmap.getByteCount()));
    }
  }
}
