package com.example.mathis.cleanapplication.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;


public class ListUserFragment extends Fragment implements View.OnClickListener, AddUserDialog.AddUserListener {

    MainActivity mainActivity;
    RecyclerView rv;
    WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;
    FloatingActionButton fab;
    ArrayList<UserModel> list;
    private final static String ARG_LIST = "list";
    Asker presenter;
    ListUserAdapter adapter;
    SharedPreferences sharedPreferences;

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

        mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) view.findViewById(R.id.main_swipe);
        mWaveSwipeRefreshLayout.setWaveARGBColor(255,63,81,181);
        mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Task().execute();
            }
        });

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = mainActivity.getPreferences(Context.MODE_PRIVATE);
        if(!sharedPreferences.contains("touchAddUser")){
            displayTuto(view, R.id.fab_add);
        }else{
            if(!sharedPreferences.getBoolean("touchAddUser",false)){
                displayTuto(view, R.id.fab_add);
            }
        }


    }

    public void displayTuto(View view, int id){
        new MaterialTapTargetPrompt.Builder(this.mainActivity)
                .setTarget(view.findViewById(id))
                .setPrimaryText("Ajouter un nouvel utilisateur")
                .setSecondaryText("Cliquer sur le bouton plus pour accéder au formulaire d'ajout d'un utilisateur")
                .setOnHidePromptListener(new MaterialTapTargetPrompt.OnHidePromptListener()
                {
                    @Override
                    public void onHidePrompt(MotionEvent event, boolean tappedTarget)
                    {

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("touchAddUser",true);
                        editor.commit();
                    }

                    @Override
                    public void onHidePromptComplete()
                    {
                        //display another tuto
                    }
                })
                .show();
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

    private class Task extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            presenter.updateViews();
            return null;
        }

        @Override protected void onPostExecute(String[] result) {
            // Call setRefreshing(false) when the list has been refreshed.
            mWaveSwipeRefreshLayout.setRefreshing(false);
            adapter.notifyDataSetChanged();
            super.onPostExecute(result);
        }
    }
}
