package com.example.penumbra.hiporam;

import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.penumbra.hiporam.API.instagramapi;
import com.example.penumbra.hiporam.adapter.MyArrayAdapter;
import com.example.penumbra.hiporam.model.Datum;
import com.example.penumbra.hiporam.model.InstagramItem;
import com.example.penumbra.hiporam.model.PairPhotoItem;
import com.example.penumbra.hiporam.model.PhotoItem;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by penumbra on 06.09.2015.
 */
public class DataManager
{

    public static final String BASE_URL = "https://api.instagram.com/v1";
    public RestAdapter restAdapter;
    public instagramapi apiService;
    public InstagramItem latestSearchItem;
    private MainActivity mainActivity;
    MyArrayAdapter adapter;

    public DataManager(Activity activity)
    {
        this.mainActivity = (MainActivity) activity;
    }

    public void search(String tags)
    {

        apiService.getTag(tags, new Callback<InstagramItem>()
        {
            @Override
            public void success(InstagramItem instagramItem, Response response)
            {
                latestSearchItem = instagramItem;
                SharedData.getInstance().listItems.clear();
                loadImages(instagramItem.getData());
            }

            @Override
            public void failure(RetrofitError retrofitError)
            {
                Log.d("TAG", retrofitError.getMessage());
                Toast.makeText(mainActivity, "Search Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loadMore(String tag)
    {
        apiService.loadMore(tag, latestSearchItem.getPagination().getNextMaxTagId(), new Callback<InstagramItem>()
        {
            @Override
            public void success(InstagramItem instagramItem, Response response)
            {
                latestSearchItem = instagramItem;
                loadMoreImages(instagramItem.getData());
            }

            @Override
            public void failure(RetrofitError retrofitError)
            {
                Log.d("TAG", retrofitError.getMessage());
            }
        });

    }

    public void loadImages(List<Datum> list)
    {
        for (int i = 0; i < list.size();)
        {

            PhotoItem photoItem = new PhotoItem(list.get(i).getId(),list.get(i).getImages().getThumbnail().getUrl());
            PhotoItem photoItem2 = new PhotoItem(list.get(i+1).getId(),list.get(i+1).getImages().getThumbnail().getUrl());
            PairPhotoItem p = new PairPhotoItem(photoItem,photoItem2);
            SharedData.getInstance().listItems.add(p);
            i = i + 2;
        }

        adapter = new MyArrayAdapter(mainActivity,R.layout.listview_item,SharedData.getInstance().listItems);
        mainActivity.listView.setAdapter(adapter);
    }

    private void loadMoreImages(List<Datum> list)
    {
        if (list.size() == 0)
            Toast.makeText(mainActivity,"End of Content",Toast.LENGTH_LONG).show();

        for (int i = 0; i < list.size();)
        {
            PhotoItem photoItem = new PhotoItem(list.get(i).getId(),list.get(i).getImages().getThumbnail().getUrl());
            PhotoItem photoItem2 = new PhotoItem("Blank","http://www.suplugins.com/podium/images/placeholder-03.png");
            if (list.size() != i+1)
            {
                photoItem2.url = list.get(i+1).getImages().getThumbnail().getUrl();
            }

            PairPhotoItem p = new PairPhotoItem(photoItem,photoItem2);
            SharedData.getInstance().listItems.add(p);
            i = i + 2;
        }

        mainActivity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                adapter.notifyDataSetChanged();
            }
        });
    }

}
