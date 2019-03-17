package at.spengergasse.kai17521.lab09.controller;

import java.util.List;

import at.spengergasse.kai17521.lab09.model.Wassersport;
import at.spengergasse.kai17521.lab09.model.WassersportDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
  String BASE_URL = "http://www.kalliany.at/SPG/";

  @GET("getWassersport_withID.php")
  Call<WassersportDetail> loadWassersportDetail(@Query("id") String id);

  @GET("selWassersport.php")
  Call<List<Wassersport>> loadWassersportList();
}
