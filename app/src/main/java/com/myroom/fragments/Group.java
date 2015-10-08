package com.myroom.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.myroom.R;
import com.myroom.adapter.MembersListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jobin on Aug 19.
 */
public class Group extends Fragment {
    ListView membersListView;
    MembersListAdapter adapter;

    List<String> groupMembers;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group, parentViewGroup, false);
        membersListView = (ListView) rootView.findViewById(R.id.memberListView);
        groupMembers=new ArrayList<String>();
        groupMembers.add("Jobin George");
        groupMembers.add("Akhil Alex");
        adapter=new MembersListAdapter(getActivity(),groupMembers);
        membersListView.setAdapter(adapter);
        return rootView;
    }
}
