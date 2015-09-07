package com.example.penumbra.hiporam;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.penumbra.hiporam.API.instagramapi;
import com.example.penumbra.hiporam.listener.MyOnScrollListener;

import retrofit.RestAdapter;


public class MainActivity extends ActionBarActivity
{
private static final String TAG = "MainActivityLog";
public ListView listView;
String searchedTag = "snow";
DataManager dataManager;

@Override
protected void onCreate(Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setUpActionBar();
    listView = (ListView) findViewById(R.id.list_view);

    dataManager = new DataManager(this);

    dataManager.restAdapter = new RestAdapter.Builder()
            .setEndpoint(dataManager.BASE_URL)
            .build();
    dataManager.apiService = dataManager.restAdapter.create(instagramapi.class);

    listView.setOnScrollListener(new MyOnScrollListener() {
        @Override
        public void onLoadMore(int page, int totalItemsCount) {
            dataManager.loadMore(searchedTag);
        }
    });
    final ImageView searchTrigger = (ImageView) findViewById(R.id.search_trigger);
    searchTrigger.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            searchAction();
        }
    });

    final EditText editText = (EditText) findViewById(R.id.search_field);
    editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                searchAction();
                return true;
            }
            return false;
        }
    });

    dataManager.search(searchedTag);
}

private void searchAction()
{
    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    YoYo.with(Techniques.Shake).duration(800).playOn((ImageView) findViewById(R.id.search_trigger));
    EditText editText = (EditText) findViewById(R.id.search_field);
    searchedTag = editText.getText().toString();
    dataManager.search(searchedTag);
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
