package edu.usc.sgujral.zappossampleapplication.JavaBeanClasses;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sunakshigujral on 1/30/17.
 */
public class SearchResult {
    @SerializedName("results")
    private ArrayList<ProductItem> searchedItems;

    public ArrayList<ProductItem> getSearchedItems() {
        return searchedItems;
    }

    public void setSearchedItems(ArrayList<ProductItem> searchedItems) {
        this.searchedItems = searchedItems;
    }
}
