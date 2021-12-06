package com.example.studyplannerapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.ViewHolder> {

    private final EventAssignment[] localDataSet;
    private TextView title;
    private TextView description;
    private TextView date;
    private TextView time;
    private final CustomAdapter2.RecyclerViewClickListener listener;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            view.setOnClickListener(this);
            textView = view.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter2(EventAssignment[] dataSet, CustomAdapter2.RecyclerViewClickListener listener) {
        localDataSet = dataSet;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.assignment_rv_layout, viewGroup, false);
        title = view.findViewById(R.id.assignment_title);
        description = view.findViewById(R.id.assignment_description);
        date = view.findViewById(R.id.assignment_date);
        time = view.findViewById(R.id.assignment_time);

        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        title.setText("Subject : "+localDataSet[position].getSubject());
        date.setText("Date : "+localDataSet[position].getDueDate());
        description.setText(localDataSet[position].getDescription());
        time.setText("End Time : "+localDataSet[position].getDueTime());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}

