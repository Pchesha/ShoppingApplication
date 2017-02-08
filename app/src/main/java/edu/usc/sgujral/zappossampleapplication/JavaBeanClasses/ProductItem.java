package edu.usc.sgujral.zappossampleapplication.JavaBeanClasses;

/**
 * Created by sunakshigujral on 1/30/17.
 *
 */

import android.databinding.BindingAdapter;
import android.os.Parcelable;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import edu.usc.sgujral.zappossampleapplication.R;

public class ProductItem implements Serializable {


    @SerializedName("brandName")
    private String brandName;

    @SerializedName("thumbnailImageUrl")
    private String itemImageUrl;

    @SerializedName("colorId")
    private int color;

    @SerializedName("originalPrice")
    private String originalPrice;

    @SerializedName("percentOff")
    private String percentOff;

    @SerializedName("price")
    private String discountedPrice;

    @SerializedName("productName")
    private String productName;

    @SerializedName("productUrl")
    private String productUrl;


    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
    }

    public String getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(String discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }


    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String itemImageUrl) {
        Picasso.with(view.getContext())
                .load(itemImageUrl)
                .placeholder(R.drawable.loadingeffect)
                .into(view);
    }
}
