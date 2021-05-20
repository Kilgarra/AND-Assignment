package com.example.and_assignment.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.and_assignment.R;
import com.example.and_assignment.model.Prescription;
import com.example.and_assignment.model.Vitamin;

import java.util.ArrayList;
import java.util.List;

public class VitaminAdapter extends RecyclerView.Adapter<VitaminAdapter.ViewHolder>{
    private ArrayList<Vitamin> vitamins;
    private final OnListItemClickListener mOnListItemClickListener;

    public VitaminAdapter(ArrayList<Vitamin> vitamins, OnListItemClickListener listener) {
        this.vitamins= vitamins;
        this.mOnListItemClickListener=listener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_main_recyclerview_item, parent, false);
        return new VitaminAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(VitaminAdapter.ViewHolder viewHolder, int position) {
        viewHolder.textView.setText(vitamins.get(position).getName());

    }


    public int getItemCount() {
        return vitamins.size();
    }
    public void updateData(List<Vitamin> vitamins){
        this.vitamins= (ArrayList<Vitamin>) vitamins;
        notifyDataSetChanged();
    }
    public interface OnListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }
    class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        ViewHolder(View itemView){
            super(itemView);
            textView=itemView.findViewById(R.id.recycler_view_item_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());


        }
    }
}
