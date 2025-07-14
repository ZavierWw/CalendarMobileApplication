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

import com.fit2081.assignment_2.entities.Event;

import java.util.ArrayList;
import java.util.List;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder> {

    private List<Event> data = new ArrayList<>();
    private Context context;

    public EventRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Event> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_event, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        Log.d("Assignment-AK", "onCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = data.get(position);
        holder.textViewId.setText(event.getEventId());
        holder.textViewTickets.setText(String.valueOf(event.getTicketsAvailable()));
        holder.textViewName.setText(String.valueOf(event.getName()));
        holder.textViewCategoryId.setText(event.getCategoryId());
        holder.isActive.setText(event.isActive() ? "Active" : "Inactive");

        // Set the click listener to launch EventGoogleResult
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EventGoogleResult.class);
            intent.putExtra("eventName", event.getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewId;
        public TextView textViewCategoryId;
        public TextView textViewTickets;
        public TextView textViewName;
        public TextView isActive;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.card_event_id);
            textViewCategoryId = itemView.findViewById(R.id.card_category_id);
            textViewTickets = itemView.findViewById(R.id.card_event_tickets);
            textViewName = itemView.findViewById(R.id.card_event_name);
            isActive = itemView.findViewById(R.id.card_is_active);
        }
    }
}
