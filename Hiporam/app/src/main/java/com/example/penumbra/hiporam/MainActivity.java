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
import android.widget.SearchView;


public class MainActivity extends ActionBarActivity
{

@Override
protected void onCreate(Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setUpActionBar();
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
