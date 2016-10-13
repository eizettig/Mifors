package com.zettig.mifors.View;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zettig.mifors.Const;
import com.zettig.mifors.Model.Data.Group;
import com.zettig.mifors.Presenter.ItemListPresenter;
import com.zettig.mifors.Presenter.Presenter;
import com.zettig.mifors.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ItemListFragment extends android.support.v4.app.Fragment implements View {

    RecyclerView recyclerView;
    TextView empty_message;
    ProgressBar progressBar;
    ListAdapter adapter = new ListAdapter();
    Presenter presenter = new ItemListPresenter(this);
    List<Group> list;
    SharedPreferences preferences;
    private final String BUNDLE_KEY = "BUNDLE_LIST_KEY";
    private String url;

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        final String sort[]={Const.SORT_BY_NAME,Const.SORT_BY_ID};
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Sort:")
                .setItems(sort, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(Const.SORT_PREFERENCES,sort[which]);
                        editor.apply();
                        loadData();
                    }
                });
        alertDialog.show();

        return true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        url = this.getArguments().getString("URL");
        preferences= getActivity().getSharedPreferences(Const.PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (list!=null && !list.isEmpty()){
            outState.putSerializable(BUNDLE_KEY,new ArrayList<>(list));
        }
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.item_list_fragment,container,false);
        empty_message = (TextView)view.findViewById(R.id.empty_message);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),recyclerView,new RecyclerItemClickListener.OnItemClickListener(){
            @Override
            public void onItemClick(android.view.View view, int position) {

            }
        }));
        loadData();
        return view;
    }

    @Override
    public void showList(List<Group> list) {
        progressBar.setVisibility(android.view.View.INVISIBLE);
        recyclerView.setVisibility(android.view.View.VISIBLE);
        final Comparator comparator = new Comparator<Group>() {
            @Override
            public int compare(Group o1, Group o2) {
                if (preferences.getString(Const.SORT_PREFERENCES,"")==Const.SORT_BY_NAME){
                    return o1.getItemName().compareTo(o2.getItemName());
                }else {
                    return o1.getItemId()<o2.getItemId()?-1:1;
                }
            }
        };
        Collections.sort(list,comparator);
        adapter.showList(list);
    }
    public void loadData(){
        presenter.loadData(url);
    }

    @Override
    public void showError(String error) {
        progressBar.setVisibility(android.view.View.INVISIBLE);
        empty_message.setText(error);
        empty_message.setVisibility(android.view.View.VISIBLE);
    }
}
