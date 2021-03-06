package com.apps.tipster;

import java.text.NumberFormat;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;


public class DetailsFragment extends Dialog {
	
	Tipster global;

	public DetailsFragment(Context context) {
		super(context);
		
		global = ((Tipster)context.getApplicationContext());
		
		this.setContentView(R.layout.details_view);
		this.setTitle("Details");
		
		TextView bill = (TextView)findViewById(R.id.bill);
		TextView tip = (TextView)findViewById(R.id.tip);
		TextView people = (TextView)findViewById(R.id.people);
		TextView split = (TextView)findViewById(R.id.split);
		TextView billPerPerson = (TextView)findViewById(R.id.billPerPerson);
		Spinner tipSpin = (Spinner)findViewById(R.id.tipSpin);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), 
	    		R.array.splitArray, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(R.drawable.spinner_layout);
	    MyOnItemSelectedListener spinListener = new MyOnItemSelectedListener();	    
	   
	    tipSpin.setAdapter(adapter);
	    tipSpin.setOnItemSelectedListener(spinListener);
	    
	    bill.setText("Your bill was : $" + Double.toString(global.getBillAmount()));
	    tip.setText("Your tip is : " + Double.toString(global.getTipAmount()) + "% or $" + Double.toString(Math.round(global.getTipAmount()/100*global.getBillAmount())));
	    people.setText("person");
	    split.setText("Split the bill by");
	    billPerPerson.setText("You pay : $" + Double.toString(global.getBillAmount()) + Double.toString(Math.round(global.getTipAmount()/100*global.getBillAmount())));	
	}
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener{

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			//Recalculate final			
			paymentTotal();
		}

		public void onNothingSelected(AdapterView parent) {
			// Do Nothing			
		}		
	}
	
	public void paymentTotal(){

		NumberFormat format = NumberFormat.getCurrencyInstance();				

		TextView people = (TextView)findViewById(R.id.people);
		TextView split = (TextView)findViewById(R.id.split);
		TextView billPerPerson = (TextView)findViewById(R.id.billPerPerson);
		Spinner tipSpin = (Spinner)findViewById(R.id.tipSpin);
		
		int peopleNo = tipSpin.getSelectedItemPosition() + 1;
		
		String tempAmount = (Double.toString(Math.round((global.getTipAmount()/100 + 1) * global.getBillAmount() *100.0)/100.0/peopleNo));
		
		if(peopleNo == 1){
			people.setText("person");
			billPerPerson.setText("You pay : $" + tempAmount);
		} else {
			people.setText("people");
			billPerPerson.setText("Each person pays : $" + tempAmount);
		}
			
	}
}
