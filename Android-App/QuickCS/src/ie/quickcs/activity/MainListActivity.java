package ie.quickcs.activity;

import ie.quickcs.R;
import ie.quickcs.messenger.Messenger;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainListActivity extends Activity {

	Button btnCompany;
	String userName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_list);
		
		Intent intent = getIntent();
		userName = intent.getStringExtra("USER-EMAIL");
		
		btnCompany=(Button) findViewById(R.id.button_company);
		
		btnCompany.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), CompanyList.class);
				String companies = Messenger.getCompanies();
				intent.putExtra("USER-EMAIL", userName);
				intent.putExtra("COMPANIES", companies);
				startActivity(intent);
			}
		});
	}
}
