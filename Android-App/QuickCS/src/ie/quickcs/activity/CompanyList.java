package ie.quickcs.activity;

import org.json.JSONArray;
import org.json.JSONObject;

import ie.quickcs.R;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;

public class CompanyList extends Activity {
  ListView list;
  String[] companyNames;
  String[] addresses;
  private String userName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getCompanies();
		setContentView(R.layout.activity_company_list);
		CustomList adapter = new CustomList(this, companyNames);
		ListView lv = (ListView) findViewById(R.id.listView);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new ListClickHandler()); 
	}
	
	public void getCompanies(){
		Intent intent = getIntent();
		String json = intent.getStringExtra("COMPANIES");
		userName = intent.getStringExtra("USER-EMAIL");
		try {
		    JSONArray array = new JSONArray(json);
		    companyNames = new String[array.length()];
		    addresses = new String[array.length()];
		    for(int i = 0; i < array.length(); i++){
		    	JSONObject companyJSON = (JSONObject) array.get(i);
		    	String companyName = (String) companyJSON.get("name");
		    	String ipAddress = (String) companyJSON.get("ipAddress");
		    	companyNames[i] = companyName;
		    	addresses[i] = ipAddress;
		    }
		} catch (Exception e) {
		    //Do Nothing
		}
	}
	
	public class ListClickHandler implements OnItemClickListener{
		@Override    
		public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {   
			Intent intent = new Intent(CompanyList.this, ChatBubbleActivity.class);
			intent.putExtra("COMPANY-IP", addresses[position]);
			intent.putExtra("COMPANY-NAME", companyNames[position]);
			intent.putExtra("USER-EMAIL", userName);
			startActivity(intent);   
			}       
		} 
		
	}
	
