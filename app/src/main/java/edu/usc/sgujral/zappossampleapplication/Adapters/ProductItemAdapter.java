package edu.usc.sgujral.zappossampleapplication.Adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import edu.usc.sgujral.zappossampleapplication.BR;
import edu.usc.sgujral.zappossampleapplication.JavaBeanClasses.ProductItem;
import edu.usc.sgujral.zappossampleapplication.R;

/**
 * Created by sunakshigujral on 1/30/17.
 */
public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.BindingHolder> {


    public ProductItemAdapter(ArrayList<ProductItem> myDataset) {
        mDataset = myDataset;
    }

    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<ProductItem> mDataset;
    private MyClickListener myClickListener;
    private Context context;

    public ProductItemAdapter(Context context, ArrayList<ProductItem> foodItems, MyClickListener myClickListener) {
        this.context = context;
        this.mDataset = foodItems;
        this.myClickListener = myClickListener;

    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_productitem, parent, false);
        BindingHolder holder = new BindingHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductItemAdapter.BindingHolder holder, int position) {
        if (mDataset.get(position) != null) {
            ProductItem productItem = mDataset.get(position);
            holder.getBinding().setVariable(BR.product, productItem);
            holder.getBinding().executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void update(ArrayList<ProductItem> items) {
        this.mDataset = items;
        notifyDataSetChanged();
    }


    public  class BindingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ViewDataBinding binding;

        public BindingHolder(View rowView) {
            super(rowView);
            binding = DataBindingUtil.bind(rowView);
            rowView.setOnClickListener(this);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition());
        }
    }


    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public void addItem(ProductItem dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    public interface MyClickListener {
        public void onItemClick(int position);
    }

}
