package com.example.studyplannerapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudyTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudyTab extends Fragment {

    RecyclerView re;
    List<EventStudy> list;
    CustomAdapter1 ad;
    private CustomAdapter1.RecyclerViewClickListener listener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudyTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudyTab.
     */
    // TODO: Rename and change types and number of parameters
    public static StudyTab newInstance(String param1, String param2) {
        StudyTab fragment = new StudyTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_study_tab, container, false);
        DBHandlerStudy db = new DBHandlerStudy(view.getContext(), "DataBaseStudyEvent",null,1);
        list = db.getAllEvents();
        EventStudy[] arr = new EventStudy[list.size()];
        arr = list.toArray(arr);
        re = view.findViewById(R.id.recycler_view1);
        re.setLayoutManager(new LinearLayoutManager(view.getContext()));
        setOnClickListener();
        ad = new CustomAdapter1(arr,listener);
        re.setAdapter(ad);
        return view;
    }

    private void setOnClickListener() {
        listener = new CustomAdapter1.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                DBHandlerStudy db = new DBHandlerStudy(v.getContext(), "DataBaseStudyEvent",null,1);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Do you want to delete ?");
                builder.setMessage("Press ok to delete, if you want to cancel press cancel");
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteEvent(list.get(position).getID());
                        Toast.makeText(v.getContext(),"Deleted Successfully",Toast.LENGTH_SHORT).show();
                        list = db.getAllEvents();
                        EventStudy[] arr = new EventStudy[list.size()];
                        arr = list.toArray(arr);
                        ad = new CustomAdapter1(arr,listener);
                        re.setAdapter(ad);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(v.getContext(),"Deletion Failed",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };
    }

}