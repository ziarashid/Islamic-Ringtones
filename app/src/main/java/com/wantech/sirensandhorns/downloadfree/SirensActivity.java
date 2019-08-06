package com.wantech.sirensandhorns.downloadfree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.wantech.sirensandhorns.downloadfree.R;

public class SirensActivity extends Activity implements OnClickListener
	{

		public static MediaPlayer mediaPlayer;
		int[] file = {R.raw.sound1, R.raw.sound2, R.raw.sound3, R.raw.sound4, R.raw.sound5, R.raw.sound6, R.raw.sound7, R.raw.sound8, R.raw.sound9, R.raw.sound10, R.raw.sound11, R.raw.sound12, R.raw.sound13, R.raw.sound14, R.raw.sound15, R.raw.sound16,
				R.raw.sound17, R.raw.sound18, R.raw.sound19, R.raw.sound20, R.raw.sound21, R.raw.sound22, R.raw.sound23, R.raw.sound24, R.raw.sound25, R.raw.sound26, R.raw.sound27, R.raw.sound28, R.raw.sound29, R.raw.sound30, R.raw.sound31,
				R.raw.sound32, R.raw.sound33, R.raw.sound34, R.raw.sound35, R.raw.sound36, R.raw.sound37, R.raw.sound38, R.raw.sound39, R.raw.sound40, R.raw.sound41, R.raw.sound42, R.raw.sound43, R.raw.sound44, R.raw.sound45, R.raw.sound46,
				R.raw.sound47, R.raw.sound48, R.raw.sound49, R.raw.sound50, R.raw.sound51, R.raw.sound52, R.raw.sound53, R.raw.sound54, R.raw.sound55, R.raw.sound56, R.raw.sound57, R.raw.sound58, R.raw.sound59, R.raw.sound60, R.raw.sound61, R.raw.sound62, R.raw.sound63
                , R.raw.sound64, R.raw.sound65, R.raw.sound66, R.raw.sound67, R.raw.sound68, R.raw.sound69, R.raw.sound70, R.raw.sound71, R.raw.sound72, R.raw.sound73, R.raw.sound74, R.raw.sound75, R.raw.sound76};
		int index;
		ListView grid;
		List<String> id1 = new ArrayList<String>();
		ArrayList<String> dataNameList = new ArrayList<String>();
		/*ArrayList<Integer> dataAudioList = new ArrayList<Integer>();*/
		ImageView dataAudioList;
		ImageAdapter adapter;
		int position;
		ImageView imageViewShareApp, imageViewRateApp;
		AdView adView;
		View v;
		long id;

		@Override
		protected void onCreate(Bundle savedInstanceState)
			{
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_sirens);

                PublisherInterstitialAd mPublisherInterstitialAd = new PublisherInterstitialAd(this);
                mPublisherInterstitialAd.setAdUnitId("ca-app-pub-4520398662557393/7845306671");
                mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());

				MobileAds.initialize(this,"ca-app-pub-4520398662557393~1569449036");
                adView=findViewById(R.id.adView);
                AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);
				fillData();
				imageViewRateApp = (ImageView) findViewById(R.id.imageViewRating);
                if(hassettingPermissions()==false && hasReadPermissions()==false && hasWritePermissions()==false) {
                    AlertDialog.Builder builderSingle1 = new AlertDialog.Builder(SirensActivity.this);
                    builderSingle1.setIcon(R.drawable.ic_launcher);
                    builderSingle1.setTitle("Permission");
                    builderSingle1.setMessage("Allow app to set ringtone");
                    builderSingle1.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            requestAppPermissions();
                            requestsettingpermission();
                        }
                    });
                    builderSingle1.setNegativeButton("cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builderSingle1.show();
                }
				grid =  findViewById(R.id.gridView1);
				adapter = new ImageAdapter(this, dataNameList, dataAudioList);
				grid.setAdapter(adapter);




				grid.setOnItemClickListener(new OnItemClickListener()
					{

						@Override
						public void onItemClick(AdapterView<?> arg0,final View arg1, int arg2, long arg3) {
							// TODO Auto-generated method stub
							long viewId = arg1.getId();

							if (viewId == R.id.dotsimage) {
						/*	if (mediaPlayer != null) {
									mediaPlayer.release();
									mediaPlayer = null;
									}*/
                                if (hassettingPermissions() == false && hasReadPermissions() == false && hasWritePermissions() == false) {
                                    AlertDialog.Builder builderSingle1 = new AlertDialog.Builder(SirensActivity.this);
                                    builderSingle1.setIcon(R.drawable.ic_launcher);
                                    builderSingle1.setTitle("Permission");
                                    builderSingle1.setMessage("Allow app to set ringtone");
                                    builderSingle1.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            requestAppPermissions();
                                            requestsettingpermission();
                                        }
                                    });
                                    builderSingle1.setNegativeButton("cancel", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            return;
                                        }
                                    });
                                    builderSingle1.show();
                                }
                               else {
                                    position = arg2;

                                    AlertDialog.Builder builderSingle = new AlertDialog.Builder(SirensActivity.this);
                                    builderSingle.setIcon(R.drawable.ic_launcher);
                                    builderSingle.setTitle("Set as...");

                                    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SirensActivity.this, android.R.layout.select_dialog_item);
                                    arrayAdapter.add("Ringtone");
                                    arrayAdapter.add("Notification");
                                    arrayAdapter.add("Alarm");
                                    //arrayAdapter.add("Contact");

                                    builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });

                                    builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            String strName = arrayAdapter.getItem(which);
                                            // AlertDialog.Builder builderInner = new AlertDialog.Builder(SirensActivity.this);
                                            if (strName == "Ringtone") {
                                                saveas(RingtoneManager.TYPE_RINGTONE, position);

                                                Toast.makeText(SirensActivity.this, strName + " Successfully Adjusted", Toast.LENGTH_LONG).show();
                                            } else if (strName == "Notification") {
                                                saveas(RingtoneManager.TYPE_NOTIFICATION, position);
                                                Toast.makeText(SirensActivity.this, strName + " Successfully Adjusted", Toast.LENGTH_LONG).show();
                                            } else if (strName == "Alarm") {
                                                saveas(RingtoneManager.TYPE_ALARM, position);
                                                Toast.makeText(SirensActivity.this, strName + " Successfully Adjusted", Toast.LENGTH_LONG).show();

                                            }

                                        }
                                    });
                                    builderSingle.show();
                                }
							} else if (viewId == R.id.dotsimage2) {
                                if(arg1!=v && v!=null && arg1!=null && mediaPlayer.isPlaying())
								{

									v.findViewById(R.id.dotsimage2).setBackgroundResource(R.drawable.play_icon);
									mediaPlayer.pause();
								}

								v=arg1;

									if(mediaPlayer != null && mediaPlayer.isPlaying())
									{

										mediaPlayer.pause();
										arg1.findViewById(R.id.dotsimage2).setBackgroundResource(R.drawable.play_icon);
									/*	pos=-1;
										adapter.notifyDataSetChanged();*/
										tos1();

									}
									else {

										playName(file[arg2]);
								/*		pos=arg2;
										adapter.notifyDataSetChanged();*/
										arg1.findViewById(R.id.dotsimage2).setBackgroundResource(R.drawable.stop_playing_icon);
										tos();
									}

								mediaPlayer.setOnCompletionListener(new OnCompletionListener()
								{

									@Override
									public void onCompletion(MediaPlayer arg0)
									{
										//mediaPlayer.release();
										//mediaPlayer = null;
										//dataAudioList.add(i, R.drawable.album_icon);
										adapter.pos=-1;
										adapter.notifyDataSetChanged();
										//arg1.findViewById(R.id.dotsimage2).setBackgroundResource(R.drawable.play_icon);


									}
								});



							}
								else if (viewId == R.id.dotsimage1) {


								String sharingText = "Hi I would like to share a new ring tone app with you: Islamic Ringtone App:\n";
									Intent share = new Intent(android.content.Intent.ACTION_SEND);
									share.setType("text/plain");
									share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
									share.putExtra(Intent.EXTRA_TEXT, sharingText + "https://play.google.com/store/apps/details?id=com.wantech_solutions.islamicringtones");
									startActivity(Intent.createChooser(share, "Share link!"));
								}
                 id=viewId;
							}
					});
		/*		grid.setOnItemLongClickListener(new OnItemLongClickListener()

					{

						@Override
						public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
							{

								if (mediaPlayer != null)
									{
										mediaPlayer.release();
										mediaPlayer = null;
									}
								position = arg2;

								AlertDialog.Builder builderSingle = new AlertDialog.Builder(SirensActivity.this);
								builderSingle.setIcon(R.drawable.ic_launcher);
								builderSingle.setTitle("Set as...");

								final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SirensActivity.this, android.R.layout.select_dialog_item);
								arrayAdapter.add("Ringtone");
								arrayAdapter.add("Notification");
								arrayAdapter.add("Alarm");
								//arrayAdapter.add("Contact");

								builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener()
									{

										@Override
										public void onClick(DialogInterface dialog, int which)
											{
												dialog.dismiss();
											}
									});

								builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener()
									{

										@Override
										public void onClick(DialogInterface dialog, int which)
											{
												String strName = arrayAdapter.getItem(which);
												// AlertDialog.Builder builderInner = new AlertDialog.Builder(SirensActivity.this);
												if (strName == "Ringtone")
													{
														saveas(RingtoneManager.TYPE_RINGTONE, position);

														Toast.makeText(SirensActivity.this, strName + " Successfully Adjusted", Toast.LENGTH_LONG).show();
													}
												else if (strName == "Notification")
													{
														saveas(RingtoneManager.TYPE_NOTIFICATION, position);
														Toast.makeText(SirensActivity.this, strName + " Successfully Adjusted", Toast.LENGTH_LONG).show();
													}
												else if (strName == "Alarm")
													{
														saveas(RingtoneManager.TYPE_ALARM, position);
														Toast.makeText(SirensActivity.this, strName + " Successfully Adjusted", Toast.LENGTH_LONG).show();

													}
											*//*	else if (strName == "Contact")
													{

														saveas(RingtoneManager.TYPE_ALL, position);
														Intent intent = new Intent(Intent.ACTION_PICK);
														intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
														startActivityForResult(intent, 1);




														  final Uri lookupUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, "012-345-6789");
														  // The columns used for `Contacts.getLookupUri`
														  final String[] projection = new String[] {
														  ContactsContract.Contacts._ID, ContactsContract.Contacts.LOOKUP_KEY
														  };
														  // Build your Cursor
														  final Cursor data = getContentResolver().query(lookupUri, projection, null, null, null);
														  data.moveToFirst();

														         try {
														  // Get the contact lookup Uri
														  final long contactId = data.getLong(0);
														  final String lookupKey = data.getString(1);
														  final Uri contactUri = ContactsContract.Contacts.getLookupUri(contactId, lookupKey);
														  if (contactUri == null) {
														  // Invalid arguments
														  return;
														  }
														  // Get the path of ringtone you'd like to use
														  final String storage = Environment.getExternalStorageDirectory().getPath();
														  final File file = new File(storage + "/raw", "horn1.mp3");
														  final String value = Uri.fromFile(file).toString();
														  // Apply the custom ringtone
														  final ContentValues values = new ContentValues(1);
														  values.put(ContactsContract.Contacts.CUSTOM_RINGTONE, value);
														  getContentResolver().update(contactUri, values, null, null);
														  } finally {
														  // Don't forget to close your Cursor
														  data.close();
														  Toast.makeText(SirensActivity.this, strName + " Successfully Adjusted"+id1, Toast.LENGTH_LONG).show();
														  }

													}*//*
											}
									});
								builderSingle.show();
								return true;
							}

					});*/



			}

		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data)
			{
				super.onActivityResult(requestCode, resultCode, data);

				switch (requestCode)
					{
					case (1):
						if (resultCode == Activity.RESULT_OK)
							{
								String path = Environment.getExternalStorageDirectory().getPath() + "/media/audio/ringtones/";
								String filename = dataNameList.get(position);
								try
									{
										Uri contactData = data.getData();
										String contactId = contactData.getLastPathSegment();
										String[] PROJECTION = new String[] {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER,};
										Cursor localCursor = getContentResolver().query(contactData, PROJECTION, null, null, null);
										localCursor.moveToFirst();
										String contactID = localCursor.getString(localCursor.getColumnIndexOrThrow("_id"));
										String contactDisplayName = localCursor.getString(localCursor.getColumnIndexOrThrow("display_name"));

										Uri localUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, contactID);
										localCursor.close();
										ContentValues localContentValues = new ContentValues();
										File k = new File(path, filename);
										localContentValues.put(ContactsContract.Data.RAW_CONTACT_ID, contactId);
										localContentValues.put(ContactsContract.Data.CUSTOM_RINGTONE, k.getAbsolutePath());
										getContentResolver().update(localUri, localContentValues, null, null);
										// Log.e("Ringtone assigned","Ringtone assigned");

										Toast.makeText(SirensActivity.this, "Ringtone assigned to: " + contactDisplayName, Toast.LENGTH_LONG).show();

									}
								catch (Exception ex)
									{
										Log.e("Ringtone assigned", ex.toString());
									}
							}
						break;
					}
			}

		protected void playName(int i)
			{
				if (mediaPlayer != null)
					{
						mediaPlayer.release();
						mediaPlayer = null;
					}

				// dataAudioList.add(i, R.drawable.album_icon);

				mediaPlayer = MediaPlayer.create(SirensActivity.this, i);
				mediaPlayer.start();


			}

		public boolean saveas(int type, int i)
			{
				byte[] buffer = null;
				InputStream fIn = getBaseContext().getResources().openRawResource(file[i]);
				int size = 0;
				try
					{
						size = fIn.available();
						buffer = new byte[size];
						fIn.read(buffer);
						fIn.close();
					}
				catch (IOException e)
					{
						// TODO: handle exception
						return false;
					}

				String path = Environment.getExternalStorageDirectory().getPath() + "/media/audio/ringtones/";
				String filename = dataNameList.get(i) + ".mp3";
				boolean exists = (new File(path)).exists();
				if (!exists)
					{
						new File(path).mkdirs();
					}
				FileOutputStream save;
				try
					{
						save = new FileOutputStream(path + filename);
						save.write(buffer);
						save.flush();
						save.close();
					}
				catch (FileNotFoundException e)
					{
						return false;
					}
				catch (Exception e)
					{
						return false;
					}
				sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path + filename)));
				File k = new File(path, filename);
				ContentValues values = new ContentValues();
				values.put(MediaStore.MediaColumns.DATA, k.getAbsolutePath());
				values.put(MediaStore.MediaColumns.TITLE, filename);
				values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");

				if (RingtoneManager.TYPE_RINGTONE == type)
					{
						values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
						Uri uri = MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath());
						Uri newUri = SirensActivity.this.getContentResolver().insert(uri, values);
						RingtoneManager.setActualDefaultRingtoneUri(SirensActivity.this, type, newUri);

						this.getContentResolver().insert(MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath()), values);
						return true;
					}
				else if (RingtoneManager.TYPE_NOTIFICATION == type)
					{
						values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
						Uri uri = MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath());
						Uri newUri = SirensActivity.this.getContentResolver().insert(uri, values);
						RingtoneManager.setActualDefaultRingtoneUri(SirensActivity.this, type, newUri);

						this.getContentResolver().insert(MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath()), values);
						return true;
					}
				else if (RingtoneManager.TYPE_ALARM == type)
					{
						values.put(MediaStore.Audio.Media.IS_ALARM, true);
						Uri uri = MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath());
						Uri newUri = SirensActivity.this.getContentResolver().insert(uri, values);
						RingtoneManager.setActualDefaultRingtoneUri(SirensActivity.this, type, newUri);

						this.getContentResolver().insert(MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath()), values);
						return true;
					}



				return false;
			}

		@Override
		protected void onDestroy()
			{
				// TODO Auto-generated method stub
				if (mediaPlayer != null)
					{
						mediaPlayer.release();
						mediaPlayer = null;
					}

				super.onDestroy();
			}

		public boolean ResetContactRingTone(String idOfContact)
			{
				try
					{


						Uri contactData = ContactsContract.Contacts.CONTENT_URI;
						String contactId = contactData.getLastPathSegment();
						Uri localUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, idOfContact);
						ContentValues localContentValues = new ContentValues();
						localContentValues.put(ContactsContract.Data.RAW_CONTACT_ID, contactId);
						localContentValues.put(ContactsContract.Data.CUSTOM_RINGTONE,"" ); // <<----ho to set here "null or missing"?
						localContentValues.putNull(ContactsContract.Data.CUSTOM_RINGTONE);
						getContentResolver().update(localUri, localContentValues, null, null);
						Toast.makeText(SirensActivity.this, "Ringtone is selected   " + idOfContact, Toast.LENGTH_LONG).show();
					}
				catch (Exception ex)
					{
						Log.e("", "ResetContactRingTone failed, Excpetion: " + ex.getMessage());
					}
				return true;
			}

		void fillData()
			{
				dataNameList.add("7assby  Raby  ");
				dataNameList.add("7ina AL Fajree");
				dataNameList.add("9iyam Al Khayr");
				dataNameList.add("Abky");
				dataNameList.add("Achraqat Nafssi");
				dataNameList.add("Adfayta");
				dataNameList.add("Aghibu");
				dataNameList.add("Aljanna Ta7t Rejlek");
				dataNameList.add("Allahu Akbar");
				dataNameList.add("Ameen 3ala Al Ummah");
				dataNameList.add("Ana Bhali");
				dataNameList.add("Ana L3abdu");

				dataNameList.add("Asmae Allah");
				dataNameList.add("Assalah Khayr Mina Nawm");
				dataNameList.add("Assalamu alaykum");
				dataNameList.add("Assmae Allah");
				dataNameList.add("Atadry Man Yujeeb");
				dataNameList.add("Bakat 3ayni");
				dataNameList.add("Bisalaty Adnou");
				dataNameList.add("Buniya Al Islam");
				dataNameList.add("Da3ouni Ounaji");
				dataNameList.add("Dawman Laka Hamdou");
				dataNameList.add("Duaa1");
				dataNameList.add("Duaa2");
				dataNameList.add("Duaa3");
				dataNameList.add("Duaa4");
				dataNameList.add("Duaa5");
				dataNameList.add("Duaa6");
				dataNameList.add("Duaa7");
				dataNameList.add("Duaa8");
				dataNameList.add("Fi 7adrat Al Ma7bub");
				dataNameList.add("Fi Jannati Khuldeen");
				dataNameList.add("Habibi Enta Rahmani");
				dataNameList.add("Hassbiya Allah");
				dataNameList.add("I’m Muslim");
				dataNameList.add("I7faz");
				dataNameList.add("ILahy A3inee");
				dataNameList.add("Ilahy");
				dataNameList.add("Iza Ma 9ala li rabi");
				dataNameList.add("Khally");
				dataNameList.add("La Ilaha ILLa Allah");
				dataNameList.add("La Illah Ila Allah  ");
				dataNameList.add("La Ta7ssb");
				dataNameList.add("Laswfa A3odu");
				dataNameList.add("Ma3aAllah");
				dataNameList.add("Mafeesh");
				dataNameList.add("Mawalaya Salle");
				dataNameList.add("Mawlaya Sally");
				dataNameList.add("Mawlaya");
				dataNameList.add("Mohammed Nabina");
				dataNameList.add("Nadaytu");
				dataNameList.add("Nidae Al Aqsa");
				dataNameList.add("Ousally bi9alby");
				dataNameList.add("Raaitu Ath Thunoob");
				dataNameList.add("Raditou Billah Rabban");
				dataNameList.add("Salatu Allah");
				dataNameList.add("Sallu 3alayhe");
				dataNameList.add("Saly ala Muhammad");
				dataNameList.add("Samtan");
				dataNameList.add("Sawfa Nab9a Hona");
				dataNameList.add("Subhana Allah");
				dataNameList.add("Takbirat AL Eid");
				dataNameList.add("Tala3a Annur");
				dataNameList.add("To2amilo Anaka");
				dataNameList.add("Wasfu Naby");
				dataNameList.add("Ya Makkah");
				dataNameList.add("Ya Mustafa");
				dataNameList.add("Ya Mustapha");
				dataNameList.add("Ya Naby Allah");
				dataNameList.add("Ya Nazeran");
				dataNameList.add("Ya Oummi");
				dataNameList.add("Ya Rabby Sally");
				dataNameList.add("Ya Taybatu");
				dataNameList.add("Yas3ad Fou2adi");
				dataNameList.add("Yawan Tatoub");
				dataNameList.add("Yurzu9 Atter");







			}

		@Override
		public void onClick(View v)
			{
				switch (v.getId())
					{

					case R.id.imageViewRating:
						// Intent intent = new Intent(Intent.ACTION_VIEW);
						// intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=Mobitsolutions&hl=en"));
						// startActivity(intent);
						Uri uri2 = Uri.parse("market://details?id=" + SirensActivity.this.getApplicationContext());
						Intent myAppLinkToMarket2 = new Intent(Intent.ACTION_VIEW, uri2);
						startActivity(myAppLinkToMarket2);
						break;
					}
			}

		public void tos()
		{
			Toast.makeText(this,"audio playing....",Toast.LENGTH_SHORT).show();
		}
		public void tos1()
		{
			Toast.makeText(this,"audio stopped....",Toast.LENGTH_SHORT).show();
		}

		private void requestAppPermissions() {
			if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
				return;
			}

			if (hasReadPermissions() && hasWritePermissions()) {
				return;
			}

			ActivityCompat.requestPermissions(this,
					new String[] {
							Manifest.permission.READ_EXTERNAL_STORAGE,
							Manifest.permission.WRITE_EXTERNAL_STORAGE
					} , MODE_ENABLE_WRITE_AHEAD_LOGGING); // your request code
		}

		private boolean hasReadPermissions() {
			return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
		}

		private boolean hasWritePermissions() {
			return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
		}

        private void requestsettingpermission() {
            if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                return;
            }
            if (hassettingPermissions()) {
                return;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.System.canWrite(getApplicationContext())) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, 200);
                }
            }
        }
        private boolean hassettingPermissions() {
            return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_APN_SETTINGS) == PackageManager.PERMISSION_GRANTED);
        }

	}