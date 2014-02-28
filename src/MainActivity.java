package org.vkedco.mobicom.web_view_act_02;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	EditText mEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_layout);
		
		//Get important components
		mEditText = (EditText)findViewById(R.id.main_activity_edit_text);
		((Button)findViewById(R.id.main_activity_button)).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
			case R.id.main_activity_button:
				//Create Intent with value from text field
				Intent i = new Intent(this, FiboFormWebViewer.class);
				i.putExtra("FiboN", mEditText.getText().toString());		
				//Start activity and watch for result
				startActivityForResult(i, 0);
				break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//Set text field with result
		if ( data != null ) {
			String value = data.getExtras().getString("FiboN");
			if ( value != null )
				mEditText.setText(value);
			else
				mEditText.setText("no data");
		}
		else {
			mEditText.setText("no result");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
