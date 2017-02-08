package edu.usc.sgujral.zappossampleapplication.webservices;

import java.util.ArrayList;

import edu.usc.sgujral.zappossampleapplication.JavaBeanClasses.ProductItem;
import edu.usc.sgujral.zappossampleapplication.JavaBeanClasses.SearchResult;
import retrofit2.Response;

/**
 * Created by sunakshigujral on 1/30/17.
 */
public interface SearchResponseListener {
    public void onSearchResponse(Response<SearchResult> response);
}
