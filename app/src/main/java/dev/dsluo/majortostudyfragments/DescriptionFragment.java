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
 * Handles display of major descriptions.
 * <p>
 * Use the {@link DescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescriptionFragment extends Fragment {
    private static final String SELECTED_MAJOR = "SELECTED_MAJOR";

    private String selectedMajor;

    public DescriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param selectedMajor The selected major.
     * @return A new instance of fragment DescriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DescriptionFragment newInstance(String selectedMajor) {
        DescriptionFragment fragment = new DescriptionFragment();
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
     * Inflate description fragment and fill out appropriate TextViews and ImageViews from info in respective files.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description, container, false);

        ImageView preview = view.findViewById(R.id.preview);
        TextView title = view.findViewById(R.id.title);
        TextView description = view.findViewById(R.id.description);

        title.setText(selectedMajor);

        int resource;

        switch (selectedMajor) {
            case "Biology":
                preview.setImageResource(R.drawable.biology);
                resource = R.raw.biology_description;
                break;
            case "Chemistry":
                preview.setImageResource(R.drawable.chemistry);
                resource = R.raw.chemistry_description;
                break;
            case "Computer Science":
                preview.setImageResource(R.drawable.computer_science);
                resource = R.raw.computer_science_description;
                break;
            case "Economics":
                preview.setImageResource(R.drawable.economics);
                resource = R.raw.economics_description;
                break;
            case "Graphic Design":
                preview.setImageResource(R.drawable.graphic_design);
                resource = R.raw.graphic_design_description;
                break;
            default:
                return view;
        }

        InputStream stream = getResources().openRawResource(resource);

        Scanner s = new Scanner(stream);
        StringBuilder descriptionBuilder = new StringBuilder();
        while (s.hasNextLine())
            descriptionBuilder.append(s.nextLine()).append("\n");
        String descriptionText = descriptionBuilder.toString();

        description.setText(descriptionText);

        return view;
    }
}
