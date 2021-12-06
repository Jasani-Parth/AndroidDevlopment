package com.example.studyplannerapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter3 extends RecyclerView.Adapter<CustomAdapter3.ViewHolder> {

    private final EventExam[] localDataSet;
    private TextView title;
    private TextView description;
    private TextView date;
    private TextView time;
    private TextView duration;
    private final CustomAdapter3.RecyclerViewClickListener listener;

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
    public CustomAdapter3(EventExam[] dataSet, CustomAdapter3.RecyclerViewClickListener listener) {
        localDataSet = dataSet;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.exam_rv_layout, viewGroup, false);
        title = view.findViewById(R.id.exam_title_tv);
        description = view.findViewById(R.id.exam_description_tv);
        date = view.findViewById(R.id.exam_date_tv);
        time = view.findViewById(R.id.exam_time_tv);
        duration = view.findViewById(R.id.exam_duration_tv);

        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        title.setText("Subject : "+localDataSet[position].getSubject());
        date.setText(localDataSet[position].getDate());
        description.setText(localDataSet[position].getDescription());
        time.setText(localDataSet[position].getTime());
        duration.setText("Duration : "+localDataSet[position].getExamDuration());
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


