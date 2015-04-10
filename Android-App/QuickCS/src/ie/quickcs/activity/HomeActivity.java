package ie.quickcs.activity;

import com.google.android.gcm.GCMRegistrar;

import ie.quickcs.R;
import ie.quickcs.messenger.Messenger;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends Activity 
{
	private static final String PROJECT_ID = "518668514236";
	private String gcmRegId = "";
	IntentFilter gcmFilter;
	private String registrationStatus = "Not yet registered";
	private String broadcastMessage = "No broadcast message";
	Button btnSignIn,btnSignUp;
	ImageView image;
	
	private BroadcastReceiver gcmReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

			broadcastMessage = intent.getExtras().getString("gcm");

			if (broadcastMessage != null) {
				//TEMPORARY DISPLAY RECIVED MESSAGE
				Toast.makeText(HomeActivity.this, registrationStatus, Toast.LENGTH_LONG).show();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    setContentView(R.layout.activity_main);
	    btnSignIn=(Button)findViewById(R.id.buttonSignIN);
	    btnSignUp=(Button)findViewById(R.id.buttonSignUP);
	    btnSignUp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intentSignUP = new Intent(getApplicationContext(),SignUPActivity.class);
				intentSignUP.putExtra("API-KEY", gcmRegId);
				startActivity(intentSignUP);
			}
		});
	    gcmFilter = new IntentFilter();
	    gcmFilter.addAction("GCM_RECEIVED_ACTION");
	    registerClient();
	}
	
	public void signIn(View V){
		final Dialog dialog = new Dialog(HomeActivity.this);
		dialog.setContentView(R.layout.login);
	    dialog.setTitle("Login");

	    final  EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
	    final  EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);

		Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);
		btnSignIn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				String userName=editTextUserName.getText().toString();
				String password=editTextPassword.getText().toString();
				String loginResponse = Messenger.login(userName, password, gcmRegId);
				if(loginResponse.trim().equals("SUCCESS")){
					Intent intent = new Intent(HomeActivity.this, MainListActivity.class);
					intent.putExtra("USER-EMAIL", userName);
					HomeActivity.this.startActivity(intent);
				}
				else{
					Log.e("GCMID", gcmRegId);
					Toast.makeText(HomeActivity.this, "Incorrect user name or password.", Toast.LENGTH_LONG).show();
				}
			}
		});

		dialog.show();
	}
	
	public void registerClient() {
		try {
			GCMRegistrar.checkDevice(this);
			GCMRegistrar.checkManifest(this);
			gcmRegId = GCMRegistrar.getRegistrationId(this);
			if (gcmRegId.equals("")) {
				GCMRegistrar.register(this, PROJECT_ID);
				gcmRegId = GCMRegistrar.getRegistrationId(this);
				registrationStatus = "Registration Acquired";
			} else {
				registrationStatus = "Already registered";
			}
		} catch (Exception e) {
			e.printStackTrace();
			registrationStatus = e.getMessage();
		}
		//TEMPORARY SHOW STATUS
		Toast.makeText(HomeActivity.this, registrationStatus, Toast.LENGTH_LONG).show();
		
	}
}