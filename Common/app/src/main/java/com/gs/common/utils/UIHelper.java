package com.gs.common.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gs.common.R;


public class UIHelper {
	
	private static Toast mToast;
	public static Dialog mLoadDialog;

	public static void showLoadDialog(Context context) {
		showLoadDialog(context, null);
	}

	public static void showLoadDialog(Context context, String msg) {
		if (context == null) {
			return;
		}
		if (context.isRestricted())
			if (mLoadDialog != null && mLoadDialog.isShowing())
				return;
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View login_dialog = inflater.inflate(R.layout.load_dialog, null);
		mLoadDialog = new Dialog(context, R.style.load_dialog);
		mLoadDialog.setCanceledOnTouchOutside(false);
		
		if (!TextUtils.isEmpty(msg)) {
			TextView messageTV = (TextView) login_dialog.findViewById(R.id.login_doag_name);
			messageTV.setText(msg);
		}
		
		mLoadDialog.setContentView(login_dialog);
		try {
			mLoadDialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void cloesLoadDialog() {
		if (mLoadDialog != null && mLoadDialog.isShowing()) {
			mLoadDialog.dismiss();
			mLoadDialog = null;
		}
	}

}





