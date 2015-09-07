package com.example.penumbra.hiporam.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.penumbra.hiporam.MainActivity;
import com.example.penumbra.hiporam.R;
import com.example.penumbra.hiporam.model.PairPhotoItem;
import com.example.penumbra.hiporam.model.PhotoItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by penumbra on 04.09.2015.
 */
public class MyArrayAdapter extends ArrayAdapter<PairPhotoItem>
{
    MainActivity mainActivity;
    Context context;
    int resource;
    ArrayList<PairPhotoItem> pairPhotoItems;

    public MyArrayAdapter(Context context, int resource, ArrayList<PairPhotoItem> objects)
    {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.pairPhotoItems = objects;
    }

    @Override
    public View getView(int position,View view,ViewGroup parent)
    {
        if (view == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(resource,parent,false);
        }

        PhotoItem photoItem = pairPhotoItems.get(position).firstPhotoItem;
        PhotoItem photoItem2 = pairPhotoItems.get(position).secondPhotoItem;

        ImageView imageView = (ImageView) view.findViewById(R.id.list_item_image_left);
        Picasso.with(context).load(photoItem.url).fit().centerCrop().into(imageView);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.list_item_image_right);
        Picasso.with(context).load(photoItem2.url).fit().centerCrop().into(imageView2);
        TextView textViewLeft = (TextView) view.findViewById(R.id.list_item_image_left_text);
        textViewLeft.setText("Picture " + String.valueOf((position * 2) + 1));
        TextView textViewRight = (TextView) view.findViewById(R.id.list_item_image_right_text);
        textViewRight.setText("Picture " + String.valueOf((position * 2) + 2));
        int first = ((MainActivity)context).listView.getFirstVisiblePosition();
        int last = ((MainActivity)context).listView.getLastVisiblePosition();


        return view;
    }
}
