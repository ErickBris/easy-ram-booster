package com.raihanbd.easyrambooster;

import java.util.Calendar;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class TaskDialog extends DialogFragment implements
		android.view.View.OnClickListener {

	private Dialog dialog = null;
	private Button btnOK;
	private TextView txtProcessKilled;
	private TextView txtMemoryCleaned;
	private TextView txtCacheCleaned;

	private String processKilled;
	private String memoryCleaned;
	private String cacheCleaned;
	private DialogDismissListener listener;

	// define task listener for track dialog event
	public interface DialogDismissListener {
		public void onDialogDismiss();
	}

	public TaskDialog() {

	}

	public void setDialogListener(TaskFragment context) {
		this.listener = (DialogDismissListener) context ;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		dialog = new Dialog(getActivity());
		dialog.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		dialog.setContentView(R.layout.boost_dialog);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));
		txtProcessKilled = (TextView) dialog
				.findViewById(R.id.txtDialogProcessKilled);
		txtMemoryCleaned = (TextView) dialog
				.findViewById(R.id.txtMemoryCleaned);
		txtCacheCleaned = (TextView) dialog.findViewById(R.id.txtCacheCleaned);
		txtCacheCleaned.setVisibility(View.GONE);
		btnOK = (Button) dialog.findViewById(R.id.btnDialogOK);
		btnOK.setOnClickListener(this);

		ShowInfo();

		return dialog;
	}

	public void ShowInfo() {

		/*
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(System.currentTimeMillis());
		Date date = cl.getTime();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");

		SharedPreferences prefs = getActivity().getSharedPreferences(
				"last_boost", Context.MODE_PRIVATE);

		SharedPreferences.Editor edit = prefs.edit();
		edit.putString("boost_time", format.format(date));
		edit.putString("memory_cleaned", getMemoryCleaned());
		edit.putString("cache_cleaned", getCacheCleaned());
		edit.commit();
		*/

		txtProcessKilled.setText("Process Killed: " + getProcessKilled());
		txtMemoryCleaned.setText("Memory Cleaned: " + getMemoryCleaned());
	}

	private String todayDate() {
		
		Calendar calob = Calendar.getInstance();
		int x = calob.get(Calendar.DAY_OF_MONTH);
		int y = (calob.get(Calendar.MONTH) + 1);
		String D = (x < 10) ? "0" + x : "" + x;
		String M = (y < 10) ? "0" + y : "" + y;
		String Y = "" + calob.get(Calendar.YEAR);
		String hr = String.valueOf(calob.HOUR);
		String min = String.valueOf(calob.MINUTE);
		StringBuilder today = new StringBuilder().append(D).append("/")
				.append(M).append("/").append(Y).append(" ").append(hr)
				.append(":").append(min);
		return today.toString();
	}

	public String getProcessKilled() {
		return processKilled;
	}

	public void setProcessKilled(String processKilled) {
		this.processKilled = processKilled;
	}

	public String getMemoryCleaned() {
		return memoryCleaned;
	}

	public void setMemoryCleaned(String memoryCleaned) {
		this.memoryCleaned = memoryCleaned;
	}

	public String getCacheCleaned() {
		return cacheCleaned;
	}

	public void setCacheCleaned(String cacheCleaned) {
		this.cacheCleaned = cacheCleaned;
	}

	@Override
	public void onClick(View v) {
		listener.onDialogDismiss();
		TaskDialog.this.dismiss();
	}
}
