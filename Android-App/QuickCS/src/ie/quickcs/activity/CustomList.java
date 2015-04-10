package ie.quickcs.activity;

import ie.quickcs.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@SuppressLint({ "InflateParams", "ViewHolder" })
public class CustomList extends ArrayAdapter<String> {

	private final Activity context;
	private final String[] itemname;
	
	public CustomList(Activity context, String[] itemname) {
		super(context, R.layout.list_single, itemname);
		
		this.context=context;
		this.itemname=itemname;
	}
	
	
	public View getView(int position,View view,ViewGroup parent) {
		LayoutInflater inflater=context.getLayoutInflater();
		View rowView=inflater.inflate(R.layout.list_single, null,true);
		
		TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
		TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
		
		txtTitle.setText(itemname[position]);
		extratxt.setText("Description "+itemname[position]);
		return rowView;
		
	};
}