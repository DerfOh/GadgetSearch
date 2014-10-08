//code designed by Fredrick Paulin
//credit for the barcode scanner goes to the ZXing team
//

package com.derfohappco.gadgetsearch;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//in this fragment the user can search for products across multiple retailers to do a price comparison
public class MainActivity extends Activity implements OnClickListener {
	
	
	Button searchGoogle;
	Button searchBestBuy;
	Button searchAmazon;
	Button searchNewegg;
	Button searchMicrocenter;
	Button searchWalmart;
	Button searchTarget;
	Button upcButton;
	
	EditText inputTerm;

	public MainActivity() {
		// Required empty public constructor
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		//buttons
		//search
		searchGoogle = (Button) findViewById(R.id.searchGoogleButton);
		searchBestBuy = (Button) findViewById(R.id.searchBestBuyButton);
		searchAmazon = (Button) findViewById(R.id.searchAmazonButton);
		searchNewegg = (Button) findViewById(R.id.searchNeweggButton);
		searchMicrocenter = (Button) findViewById(R.id.searchMicrocenterButton);
		searchWalmart = (Button) findViewById(R.id.searchWalmartButton);
		searchTarget = (Button) findViewById(R.id.searchTargetButton);
		
		upcButton = (Button) findViewById(R.id.buttonScanner);
		
		
		//input terms
		inputTerm = (EditText) findViewById(R.id.searchTerm);
		
		//button listeners
		searchAmazon.setOnClickListener(this);
		searchBestBuy.setOnClickListener(this);
		searchGoogle.setOnClickListener(this);
		searchNewegg.setOnClickListener(this);
		searchMicrocenter.setOnClickListener(this);
		searchWalmart.setOnClickListener(this);
		searchTarget.setOnClickListener(this);
		upcButton.setOnClickListener(this);
		

		
	}

	public void onClick(View v){
		
		if (v.getId() == R.id.searchAmazonButton){
			Uri uriUrl = Uri.parse("http://www.amazon.com/gp/aw/s/ref=is_box?k=" + inputTerm.getText());//"&emi=ATVPDKIKX0DER" limits the search to shipped and sold by amazon only
			Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
			startActivity(launchBrowser);
			
		}
		else if(v.getId() == R.id.searchNeweggButton){
			Uri uriUrl = Uri.parse("http://www.newegg.com/Product/ProductList.aspx?Submit=ENE&DEPA=0&Order=BESTMATCH&Description=" + inputTerm.getText() + "&N=-1&isNodeId=1");
			Intent lanchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
			startActivity(lanchBrowser);
		}
		else if(v.getId() == R.id.searchGoogleButton){
			Uri uriUrl = Uri.parse("https://www.google.com/search?q=" + inputTerm.getText());
			Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
			startActivity(launchBrowser);
		}
		else if(v.getId() == R.id.searchBestBuyButton){
			Uri uriUrl = Uri.parse("http://m.bestbuy.com/m/e/search/searchresults.jsp?&st=" + inputTerm.getText());
			Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
			startActivity(launchBrowser);
		}
		else if (v.getId() == R.id.searchMicrocenterButton){
			Uri uriUrl = Uri.parse("http://www.microcenter.com/search/search_results.aspx?Ntt=" + inputTerm.getText());
			Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
			startActivity(launchBrowser);
		}
		
		else if (v.getId() == R.id.searchWalmartButton){
			Uri uriUrl = Uri.parse("http://mobile.walmart.com/search/" + inputTerm.getText());
			Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
			startActivity(launchBrowser);
		}
		
		else if (v.getId() == R.id.searchTargetButton){
			Uri uriUrl = Uri.parse("http://m.target.com/s?category=0%7CAll%7Cmatchallpartial%7Call+categories&searchTerm=" + inputTerm.getText());
			Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
			startActivity(launchBrowser);
		}
		
		else if(v.getId() == R.id.buttonScanner){
			
			//start the intent for the zXing barcode scanner app -- implement a check to see if it's installed, if not redirect to play store page to download
			Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		       intent.putExtra("SCAN_MODE", "PRODUCT_MODE");//for Qr code, its "QR_CODE_MODE" instead of "PRODUCT_MODE"
		       intent.putExtra("SAVE_HISTORY", false);//this stops saving ur barcode in barcode scanner app's history
		       startActivityForResult(intent, 0);
		       
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) { //Set up to handle the scanner results.
	        super.onActivityResult(requestCode, resultCode, data);
	        
	        if (requestCode == 0) {
	            if (resultCode == -1) {
	                String contents = data.getStringExtra("SCAN_RESULT");
	                inputTerm.setText(contents, TextView.BufferType.EDITABLE);
	            } else{
	                // Handle cancel, leave blank to prevent quickTools from crashing
	            }
	        }
	        
	        
	    }
}