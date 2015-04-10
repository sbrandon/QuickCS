package ie.quickcs.activity;

import ie.quickcs.R;
import ie.quickcs.messenger.Messenger;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUPActivity extends Activity
{
	EditText editFirstName,editLastName,editTextUserName,editTextPassword,editTextConfirmPassword;
	Button btnCreateAccount;
	private String gcmRegId;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		editFirstName = (EditText)findViewById(R.id.editFirstName);
		editLastName = (EditText)findViewById(R.id.editLastName);
		editTextUserName=(EditText)findViewById(R.id.editTextUserName);
		editTextPassword=(EditText)findViewById(R.id.editTextPassword);
		editTextConfirmPassword=(EditText)findViewById(R.id.editTextConfirmPassword);
		
		Intent intent = getIntent();
        gcmRegId = intent.getStringExtra("API-KEY");
		
		btnCreateAccount=(Button)findViewById(R.id.buttonCreateAccount);
		btnCreateAccount.setOnClickListener(new View.OnClickListener() {

		public void onClick(View v) {

			String firstName = editFirstName.getText().toString();
			String lastName = editLastName.getText().toString();
			String userName=editTextUserName.getText().toString();
			String password=editTextPassword.getText().toString();
			String confirmPassword=editTextConfirmPassword.getText().toString();	
			if(userName.equals("")||password.equals("")||confirmPassword.equals(""))
			{
				Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
				return;
			}
			if(!password.equals(confirmPassword))
			{
				Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
				return;
			}
			else
			{
				String regResponse = Messenger.register(userName, password, firstName, lastName, gcmRegId);
				if(regResponse.trim().equals("SUCCESS")){
					Intent intent = new Intent(SignUPActivity.this, MainListActivity.class);
					intent.putExtra("USER-EMAIL", userName);
					SignUPActivity.this.startActivity(intent);
				}
				else{
					Toast.makeText(SignUPActivity.this, "Sorry, there was an error!", Toast.LENGTH_LONG).show();
				}
			}
		}
	});
}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
