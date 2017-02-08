package edu.usc.sgujral.zappossampleapplication.webservices;

import edu.usc.sgujral.zappossampleapplication.JavaBeanClasses.ProductItem;
import edu.usc.sgujral.zappossampleapplication.JavaBeanClasses.SearchResult;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sunakshigujral on 1/30/17.
 */
public class RetrofitApiClient {

    public static final String BASE_URL = "https://api.zappos.com/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public interface ApiInterface {
        @GET("Search")
        Call<SearchResult> getSearchResult(@Query("term") String searchQuery, @Query("key") String apiKey );

    }
}
