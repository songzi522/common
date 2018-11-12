package com.gs.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.gs.common.R;


/**
 * Created by Administrator on 2018/1/19.
 */

public class BottomSelectDialog {

    private Dialog mDialog;
    private View mDialogView;

    private Builder mBuilder;

    RelativeLayout rlWechatPay;
    RelativeLayout rlCancel;

    public BottomSelectDialog(Builder builder) {
        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.bottomDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.bottom_dialog, null);

        rlWechatPay = (RelativeLayout) mDialogView.findViewById(R.id.rlWechatPay);
        rlCancel = (RelativeLayout) mDialogView.findViewById(R.id.rlCancel);

        mDialog.setContentView(mDialogView);

        //设置dialog的宽
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);

        initDialog(builder);

    }

    private void initDialog(Builder builder) {
        mDialog.setCanceledOnTouchOutside(builder.isTouchOutside());

        rlWechatPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBuilder.getOnWechatPayClickListener() != null) {
                    mBuilder.getOnWechatPayClickListener().clickWechatPayButton(BottomSelectDialog.this, rlWechatPay);
                }
            }
        });

        rlCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }


    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public Dialog getDialog() {
        return mDialog;
    }

    public static class Builder {
        private LocalDialogInterface.OnWechatPayClickListener<BottomSelectDialog> onWechatPayClickListener;

        private boolean isTouchOutside;
        private Context mContext;

        public Builder(Context context) {
            mContext = context;
            onWechatPayClickListener = null;
            isTouchOutside = true;
        }

        public Context getContext() {
            return mContext;
        }

        public boolean isTouchOutside() {
            return isTouchOutside;
        }

        public LocalDialogInterface.OnWechatPayClickListener<BottomSelectDialog> getOnWechatPayClickListener() {
            return onWechatPayClickListener;
        }

        public Builder setOnWechatPayClickListener(LocalDialogInterface.OnWechatPayClickListener<BottomSelectDialog>
                                                           onWechatPayClickListener) {
            this.onWechatPayClickListener = onWechatPayClickListener;
            return this;
        }




        public BottomSelectDialog build() {
            return new BottomSelectDialog(this);
        }
    }

}
