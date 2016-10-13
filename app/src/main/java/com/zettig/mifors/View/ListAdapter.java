package com.zettig.mifors.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zettig.mifors.Model.Data.Group;
import com.zettig.mifors.R;
import java.util.ArrayList;
import java.util.List;



public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    List<Group> list = new ArrayList<>();

    public class ListViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView name;
        public ListViewHolder(View itemView) {
            super(itemView);
            id = (TextView)itemView.findViewById(R.id.id);
            name = (TextView)itemView.findViewById(R.id.name);
        }
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.name.setText(list.get(position).getItemName());
        holder.id.setText(String.valueOf(list.get(position).getItemId()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    protected void showList(List<Group> list){
        this.list = list;
        notifyDataSetChanged();
    }
}
