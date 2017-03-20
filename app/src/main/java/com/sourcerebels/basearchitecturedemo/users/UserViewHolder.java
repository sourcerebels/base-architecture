package com.sourcerebels.basearchitecturedemo.users;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sourcerebels.basearchitecturedemo.R;
import com.sourcerebels.basearchitecturedemo.model.User;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private TextView name;

    public UserViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.adapter_user_name_tv);
    }

    public void bind(User user) {
        name.setText(user.getName());
    }
}
