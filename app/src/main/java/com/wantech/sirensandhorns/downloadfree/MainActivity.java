package com.wantech.sirensandhorns.downloadfree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.wantech.sirensandhorns.downloadfree.R;

public class MainActivity extends Activity
	{

		@Override
		protected void onCreate(Bundle savedInstanceState)
			{
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_main);
				new Handler().postDelayed(new Runnable()
					{

						@Override
						public void run()
							{
								Intent intent = new Intent(MainActivity.this, SirensActivity.class);
								startActivity(intent);
								finish();
							}
					}, 2000);

			}
	}
