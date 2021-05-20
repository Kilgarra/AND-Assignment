package com.example.and_assignment.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.and_assignment.R;
import com.example.and_assignment.model.Prescription;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionsAdapter extends RecyclerView.Adapter<PrescriptionsAdapter.ViewHolder> {
    private ArrayList<Prescription> prescriptions;
    final private OnListItemClickListener mOnListItemClickListener;

    public PrescriptionsAdapter(ArrayList<Prescription> prescriptions, OnListItemClickListener listener) {
        this.prescriptions= prescriptions;
        mOnListItemClickListener=listener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_main_recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.textView.setText(prescriptions.get(position).getName());

    }

    public int getItemCount() {
        return prescriptions.size();
    }
    public void updateData(List<Prescription> prescriptions){
        this.prescriptions= (ArrayList<Prescription>) prescriptions;
        notifyDataSetChanged();
    }
    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
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
