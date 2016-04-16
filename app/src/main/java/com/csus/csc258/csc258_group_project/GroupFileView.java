package com.csus.csc258.csc258_group_project;

import android.app.Fragment;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.*;

/**
 * Created by Yulong on 2016/2/4.
 */
public class GroupFileView extends Fragment implements View.OnClickListener {
    View root_view;

    private ArrayList<Button> bDeleteButtons;
    private Group grp;
    //private EditText addTextToFile;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root_view = inflater.inflate(R.layout.content_file, container, false);
        bDeleteButtons = new ArrayList<>();
        bDeleteButtons.add((Button)root_view.findViewById(R.id.delfilebtn));
        Button b = (Button) root_view.findViewById(R.id.addfilebtn);
        b.setOnClickListener(this);

        MainActivity activity = (MainActivity) getActivity();
        for (GroupFile f : activity.getGroupFiles()) {
            createFile(f.getFileName(), f.getFileId());
        }
        return root_view;
    }

    @Override
    public void onClick(View v) {

        // The "Add File" button was pressed
        if (v.getId() == R.id.addfilebtn) {
            TextDialogBox newFileWindow = new TextDialogBox();
            newFileWindow.setHint(getResources().getString(R.string.file_prompt));
            newFileWindow.setTitle(getResources().getString(R.string.add_file_title));
            newFileWindow.show(getFragmentManager(), "FileName");
        }

        // A delete button was pressed
        for (GroupFile f : ((MainActivity) getActivity()).getGroupFiles()) {
            if (f.getFileId() == v.getId()) {
                Button b = (Button) v;
                // Delete button was pressed
                if (b.getText().equals(getString(R.string.delfilebtn)))
                    //Delete the file
                    ;
            }
        }
    }

    private void createFile(String filename, int id) {
        // Get the layout for the list of files
        LinearLayout lFileList = (LinearLayout) root_view.findViewById(R.id.llFileList);

        // Create a new row
        LinearLayout lNewFile = new LinearLayout(root_view.getContext());
        lNewFile.setOrientation(LinearLayout.HORIZONTAL);

        // Create a new text file name
        TextView txtFileName = new TextView(root_view.getContext());
        txtFileName.setText(filename);
        lNewFile.addView(txtFileName);


        // Create a new Text File
        EditText addTextToFile = (EditText)new EditText(root_view.getContext());
        addTextToFile.setText("Enter Your Text here");
        lNewFile.addView(addTextToFile);

        // Create a new delete button
        Button bDelete = new Button(root_view.getContext());
        bDelete.setText(getString(R.string.delfilebtn));
        bDelete.setId(id);
        bDelete.setOnClickListener(this);
        bDeleteButtons.add(bDelete);
        lNewFile.addView(bDelete);

        // Add the new file to the list of files
        lFileList.addView(lNewFile);
    }
}