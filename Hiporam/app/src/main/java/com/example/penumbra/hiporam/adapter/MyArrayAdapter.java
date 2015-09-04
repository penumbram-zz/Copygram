package com.example.penumbra.hiporam.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.penumbra.hiporam.R;
import com.example.penumbra.hiporam.model.PhotoItem;

/**
 * Created by penumbra on 04.09.2015.
 */
public class MyArrayAdapter extends ArrayAdapter<PhotoItem>
{
    Context context;
    int resource;
    PhotoItem[] photoItems;

    public MyArrayAdapter(Context context, int resource, PhotoItem[] objects)
    {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.photoItems = objects;
    }

    @Override
    public View getView(int position,View view,ViewGroup parent)
    {
        if (view == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(resource,parent,false);
        }

        PhotoItem photoItem = photoItems[position];

        ImageView imageView = (ImageView) view.findViewById(R.id.list_item_image);


        return view;
    }
}
