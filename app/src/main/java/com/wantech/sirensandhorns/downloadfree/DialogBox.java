package com.wantech.sirensandhorns.downloadfree;

import android.app.FragmentManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.wantech.sirensandhorns.downloadfree.R;

public class DialogBox extends DialogFragment
	{
		Button playAudio;
		Button stopAudio;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
			{
				View view = inflater.inflate(R.layout.activity_dialog_box, null);
				final MediaPlayer cheer = MediaPlayer.create(getActivity(), R.raw.sound1);
				playAudio = (Button) view.findViewById(R.id.buttonPlayAudio);
				stopAudio = (Button) view.findViewById(R.id.buttonStopAudio);
				setCancelable(false);
				playAudio.setOnClickListener(new View.OnClickListener()
					{
						Intent intent = null, chooser = null;
						
						
						@Override
						public void onClick(View arg0)
							{
								Toast.makeText(getActivity(), "Play Button was clicked", Toast.LENGTH_SHORT).show();
								
								cheer.start();
							}
					});
				stopAudio.setOnClickListener(new View.OnClickListener()
					{

						@Override
						public void onClick(View v)
							{
								Toast.makeText(getActivity(), "Stop Button was clicked", Toast.LENGTH_SHORT).show();
								cheer.stop();
								dismiss();
								
							}
					});
				return view;

			}

		public void show(FragmentManager manag, String string)
			{
				// TODO Auto-generated method stub
				
			}

	}