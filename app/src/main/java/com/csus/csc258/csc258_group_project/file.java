package com.csus.csc258.csc258_group_project;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.*;

/**
 * Created by Yulong on 2016/2/4.
 */
public class file extends Fragment {
    View root_view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        root_view = inflater.inflate(R.layout.content_file,container, false);
        return root_view;
    }
}
