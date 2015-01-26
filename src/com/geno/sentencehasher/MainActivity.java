package com.geno.sentencehasher;

import android.os.*;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.text.*;
import android.view.View.*;

public class MainActivity extends Activity
{
	public SharedPreferences sp;
	public SharedPreferences.Editor e;
	public TextView md5,sha1;
	public EditText md5in,sha1in,text;
	public TableLayout back;
	public Button calc;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		sp=getSharedPreferences("Default",MODE_PRIVATE);
		setContentView(R.layout.light);
		md5=(TextView)findViewById(R.id.md5);
		sha1=(TextView)findViewById(R.id.sha1);
		md5in=(EditText)findViewById(R.id.md5in);
		sha1in=(EditText)findViewById(R.id.sha1in);
		text=(EditText)findViewById(R.id.text);
		back=(TableLayout)findViewById(R.id.back);
		calc=(Button)findViewById(R.id.calc);
		if(sp.getBoolean("dark",false))dark();
		calc.setOnClickListener
		(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					md5in.setText(text.getText().toString().hashCode());
				}
			}
		);
	}
	
	void dark()
	{
		md5.setTextColor(0xFF808080);
		sha1.setTextColor(0xFF808080);
		back.setBackgroundColor(0xFF808080);
	}
}
