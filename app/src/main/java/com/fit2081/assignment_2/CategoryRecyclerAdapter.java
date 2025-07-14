package com.fit2081.assignment_2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fit2081.assignment_2.entities.EventCategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder> {

    private List<EventCategory> data = new ArrayList<>();
    private final int HEADER_TYPE = 0;
    private final int ITEM_TYPE = 1;
    private Context context;

    public CategoryRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<EventCategory> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if(viewType == HEADER_TYPE){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_header, parent, false); // CardView inflated as RecyclerView list item
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false); // CardView inflated as RecyclerView list item
        }
        ViewHolder viewHolder = new ViewHolder(v);
        Log.d("Assignment-AK", "onCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position == 0){
            holder.textViewCategoryId.setText("Id");
            holder.textViewName.setText("Name");
            holder.textViewCount.setText("Event Count");
        } else {
            EventCategory eventCategory = data.get(position-1);
            holder.textViewCount.setText(String.valueOf(eventCategory.getEventCount()));
            holder.textViewName.setText(String.valueOf(eventCategory.getName()));
            holder.textViewCategoryId.setText(eventCategory.getCategoryId());
            holder.isActive.setText(eventCategory.isActive() ? "Yes" : "No");

            // Set the click listener to launch GoogleMapActivity
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, GoogleMapActivity.class);
                intent.putExtra("location", eventCategory.getEventLocation());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER_TYPE;
        else
            return ITEM_TYPE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewCategoryId;
        public TextView textViewCount;
        public TextView textViewName;
        public TextView isActive;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCategoryId = itemView.findViewById(R.id.card_category_id);
            textViewCount = itemView.findViewById(R.id.card_event_count);
            textViewName = itemView.findViewById(R.id.card_name);
            isActive = itemView.findViewById(R.id.card_is_active);
        }
    }
}
