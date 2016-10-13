package com.zettig.mifors;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.zettig.mifors.View.ItemListFragment;
import com.zettig.mifors.View.UrlFragment;



public class MainActivity extends AppCompatActivity implements ActivityCallback{

    Toolbar toolbar;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = getSharedPreferences(Const.PREFERENCES,MODE_PRIVATE);
        if (!preferences.contains(Const.SORT_PREFERENCES)){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(Const.SORT_PREFERENCES,Const.SORT_BY_NAME);
            editor.apply();
        }
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment==null) replaceFragment(new UrlFragment(),false);
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void showList(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("URL",url);
        Fragment fragment = new ItemListFragment();
        fragment.setArguments(bundle);
        replaceFragment(fragment,true);
    }
}
