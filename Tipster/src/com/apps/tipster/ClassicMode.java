package com.apps.tipster;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RatingBar;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class ClassicMode extends TipsterActivity {
	
	public static final double VALUE = 42.85714285714286;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	    ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    actionBar.setTitle(R.string.classicMode);
        Tipster globalApp = ((Tipster)getApplicationContext());
    }
    
    public void onClick(View view){
    	Intent displayTotal = new Intent(this, DisplayTotals.class);
    	RatingBar mealSpeed = (RatingBar)findViewById(R.id.rtngMealSpeed);
    	RatingBar mealTaste = (RatingBar)findViewById(R.id.ratingBar1);
    	RatingBar serverCourtesy = (RatingBar)findViewById(R.id.ratingBar2);
    	RatingBar serverRating = (RatingBar)findViewById(R.id.ratingBar3);
    	RatingBar serverRush = (RatingBar)findViewById(R.id.ratingBar4);
    	RatingBar estabClean = (RatingBar)findViewById(R.id.ratingBar5);
    	RatingBar estabInviting = (RatingBar)findViewById(R.id.ratingBar6);
    	RadioButton yes = (RadioButton)findViewById(R.id.radio0);
    	RadioButton no = (RadioButton)findViewById(R.id.radio1);
    	
    	double mealSpeedValue = mealSpeed.getRating()*VALUE, mealTasteValue = mealTaste.getRating()*VALUE, serverCourtesyValue = serverCourtesy.getRating()*VALUE,
    			serverRatingValue = serverRating.getRating()*VALUE, serverRushValue = serverRush.getRating()*VALUE, estabCleanValue = estabClean.getRating()*VALUE,
    			estabInvitingValue = estabInviting.getRating()*VALUE, finalReturnValue;
    	
    	double total = mealSpeedValue + mealTasteValue + serverCourtesyValue + serverRatingValue + serverRushValue + estabCleanValue + estabInvitingValue;;
    	if(yes.isChecked()){
    		finalReturnValue = 5;
    	} else{
    		finalReturnValue = 2;
    	}

    	total = Math.round(total+finalReturnValue*100.0)/100.0;
    	
    	displayTotal.putExtra("percent", total);
    	startActivity(displayTotal);
    	
    }
}
