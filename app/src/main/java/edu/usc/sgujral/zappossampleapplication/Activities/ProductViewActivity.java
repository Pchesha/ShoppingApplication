package edu.usc.sgujral.zappossampleapplication.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.usc.sgujral.zappossampleapplication.JavaBeanClasses.ProductItem;
import edu.usc.sgujral.zappossampleapplication.R;
import edu.usc.sgujral.zappossampleapplication.databinding.ActivityProductViewBinding;


public class ProductViewActivity extends AppCompatActivity {

    private Context context;
    private LinearLayout linearLayout;
    private Animation animate_fab;
    private FloatingActionButton fab;
    private static int count=0;
    SharedPreferences sharedPref;
    private TextView discountperentTextView;
    private TextView originalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_product_view);
        ActivityProductViewBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_product_view);
        context= this;
        ProductItem product= (ProductItem) this.getIntent().getSerializableExtra("product");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        discountperentTextView = (TextView) findViewById(R.id.productPercentOff);
        originalPriceTextView= (TextView) findViewById(R.id.productOriginalPrice);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        animate_fab = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_anim);
        toolbar.setTitle(product.getBrandName()+" "+ product.getProductName());
        setSupportActionBar(toolbar);
        ActionBar actionBar= this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
         sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        binding.setProduct(product);

        char [] discountpercent = product.getPercentOff().toCharArray();
        if(discountpercent[0]=='0'){
            discountperentTextView.setVisibility(View.GONE);
            originalPriceTextView.setVisibility(View.GONE);
        }
        else{
            originalPriceTextView.setPaintFlags(originalPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               fab.startAnimation(animate_fab);

                SharedPreferences.Editor editor = sharedPref.edit();
                int previousValue = sharedPref.getInt(getString(R.string.noOfItemsInCart), 0);
                editor.putInt(getString(R.string.noOfItemsInCart), previousValue+1);
                editor.commit();

                invalidateOptionsMenu();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_product_view, menu);
        return  true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.menu_cart);

        FrameLayout rootView = (FrameLayout)item.getActionView();
        TextView cartItemsTextView= (TextView) rootView.findViewById(R.id.cartItemsTextView);

        int count = sharedPref.getInt(getString(R.string.noOfItemsInCart), 0);
        cartItemsTextView.setText(""+count);

        return true;

    }

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
