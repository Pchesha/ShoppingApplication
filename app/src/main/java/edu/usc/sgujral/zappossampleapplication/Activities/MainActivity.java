package edu.usc.sgujral.zappossampleapplication.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;

import java.util.ArrayList;

import edu.usc.sgujral.zappossampleapplication.Adapters.GridViewAdapter;
import edu.usc.sgujral.zappossampleapplication.JavaBeanClasses.GridItem;
import edu.usc.sgujral.zappossampleapplication.JavaBeanClasses.ProductItem;
import edu.usc.sgujral.zappossampleapplication.JavaBeanClasses.SearchResult;
import edu.usc.sgujral.zappossampleapplication.R;
import edu.usc.sgujral.zappossampleapplication.webservices.SearchResponseListener;
import edu.usc.sgujral.zappossampleapplication.webservices.SearchResultTask;
import retrofit2.Response;

/**
 * Created by sunakshigujral on 2/4/17.
 */
public class MainActivity extends AppCompatActivity implements SearchResponseListener,View.OnClickListener , GridViewAdapter.ItemClickedListener{
    private SearchResponseListener searchResponseListener;
    private Toolbar toolbar;
    private FrameLayout shoeFrameLayout;
    private FrameLayout bagFrameLayout;
    private FrameLayout watchFrameLayout;
    private Button searchButton;
    private EditText searchEditText;
    private boolean isSearched= false;
    private GridViewAdapter gridViewAdapter;
    private GridViewAdapter.ItemClickedListener itemClickedListener;
    private ArrayList<GridItem> gridItems;
    private GridView gridView;
    private String searchString =null;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        context= this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        searchButton= (Button) findViewById(R.id.search_btn);
        searchEditText= (EditText) findViewById(R.id.search_text_view);
        gridView = (GridView) findViewById(R.id.productGridView);

        setToolbar();

        gridItems= new ArrayList<GridItem>();
        gridItems.add(new GridItem("Shoes", R.drawable.shoes_label));
        gridItems.add(new GridItem("Bags",R.drawable.bags_label));
        gridItems.add(new GridItem("Watches",R.drawable.watches_label));
        gridItems.add(new GridItem("Clothing",R.drawable.clothing_label));
        gridViewAdapter= new GridViewAdapter(this, gridItems, itemClickedListener);
        gridView.setAdapter(gridViewAdapter);


        searchResponseListener= this;
        itemClickedListener = this;
        searchButton.setOnClickListener(this);


    }
    private void setToolbar() {
        setSupportActionBar(toolbar);
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id){
            case R.id.search_btn: isSearched= true;
                displaySearchResults(searchEditText.getText().toString());
                break;
        }
    }

    @Override
    public void onSearchResponse(Response<SearchResult> response) {
       ArrayList<ProductItem> productItems = response.body().getSearchedItems();
        if(productItems.size()>0) {
            if (isSearched) {
                isSearched = false;

                Intent intent = new Intent(this, ProductViewActivity.class);
                intent.putExtra("product", productItems.get(0));
                this.startActivity(intent);
            } else {
                Intent intent = new Intent(this, ProductListActivity.class);
                intent.putExtra("productList", productItems);
                intent.putExtra("title", searchString);
                this.startActivity(intent);
            }
        }   else{
                new AlertDialog.Builder(context)
                        .setTitle("Alert")
                        .setMessage(context.getResources().getString(R.string.noSearchFound))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

    }

    private void displaySearchResults(String s) {
        searchString = s;
        SearchResultTask.getSearchResult(this,searchResponseListener, s);
    }

    @Override
    public void onFoodCategoryClicked(int position) {
        displaySearchResults(gridItems.get(position).getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(searchEditText!=null) {
            searchEditText.clearFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
        }
    }
}
