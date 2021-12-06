package com.example.studyplannerapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.studyplannerapp.AssignmentTab;
import com.example.studyplannerapp.ExamTab;
import com.example.studyplannerapp.LectureTab;
import com.example.studyplannerapp.R;
import com.example.studyplannerapp.StudyTab;
import com.example.studyplannerapp.VPAdapter;
import com.example.studyplannerapp.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        ViewPager viewPager = (ViewPager) root.findViewById(R.id.viewpager);
        setUpViewPager(viewPager) ;

        TabLayout tabs = (TabLayout) root.findViewById(R.id.tabLayout) ;
        tabs.setupWithViewPager(viewPager);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setUpViewPager(ViewPager viewPager) {
        VPAdapter vpAdapter = new VPAdapter(getChildFragmentManager()) ;
        vpAdapter.addFragment(new StudyTab(),"Study");
        vpAdapter.addFragment(new AssignmentTab(),"Assignment");
        vpAdapter.addFragment(new ExamTab(),"Exam");
        vpAdapter.addFragment(new LectureTab(),"Lecture");

        viewPager.setAdapter(vpAdapter);
    }
}