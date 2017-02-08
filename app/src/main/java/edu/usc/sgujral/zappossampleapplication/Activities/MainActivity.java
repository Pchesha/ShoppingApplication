package edu.usc.sgujral.zappossampleapplication.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.usc.sgujral.zappossampleapplication.Adapters.ProductItemAdapter;
import edu.usc.sgujral.zappossampleapplication.JavaBeanClasses.ProductItem;
import edu.usc.sgujral.zappossampleapplication.JavaBeanClasses.SearchResult;
import edu.usc.sgujral.zappossampleapplication.R;
import edu.usc.sgujral.zappossampleapplication.webservices.SearchResponseListener;
import edu.usc.sgujral.zappossampleapplication.webservices.SearchResultTask;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchResponseListener, ProductItemAdapter.MyClickListener {

    private Toolbar toolbar;
    private SearchResponseListener searchResponseListener;
    private ArrayList<ProductItem> productItems;
    private ProductItemAdapter productItemAdapter;
    private ProductItemAdapter.MyClickListener myClickListener;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private SearchView searchView;
    private LinearLayout linearLayout;
    private TextView textView;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.labelLinearLayout);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView = (TextView) findViewById(R.id.alertTextView);

        productItems= (ArrayList<ProductItem>) this.getIntent().getSerializableExtra("productList");
        title = this.getIntent().getStringExtra("title");

        setToolbar();

        searchResponseListener= this;
        myClickListener= this;

        productItemAdapter = new ProductItemAdapter(this, productItems, myClickListener);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(productItemAdapter);
    }

    private void setToolbar() {

        //toolbar.setTitle("ILoveZappos");
        if(title!=null)
            toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        ActionBar actionBar= this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    public void onSearchResponse(Response<SearchResult> response) {
        Log.d("Result in Main", response.toString());
        Log.d("Result in Main", ""+response.body().getSearchedItems().size());
        Log.d("Result in Main", response.body().getSearchedItems().get(0).getBrandName());
        productItems = response.body().getSearchedItems();
        //productGridViewAdapter.update(productItems);
        productItemAdapter.update(productItems);

        if(productItems.size()>0){
            linearLayout.setVisibility(View.GONE);
        }
        else{
            textView.setText(getResources().getString(R.string.noSearchFound));
        }
    }

    @Override
    public void onItemClick(int position) {

        Intent intent= new Intent(this, ProductViewActivity.class);
        intent.putExtra("product",productItems.get(position));
        this.startActivity(intent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//
//        //Hide keypad
//        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
////            searchView.clearFocus();
////            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
////            imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
//
//        //Query change listener for searchview
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                displaySearchResults(s);
//                searchView.clearFocus();
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                // queryChangeListener.onQueryChanged(s);
//                return false;
//            }
//        });
//
//        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                return false;
//            }
//        });
//        return true;
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(searchView!=null){
//            searchView.clearFocus();
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
//        }
//    }
//
//
//    private void displaySearchResults(String s) {
//        Log.d("Searching","");
//        SearchResultTask.getSearchResult(this,searchResponseListener, s);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);

    }


}
