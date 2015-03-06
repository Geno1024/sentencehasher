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
	public TextView md5,sha1,sha256,sha384,sha512,avail;
	public TextView md5in,sha1in,sha256in,sha384in,sha512in,text;
	public TextWatcher intext;
	public long len;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		avail=(TextView)findViewById(R.id.avail);
		avail.setText(getString(R.string.intro)+"\n"+getString(R.string.avail)+Security.getAlgorithms("MessageDigest"));

		md5=(TextView)findViewById(R.id.md5);
		md5in=(TextView)findViewById(R.id.md5in);

		sha1=(TextView)findViewById(R.id.sha1);
		sha1in=(TextView)findViewById(R.id.sha1in);

		sha256=(TextView)findViewById(R.id.sha256);
		sha256in=(TextView)findViewById(R.id.sha256in);

		sha384=(TextView)findViewById(R.id.sha384);
		sha384in=(TextView)findViewById(R.id.sha384in);

		sha512=(TextView)findViewById(R.id.sha512);
		sha512in=(TextView)findViewById(R.id.sha512in);

		text=(EditText)findViewById(R.id.text);

		intext=new TextWatcher() 
		{
			@Override public void onTextChanged(CharSequence s,int start,int before,int count)
			{
			}

			@Override public void beforeTextChanged(CharSequence s,int start,int count,int after)
			{
			}

			@Override
			public void afterTextChanged(Editable s)
			{
				md5in.setText(hash(text.getText().toString(),"MD5").toUpperCase());
				sha1in.setText(hash(text.getText().toString(),"SHA1").toUpperCase());
				sha256in.setText(hash(text.getText().toString(),"SHA256").toUpperCase());
				sha384in.setText(hash(text.getText().toString(),"SHA384").toUpperCase());
				sha512in.setText(hash(text.getText().toString(),"SHA512").toUpperCase());
				avail.setText(getString(R.string.intro)+"\n"+getString(R.string.avail)+Security.getAlgorithms("MessageDigest")+"\nMessage length: "+len);
			}
		};

		text.addTextChangedListener(intext);

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
		sha1in.setOnClickListener
		(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					clip(sha1in);
				}
			}
		);
		sha256in.setOnClickListener
		(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					clip(sha256in);
				}
			}
		);
		sha384in.setOnClickListener
		(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					clip(sha384in);
				}
			}
		);
		sha512in.setOnClickListener
		(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					clip(sha512in);
				}
			}
		);
	}

	void clip(TextView t)
	{
		android.content.ClipboardManager cm=(android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
		cm.setText(t.getText().toString());
		Toast.makeText(this,getString(R.string.copied),Toast.LENGTH_SHORT).show();
	}

	String hash(String str,String algorithm)
	{
		MessageDigest hashcode = null;
		try
		{
			hashcode=MessageDigest.getInstance(algorithm);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		char[] charArray=str.toCharArray();
		byte[] byteArray=new byte[charArray.length];

		for(int i=0;i<charArray.length;i++)
		{
			byteArray[i]=(byte)charArray[i];
		}
		len = byteArray.length;
		byte[] hashBytes=hashcode.digest(byteArray);
		StringBuffer hexValue=new StringBuffer();
		for( int i=0;i<hashBytes.length;i++)
		{
			int val=((int)hashBytes[i])&0xff;
			if(val<16)
			{
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
}

