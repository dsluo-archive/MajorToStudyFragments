package dev.dsluo.majortostudyfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String INFO_TYPE = "dev.dsluo.majortostudyfragments.INFO_TYPE";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String JOBS = "JOBS";

    /**
     * Set up main activity and set {@link android.view.View.OnClickListener}s for respective buttons.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button description = findViewById(R.id.description);
        Button jobs = findViewById(R.id.jobs);

        description.setOnClickListener(v -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(INFO_TYPE, DESCRIPTION);
            startActivity(intent);
        });

        jobs.setOnClickListener(v -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(INFO_TYPE, JOBS);
            startActivity(intent);
        });
    }
}
