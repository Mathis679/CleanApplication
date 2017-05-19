package com.example.mathis.cleanapplication.adapters;

/**
 * Created by e-Conception on 16/05/2017.
 */


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.data.Provider;
import com.example.data.model.UserModel;
import com.example.mathis.cleanapplication.R;
import com.example.mathis.cleanapplication.viewmodels.UserViewModel;

import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.MyViewHolder> {

    private List<UserModel> list;

    public ListUserAdapter(List<UserModel> list){
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserModel userModel = list.get(position);
        holder.display(userModel);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView description;

        private UserViewModel currentUserViewModel;

        public MyViewHolder(final View itemView) {
            super(itemView);

            name = ((TextView) itemView.findViewById(R.id.name));
            description = ((TextView) itemView.findViewById(R.id.description));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currentUserViewModel.getDisplayName())
                            .setMessage(currentUserViewModel.getEmail())
                            .show();
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle("Supprimer une personne")
                            .setMessage("Are you sure?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Provider.getInstance().datas.removeUser(currentUserViewModel.getUserModel(),itemView.getContext());
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();

                    return true;
                }
            });
        }

        public void display(UserModel userModel) {
            currentUserViewModel = new UserViewModel(userModel);
            name.setText(currentUserViewModel.getDisplayName());
            description.setText(currentUserViewModel.getEmail());
        }
    }

    public void updateList(List<UserModel> list){
        this.list = list;
    }

    public int getLastItemId(){
        return this.list.get(list.size()-1).getId();
    }

}
