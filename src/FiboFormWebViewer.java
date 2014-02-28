package org.vkedco.mobicom.web_view_act_02;

import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

public class FiboFormWebViewer extends Activity {
	
	WebView mWebView = null;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);
		
		//Set some important WebView settings
		mWebView = (WebView)findViewById(R.id.web_view);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.addJavascriptInterface(this, "webViewInterface");
		
		mWebView.loadUrl("file:///android_asset/fibo_form.html");
	}
	
	@JavascriptInterface
	public String getFiboN(){
		//Returns the value passed from main activity
		return getIntent().getExtras().getString("FiboN");
	}
	
	@JavascriptInterface
	public void reportFiboN(String data){
		//Returns the value from WebView back to main activity
		Intent i = new Intent();
		int n;
		long fn;
		try {
			n = Integer.parseInt(data);
			fn = fiboN(n);
			Log.v("FIBO", ""+fn);
			i.putExtra("FiboN", Long.toString(fn));
			this.setResult(0, i);
			finish();
		}
		catch ( Exception ex ) {
			fn = -1;
			i.putExtra("FiboN", Long.toString(fn));
			this.setResult(0, i);
			Log.v("FIBO", ""+fn);
			finish();
		}
	}
	
	private long fiboN(int n) {
		if ( n == 0 ) return 0;
		if ( n == 1 ) return 1;
		long prev = 0;
		long curr = 1;
		long tmp = 0;
		while ( n > 0 ) {
			tmp = curr;
			curr += prev;
			prev = tmp;
			n--;
		}
		return prev;
		
	}

}
