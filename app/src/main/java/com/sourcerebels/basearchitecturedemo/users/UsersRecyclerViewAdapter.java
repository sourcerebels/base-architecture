package com.sourcerebels.basearchitecturedemo.users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sourcerebels.basearchitecturedemo.R;
import com.sourcerebels.basearchitecturedemo.model.User;

import java.util.List;

public class UsersRecyclerViewAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<User> users;

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.adapter_user, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void add(User user) {
        users.add(0, user);
        notifyItemInserted(0);
    }
}
