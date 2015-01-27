package com.geno.sentencehasher;

import android.os.*;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.text.*;
import android.view.View.*;
import java.security.*;

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
		if (sp.getBoolean("dark", false))dark();
		calc.setOnClickListener
		(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					md5in.setText(hash(text.getText().toString(),"MD5").toUpperCase());
					sha1in.setText(hash(text.getText().toString(),"SHA1").toUpperCase());
					
				}
			}
		);
		md5in.setOnClickListener
		(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					clip(md5in);
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
	
	void clip(EditText t)
	{
		android.content.ClipboardManager cm = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
		cm.setText(t.getText().toString());
	}

	public static String hash(String str,String algorithm)  
	{  
		MessageDigest md5 = null;  
		try  
		{  
			md5 = MessageDigest.getInstance(algorithm); 
		}catch(Exception e)  
		{  
			e.printStackTrace();  
			return "";  
		}  

		char[] charArray = str.toCharArray();  
		byte[] byteArray = new byte[charArray.length];  

		for(int i = 0; i < charArray.length; i++)  
		{  
			byteArray[i] = (byte)charArray[i];  
		}  
		byte[] md5Bytes = md5.digest(byteArray);  

		StringBuffer hexValue = new StringBuffer();  
		for( int i = 0; i < md5Bytes.length; i++)  
		{  
			int val = ((int)md5Bytes[i])&0xff;  
			if(val < 16)  
			{  
				hexValue.append("0");  
			}  
			hexValue.append(Integer.toHexString(val));  
		}  
		return hexValue.toString();  
	} 
}
