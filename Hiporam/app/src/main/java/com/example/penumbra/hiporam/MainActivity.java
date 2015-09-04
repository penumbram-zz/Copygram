package com.example.penumbra.hiporam;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.penumbra.hiporam.API.instagramapi;
import com.example.penumbra.hiporam.adapter.MyArrayAdapter;
import com.example.penumbra.hiporam.model.Datum;
import com.example.penumbra.hiporam.model.InstagramItem;
import com.example.penumbra.hiporam.model.PhotoItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity
{
public static final String BASE_URL = "https://api.instagram.com/v1";
private ListView mainListView ;
private ArrayAdapter<String> listAdapter ;
@Override
protected void onCreate(Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setUpActionBar();

    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .build();

    instagramapi apiService =
            restAdapter.create(instagramapi.class);

    String tag = "snow";
    apiService.getTag(tag, new Callback<InstagramItem>()
    {
        @Override
        public void success(InstagramItem instagramItem, Response response)
        {
            loadImages(instagramItem.getData());
        }

        @Override
        public void failure(RetrofitError retrofitError)
        {
            Log.d("TAG",retrofitError.getMessage());
        }
    });





}

private void loadImages(List<Datum> list)
{
    log(list.get(0).getImages().getThumbnail().getUrl());
    PhotoItem[] photoItems = new PhotoItem[20];

    for (int i = 0; i < list.size(); i++)
    {
        PhotoItem photoItem = new PhotoItem(list.get(i).getId(),list.get(i).getImages().getThumbnail().getUrl());
        photoItems[i] = photoItem;
    }

    MyArrayAdapter adapter = new MyArrayAdapter(this,R.layout.listview_item,photoItems);

    ListView listView = (ListView) findViewById(R.id.list_view);
    listView.setAdapter(adapter);
}

private void log(String s)
{
    Log.d("TAG",s);
}

private void setUpActionBar()
{
/*    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayShowHomeEnabled(true);
    actionBar.setLogo(R.drawable.insta_logo);
    actionBar.setDisplayUseLogoEnabled(true);
    */
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayShowCustomEnabled(true);
    actionBar.setDisplayShowTitleEnabled(false);
    actionBar.setIcon(R.drawable.insta_logo);

    actionBar.setHomeButtonEnabled(false); // disable the button
    actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
    actionBar.setDisplayShowHomeEnabled(false); // remove the icon

    LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View v = inflator.inflate(R.layout.action_bar_custom, null);

    actionBar.setCustomView(v);
}

}
