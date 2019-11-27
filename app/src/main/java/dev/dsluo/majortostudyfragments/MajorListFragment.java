package dev.dsluo.majortostudyfragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;


/**
 * Handles display of the Major List.
 * Activities that contain this fragment must implement the
 * {@link MajorListFragment.OnMajorSelectedListener} interface
 * to handle interaction events.
 */
public class MajorListFragment extends ListFragment {

    private OnMajorSelectedListener listener;

    public static final String[] MAJORS = new String[]{
            "Biology", "Chemistry", "Computer Science", "Economics", "Graphic Design"
    };

    public MajorListFragment() {
        // Required empty public constructor
    }

    /**
     * Handle list population and restoration of list selection.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, MAJORS));

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setSelection(listener.getMajorIndex());
    }

    /**
     * Inflate the fragment view from layout.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_major_list, container, false);
    }

    /**
     * Handle list selection.
     *
     * @param position Position of selected item in list.
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        listener.onMajorSelected(position);
    }


    /**
     * Ensure that containing activity implements {@link OnMajorSelectedListener}
     *
     * @param context The activity.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMajorSelectedListener)
            listener = (OnMajorSelectedListener) context;
        else
            throw new RuntimeException(context.toString()
                    + " must implement OnMajorSelectedListener");
    }

    /**
     * Detach listener on detach.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /**
     * Interface to allow communication between {@link DetailActivity} and this fragment.
     */
    public interface OnMajorSelectedListener {
        /**
         * Get currently selected major index.
         */
        int getMajorIndex();

        /**
         * Handle display of major details (description/job).
         *
         * @param index Index of the major in list.
         */
        void onMajorSelected(int index);
    }
}
