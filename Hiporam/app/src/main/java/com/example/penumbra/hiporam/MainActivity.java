package com.example.penumbra.hiporam;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.penumbra.hiporam.API.instagramapi;
import com.example.penumbra.hiporam.adapter.MyArrayAdapter;
import com.example.penumbra.hiporam.listener.MyOnScrollListener;
import com.example.penumbra.hiporam.model.Datum;
import com.example.penumbra.hiporam.model.InstagramItem;
import com.example.penumbra.hiporam.model.PairPhotoItem;
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
private static final String TAG = "MainActivityLog";
public static final String BASE_URL = "https://api.instagram.com/v1";
private ListView mainListView ;
private ArrayAdapter<String> listAdapter ;

private ListView listView;
InstagramItem latestSearchItem;
RestAdapter restAdapter;
instagramapi apiService;
String searchedTag = "snow";
MyArrayAdapter adapter;
ArrayList<PairPhotoItem> listItems = new ArrayList<PairPhotoItem>();

@Override
protected void onCreate(Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setUpActionBar();

    listView = (ListView) findViewById(R.id.list_view);

    restAdapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .build();
    apiService = restAdapter.create(instagramapi.class);

    listView.setOnScrollListener(new MyOnScrollListener()
    {
        @Override
        public void onLoadMore(int page, int totalItemsCount)
        {
            loadMore(searchedTag);
        }
    });
    final ImageView searchTrigger = (ImageView) findViewById(R.id.search_trigger);
    searchTrigger.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            searchAction();
        }
    });

    final EditText editText = (EditText) findViewById(R.id.search_field);
    editText.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent)
        {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO)
            {
                searchAction();
            }
            return false;
        }
    });
    editText.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
        @Override
        public void onFocusChange(View view, boolean b)
        {
            editText.post(new Runnable()
            {
                @Override
                public void run()
                {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                }
            });
        }
    });

    search(searchedTag);
}

private void searchAction()
{
    YoYo.with(Techniques.Shake).duration(800).playOn((ImageView) findViewById(R.id.search_trigger));
    EditText editText = (EditText) findViewById(R.id.search_field);
    searchedTag = editText.getText().toString();
    search(searchedTag);
}

public void search(String tags)
{

    apiService.getTag(tags, new Callback<InstagramItem>()
    {
        @Override
        public void success(InstagramItem instagramItem, Response response)
        {
            log("Search = " + response.getUrl());
            latestSearchItem = instagramItem;
            listItems.clear();
            loadImages(instagramItem.getData());
        }

        @Override
        public void failure(RetrofitError retrofitError)
        {
            Log.d("TAG",retrofitError.getMessage());
            Toast.makeText(MainActivity.this,"Search Failed",Toast.LENGTH_LONG).show();
        }
    });
}

private void loadMore(String tag)
{
    log(" --- LOAD MORE PAGINATION --- ");
    apiService.loadMore(tag, latestSearchItem.getPagination().getNextMaxTagId(), new Callback<InstagramItem>()
    {
        @Override
        public void success(InstagramItem instagramItem, Response response)
        {
            log("loadMore = " + response.getUrl());
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

private void loadImages(List<Datum> list)
{
    for (int i = 0; i < list.size();)
    {

        PhotoItem photoItem = new PhotoItem(list.get(i).getId(),list.get(i).getImages().getThumbnail().getUrl());
        PhotoItem photoItem2 = new PhotoItem(list.get(i+1).getId(),list.get(i+1).getImages().getThumbnail().getUrl());
        PairPhotoItem p = new PairPhotoItem(photoItem,photoItem2);
        listItems.add(p);
        i = i + 2;
    }

    adapter = new MyArrayAdapter(this,R.layout.listview_item,listItems);
    listView.setAdapter(adapter);
}

private void loadMoreImages(List<Datum> list)
{
    log("Size: " + list.size());
    if (list.size() == 0)
        Toast.makeText(MainActivity.this,"End of Content",Toast.LENGTH_LONG).show();

    for (int i = 0; i < list.size();)
    {
        PhotoItem photoItem = new PhotoItem(list.get(i).getId(),list.get(i).getImages().getThumbnail().getUrl());
        PhotoItem photoItem2 = new PhotoItem("Blank","http://www.suplugins.com/podium/images/placeholder-03.png");
        if (list.size() != i+1)
        {
            photoItem2.url = list.get(i+1).getImages().getThumbnail().getUrl();
        }

        PairPhotoItem p = new PairPhotoItem(photoItem,photoItem2);
        listItems.add(p);
        i = i + 2;
    }
    runOnUiThread(new Runnable()
    {
        @Override
        public void run()
        {
            adapter.notifyDataSetChanged();
        }
    });
}

private void log(String s)
{
    Log.d(TAG,s);
}

private void setUpActionBar()
{
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
