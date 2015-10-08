package com.myroom.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;
import com.myroom.NewItemActivity;
import com.myroom.R;
import com.myroom.adapter.ItemListAdapter;
import com.myroom.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jobin on Aug 19.
 */
public class Purchases extends Fragment {
    List<Item> ItemList= new ArrayList<>();;
    List<String> allList;
    List<String> visibleList;
    RecyclerView recyclerView;
    ItemListAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_purchases, parentViewGroup, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.list);
        allList=new ArrayList<String>();
        allList.add("Eletricity");
        allList.add("Bisleri");
        allList.add("Internet");
        allList.add("Curry");
        allList.add("Beer");
        visibleList=new ArrayList<String>();
        visibleList.addAll(allList);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        adapter = new ItemListAdapter(getActivity(),visibleList);
        recyclerView.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent=new Intent(getActivity(),NewItemActivity.class);
                startActivity(addIntent);
            }
        });

       return rootView;
    }
    public void flushFilter(){
        visibleList=new ArrayList<>();
        visibleList.addAll(allList);
        adapter.notifyDataSetChanged();
    }

    public void setFilter(String queryText) {

        visibleList = new ArrayList<>();
        //String constraint = constraint.toString().toLowerCase();
        for (String item: allList) {
            if (item.toLowerCase().contains(queryText))
                visibleList.add(item);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}
