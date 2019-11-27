package dev.dsluo.majortostudyfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.InputStream;
import java.util.Scanner;


/**
 * Handles display of major jobs.
 * <p>
 * Use the {@link JobsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JobsFragment extends Fragment {
    private static final String SELECTED_MAJOR = "SELECTED_MAJOR";

    private String selectedMajor;

    public JobsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param selectedMajor The selected major.
     * @return A new instance of fragment JobsFragment.
     */
    public static JobsFragment newInstance(String selectedMajor) {
        JobsFragment fragment = new JobsFragment();
        Bundle args = new Bundle();
        args.putString(SELECTED_MAJOR, selectedMajor);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Set arguments if available.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedMajor = getArguments().getString(SELECTED_MAJOR);
        }
    }

    /**
     * Inflate jobs fragment and fill out appropriate TextViews and ImageViews from info in respective files.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jobs, container, false);

        ImageView preview = view.findViewById(R.id.preview);
        TextView title = view.findViewById(R.id.title);
        TextView jobs = view.findViewById(R.id.jobs);

        title.setText(selectedMajor);

        int resource;

        switch (selectedMajor) {
            case "Biology":
                preview.setImageResource(R.drawable.biology);
                resource = R.raw.graphic_design_jobs;
                break;
            case "Chemistry":
                preview.setImageResource(R.drawable.chemistry);
                resource = R.raw.graphic_design_jobs;
                break;
            case "Computer Science":
                preview.setImageResource(R.drawable.computer_science);
                resource = R.raw.graphic_design_jobs;
                break;
            case "Economics":
                preview.setImageResource(R.drawable.economics);
                resource = R.raw.graphic_design_jobs;
                break;
            case "Graphic Design":
                preview.setImageResource(R.drawable.graphic_design);
                resource = R.raw.graphic_design_jobs;
                break;
            default:
                return view;
        }

        InputStream stream = getResources().openRawResource(resource);

        Scanner s = new Scanner(stream);
        StringBuilder jobsBuilder = new StringBuilder();
        while (s.hasNextLine())
            jobsBuilder.append(s.nextLine()).append("\n");
        String jobsText = jobsBuilder.toString();

        jobs.setText(jobsText);

        return view;
    }
}
