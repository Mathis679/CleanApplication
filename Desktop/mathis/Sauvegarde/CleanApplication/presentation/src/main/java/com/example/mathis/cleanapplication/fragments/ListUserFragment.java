package com.example.mathis.cleanapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.data.Asker;
import com.example.data.model.UserModel;
import com.example.mathis.cleanapplication.R;
import com.example.mathis.cleanapplication.activities.MainActivity;
import com.example.mathis.cleanapplication.adapters.ListUserAdapter;
import com.example.mathis.cleanapplication.dialogs.AddUserDialog;
import com.example.mathis.cleanapplication.presenters.ListUserPresenter;

import java.util.ArrayList;
import java.util.List;


public class ListUserFragment extends Fragment implements View.OnClickListener, AddUserDialog.AddUserListener {

    MainActivity mainActivity;
    RecyclerView rv;
    FloatingActionButton fab;
    ArrayList<UserModel> list;
    private final static String ARG_LIST = "list";
    Asker presenter;
    ListUserAdapter adapter;

    public ListUserFragment() {
    }


    public static ListUserFragment newInstance(ArrayList<UserModel> list) {
        ListUserFragment fragment = new ListUserFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_LIST, list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            list = (ArrayList<UserModel>)getArguments().getSerializable(ARG_LIST);
        }
        mainActivity = (MainActivity)this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        adapter = new ListUserAdapter(list);

        rv = (RecyclerView) view.findViewById(R.id.list);
        rv.setLayoutManager(new LinearLayoutManager(mainActivity));
        if(list!=null && list.size()!=0){
            rv.setAdapter(adapter);
        }else{
            Toast.makeText(mainActivity, "Pas de données à afficher",Toast.LENGTH_LONG).show();
        }

        fab = (FloatingActionButton) view.findViewById(R.id.fab_add);
        fab.setOnClickListener(this);

        mainActivity.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click","okaay");
                presenter.updateViews();
            }
        });

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void updateViews(List<UserModel> list){
        this.list = (ArrayList<UserModel>) list;
        adapter.updateList(list);
        adapter.notifyDataSetChanged();
    }

    public void onRefresh(){

    }

    public void setPresenter(Asker presenter){
        this.presenter = presenter;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fab_add:
                showDialogAddUser();
                break;
            default:
                Log.d(ListUserFragment.class.getName(),"Unhandled click");
        }
    }

    public void showDialogAddUser(){
        AddUserDialog addUserDialog = new AddUserDialog(mainActivity, this);
        addUserDialog.show();
    }

    @Override
    public void onCreatedUser(String firstname, String lastname) {
        String email = firstname + "@" + lastname + ".com";
        presenter.newUserAdded(new UserModel(adapter.getLastItemId()+1,email,firstname,lastname));
    }
}
