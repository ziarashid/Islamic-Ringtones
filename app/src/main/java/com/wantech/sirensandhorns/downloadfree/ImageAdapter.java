package com.wantech.sirensandhorns.downloadfree;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wantech.sirensandhorns.downloadfree.R;

public class ImageAdapter extends BaseAdapter
	{
		Context context;

		ArrayList<String> titles;
		//ArrayList<Integer> images;
          ImageView images;
	     static int pos=-1;
		public ImageAdapter( Context context, ArrayList<String> dataNameList, ImageView dataImageList)
			{
				this.context = context;
				titles = dataNameList;
				images = dataImageList;
			}

		@Override
		public int getCount()
			{
				// TODO Auto-generated method stub
				return titles.size();
			}

		@Override
		public Object getItem(int arg0)
			{
				// TODO Auto-generated method stub
				return null;
			}

		@Override
		public long getItemId(int arg0)
			{
				// TODO Auto-generated method stub
				return 0;
			}

		@Override
		public View getView(final int i, View convertView, final ViewGroup viewGroup)
			{

				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.activity_image_adapter, null);
				TextView exerciseName = (TextView) convertView.findViewById(R.id.textViewButtonName);
				// TextView totalNaat = (TextView) convertView.findViewById(R.id.totalNaats);
				// totalNaat.setText(naatSize.get(i));
				if(pos==i && SirensActivity.mediaPlayer.isPlaying())
				{
					convertView.findViewById(R.id.dotsimage2).setBackgroundResource(R.drawable.stop_playing_icon);
				}
				else  {
					convertView.findViewById(R.id.dotsimage2).setBackgroundResource(R.drawable.play_icon);

				}

				ImageView exerciseImg = (ImageView) convertView.findViewById(R.id.dotsimage);
				exerciseName.setText(titles.get(i));
            convertView.findViewById(R.id.dotsimage).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					((ListView) viewGroup).performItemClick(v, i, 0);
				}
			});
				convertView.findViewById(R.id.dotsimage2).setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						pos=i;
						//notifyDataSetChanged();
						((ListView) viewGroup).performItemClick(v, i, 0);


			}
	});
				convertView.findViewById(R.id.dotsimage1).setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						((ListView) viewGroup).performItemClick(v, i, 0);

					}
				});


				return convertView;

			}



	}