package dev.dsluo.majortostudyfragments;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class DetailActivity extends AppCompatActivity implements MajorListFragment.OnMajorSelectedListener {

    private static final String DETAILS = "dev.dsluo.majortostudyfragments.DETAILS";
    private static final String MAJOR_INDEX = "dev.dsluo.majortostudyfragments.MAJOR_INDEX";
    private static final String VERTICAL_BACK = "dev.dsluo.majortostudyfragments.VERTICAL_BACK";

    private String mode = MainActivity.DESCRIPTION;

    private int majorIndex = 0;
    private boolean details = false;

    /**
     * Handle onCreate.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            this.details = savedInstanceState.getBoolean(DETAILS);
            this.majorIndex = savedInstanceState.getInt(MAJOR_INDEX);
        }

        setContentView(R.layout.activity_detail);

        mode = getIntent().getStringExtra(MainActivity.INFO_TYPE);

        this.showMajorList();
        if (details)
            this.onMajorSelected(this.majorIndex);
    }

    /**
     * Make the back button go back.
     */
    @Override
    public boolean onSupportNavigateUp() {
        if (details) {
            getSupportFragmentManager()
                    .popBackStack(VERTICAL_BACK, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            details = false;
        } else
            finish();
        return true;
    }

    /**
     * Gets current major index.
     */
    @Override
    public int getMajorIndex() {
        return this.majorIndex;
    }

    /**
     * Shows the {@link MajorListFragment}.
     */
    private void showMajorList() {
        MajorListFragment majorListFragment = new MajorListFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, majorListFragment)
                .commit();
    }

    /**
     * Handles showing the job/description when an item in the {@link MajorListFragment} is selected.
     *
     * @param index The index of the major.
     */
    @Override
    public void onMajorSelected(int index) {
        this.majorIndex = index;

        Fragment fragment;
        if (mode.equals(MainActivity.DESCRIPTION))
            fragment = DescriptionFragment.newInstance(MajorListFragment.MAJORS[majorIndex]);
        else
            fragment = JobsFragment.newInstance(MajorListFragment.MAJORS[majorIndex]);

        // horizontal
        if (findViewById(R.id.info_frame) != null) {
            getSupportFragmentManager()
                    .popBackStack(VERTICAL_BACK, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.info_frame, fragment)
                    .commit();
        }
        // vertical
        else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(VERTICAL_BACK)
                    .commit();
        }
        details = true;
    }

    /**
     * Save the details and major index.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(DETAILS, details);
        outState.putInt(MAJOR_INDEX, majorIndex);
    }
}
