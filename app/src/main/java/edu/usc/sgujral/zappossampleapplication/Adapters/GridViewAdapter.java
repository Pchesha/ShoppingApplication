package edu.usc.sgujral.zappossampleapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.usc.sgujral.zappossampleapplication.JavaBeanClasses.GridItem;
import edu.usc.sgujral.zappossampleapplication.R;

/**
 * Created by sunakshigujral on 2/5/17.
 */
public class GridViewAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<GridItem> myDataset;
    private LayoutInflater inflater;
    private ItemClickedListener itemClickedListener;

    public GridViewAdapter(Context context, ArrayList<GridItem> myDataset, ItemClickedListener itemClickedListener){
        this.context= context;
        this.myDataset= myDataset;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemClickedListener = itemClickedListener;
    }
    @Override
    public int getCount() {
        return myDataset.size();
    }

    @Override
    public Object getItem(int position) {
        return (GridItem)myDataset.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_griditem, null);
        }

        TextView textView;
        ImageView imageView;

        imageView=(ImageView) convertView.findViewById(R.id.productImageview);
        textView=(TextView) convertView.findViewById(R.id.productNameTextview);

        if(myDataset.get(position).getImageID()!= 0){
            imageView.setImageResource(myDataset.get(position).getImageID());
        }
        if(myDataset.get(position).getName()!= null) {
            textView.setText(myDataset.get(position).getName());
        }
        // imageView.setImageResource(R.drawable.indian_food);
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                itemClickedListener.onFoodCategoryClicked(position);
            }
        });

        return convertView;
    }

    public void update(ArrayList<GridItem> items) {
        this.myDataset = items;
        notifyDataSetChanged();
    }
    public interface ItemClickedListener{
        public void onFoodCategoryClicked(int position);
    }
}
