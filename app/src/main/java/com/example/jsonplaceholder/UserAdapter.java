package com.example.jsonplaceholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import com.example.jsonplaceholder.jsonplaceholder_class.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private final ArrayList<User> users;
    private OnClickItem onClickItem;

    public UserAdapter(ArrayList<User> users) {
        this.users = users;
    }


    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @NonNull
    @NotNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull @org.jetbrains.annotations.NotNull UserAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        holder.tvUserName.setText(user.getName());
        holder.tvUserLogin.setText(user.getUsername());
        if(user.getName() != null && !user.getName().isEmpty()) {
            String letter = String.valueOf(user.getName().charAt(0));
            holder.tvUserAvatar.setText(letter);
            int resId = getBackgroundResource(position);
            holder.tvUserAvatar.setBackgroundResource(resId);
        }
    }

    private  static final int[] RESOURCES = new int[]{R.drawable.shape_oval_red,
            R.drawable.shape_oval_orange,
            R.drawable.shape_oval_blue,
            R.drawable.shape_oval_green,
            R.drawable.shape_oval_purple,
    };

    private int getBackgroundResource(int position) {
        return RESOURCES[position % RESOURCES.length];
    }

    @Override
    public int getItemCount() {
        if(users == null) {
            return 0;
        } else {
            return users.size();
        }
    }

    //
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView tvUserName;
        private final TextView tvUserLogin;
        private final TextView tvUserAvatar;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvUserLogin = itemView.findViewById(R.id.tvUserLogin);
            tvUserAvatar = itemView.findViewById(R.id.tvUserAvatar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onClickItem != null) {
                User user = users.get(getAdapterPosition());
                int resId = getBackgroundResource(getAdapterPosition());
                onClickItem.onClickItem(user, resId);
            }
        }
    }

    //
    interface OnClickItem {
        void onClickItem(User user, int resId);
    }
}
