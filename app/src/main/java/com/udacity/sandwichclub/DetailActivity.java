package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import com.udacity.sandwichclub.utils.StringUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView mAlsoKnownTextView;
    private TextView mIngredientsTextView;
    private TextView mOriginTextView;
    private TextView mDescriptionTextView;

    private LinearLayout mAlsoKnownAsLayout;
    private LinearLayout mIngredientsLayout;
    private LinearLayout mOriginLayout;
    private LinearLayout mDescriptionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView ingredientsIv = findViewById(R.id.image_iv);
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        initializeUi();

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        populateUI(sandwich);
        Picasso.with(this).load(sandwich.getImage()).into(ingredientsIv);
        setTitle(sandwich.getMainName());
    }

    // Assigns XML elements to the elements in this class.
    private void initializeUi() {
        // Grab all TextView elements first.
        mAlsoKnownTextView = findViewById(R.id.also_known_tv);
        mIngredientsTextView = findViewById(R.id.ingredients_tv);
        mOriginTextView = findViewById(R.id.origin_tv);
        mDescriptionTextView = findViewById(R.id.description_tv);

        // Grab all layouts.
        mAlsoKnownAsLayout = findViewById(R.id.also_known_as_container);
        mIngredientsLayout = findViewById(R.id.ingredients_container);
        mOriginLayout = findViewById(R.id.origin_container);
        mDescriptionLayout = findViewById(R.id.description_container);

        // Hide all the inputs initially and show only when the content is available.
        // Use GONE in order to remove the element due to styling.
        mAlsoKnownAsLayout.setVisibility(View.GONE);
        mIngredientsLayout.setVisibility(View.GONE);
        mOriginLayout.setVisibility(View.GONE);
        mDescriptionLayout.setVisibility(View.GONE);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    // Fills the view and hides empty fields.
    private void populateUI(Sandwich sandwich) {
        if (!sandwich.getAlsoKnownAs().isEmpty()) {
            mAlsoKnownAsLayout.setVisibility(View.VISIBLE);
            mAlsoKnownTextView.setText(StringUtils.join(", ", sandwich.getAlsoKnownAs()));
        }

        if (!sandwich.getIngredients().isEmpty()) {
            mIngredientsLayout.setVisibility(View.VISIBLE);
            mIngredientsTextView.setText(StringUtils.join(", ", sandwich.getIngredients()));
        }

        if (!sandwich.getPlaceOfOrigin().isEmpty()) {
            mOriginLayout.setVisibility(View.VISIBLE);
            mOriginTextView.setText(sandwich.getPlaceOfOrigin());
        }

        if (!sandwich.getDescription().isEmpty()) {
            mOriginLayout.setVisibility(View.VISIBLE);
            mDescriptionTextView.setText(sandwich.getDescription());
        }
    }
}
