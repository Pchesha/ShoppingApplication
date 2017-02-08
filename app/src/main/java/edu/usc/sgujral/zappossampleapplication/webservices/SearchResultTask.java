package edu.usc.sgujral.zappossampleapplication.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import edu.usc.sgujral.zappossampleapplication.JavaBeanClasses.SearchResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sunakshigujral on 1/30/17.
 */
public class SearchResultTask {


    static ProgressDialog progressDialog;

    public static void getSearchResult(Context context, final SearchResponseListener searchResponseListener, String search) {

        progressDialog= new ProgressDialog(context);
        progressDialog.show();
        RetrofitApiClient.ApiInterface apiService =
                RetrofitApiClient.getClient().create(RetrofitApiClient.ApiInterface.class);

        Call<SearchResult> call = apiService.getSearchResult(search, "b743e26728e16b81da139182bb2094357c31d331");
        call.enqueue(new Callback<SearchResult>() {


            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {

                progressDialog.dismiss();
                searchResponseListener.onSearchResponse(response);
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                // Log error here since request failed
                progressDialog.dismiss();
                Log.e("Result", "fail");
            }
        });
    }
}
