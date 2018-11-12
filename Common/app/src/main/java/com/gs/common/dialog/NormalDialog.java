package com.gs.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gs.common.R;
import com.gs.common.utils.ScreenSizeUtils;


public class NormalDialog {

    private TextView mTitle;
    private TextView mContent;
    private LinearLayout llRightAndLeft;
    private Button mLeftBtn;
    private Button mRightBtn;
    private Button mSingleBtn;
    private View tvLine1;
    private TextView mLine;
    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    public NormalDialog(Builder builder) {
        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.NormalDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.dialog_normal, null);
        mTitle = (TextView) mDialogView.findViewById(R.id.tvTitle);
        mContent = (TextView) mDialogView.findViewById(R.id.tvContent);
        llRightAndLeft = (LinearLayout) mDialogView.findViewById(R.id.llRightAndLeft);
        mLeftBtn = (Button) mDialogView.findViewById(R.id.leftBtn);
        mRightBtn = (Button) mDialogView.findViewById(R.id.rightBtn);
        mSingleBtn = (Button) mDialogView.findViewById(R.id.singleBtn);
        tvLine1 = mDialogView.findViewById(R.id.tvLine1);
        mLine = (TextView) mDialogView.findViewById(R.id.dialog_normal_line);
//        mDialogView.setMinimumHeight((int) (ScreenSizeUtils.getInstance(mBuilder.getContext())
//                .getScreenHeight() * builder.getHeight()));
        mDialog.setContentView(mDialogView);

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(mBuilder.getContext()).getScreenWidth() *
                builder.getWidth());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);

        initDialog(builder);
    }

    private void initDialog(Builder builder) {
        mDialog.setCanceledOnTouchOutside(builder.isTouchOutside());

        mTitle.setText(builder.getTitleText());
        mContent.setText(builder.getContentText());
        mLeftBtn.setText(builder.getLeftBtnText());
        mRightBtn.setText(builder.getRightBtnText());
        mSingleBtn.setText(builder.getSingleBtnText());

        if (builder.getTitleVisible()) {
            mTitle.setVisibility(View.VISIBLE);
        } else {
            mTitle.setVisibility(View.GONE);
        }

        if (builder.isSingleMode()) {
            mSingleBtn.setVisibility(View.VISIBLE);
            llRightAndLeft.setVisibility(View.GONE);
        }

        if (builder.isNoBtnMode()) {
            mSingleBtn.setVisibility(View.GONE);
            llRightAndLeft.setVisibility(View.GONE);
            tvLine1.setVisibility(View.GONE);
        }

        mLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickLeftButton(NormalDialog.this, mLeftBtn);
                }
            }
        });

        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getOnclickListener() != null) {
                    mBuilder.getOnclickListener().clickRightButton(NormalDialog.this, mRightBtn);
                }
            }
        });

        mSingleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getSingleListener() != null) {
                    mBuilder.getSingleListener().clickSingleButton(NormalDialog.this, mSingleBtn);
                }
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

        private boolean isSingleMode;
        private boolean noBtnMode;
        private LocalDialogInterface.OnLeftAndRightClickListener<NormalDialog> onclickListener;
        private LocalDialogInterface.OnSingleClickListener<NormalDialog> singleListener;
        private boolean isTitleVisible;
        private boolean isTouchOutside;
        private Context mContext;
        private float width;
        private String titleText;
        private String contentText;
        private String leftBtnText;
        private String rightBtnText;
        private String singleBtnText;

        public Builder(Context context) {

            mContext = context;
            isSingleMode = false;
            noBtnMode = false;
            onclickListener = null;
            singleListener = null;
            isTitleVisible = false;
            isTouchOutside = true;

            width = 0.65f;
            titleText = "提示";
            contentText = "请先扫描二维码登陆";
            leftBtnText = "确定";
            leftBtnText = "取消";
            singleBtnText = "确定";

        }

        public Context getContext() {
            return mContext;
        }


        public String getSingleBtnText() {
            return singleBtnText;
        }

        public Builder setSingleBtnText(String singleBtnText) {
            this.singleBtnText = singleBtnText;
            return this;
        }

        public String getLeftBtnText() {
            return leftBtnText;
        }

        public Builder setLeftBtnText(String leftBtnText) {
            this.leftBtnText = leftBtnText;
            return this;
        }

        public String getRightBtnText() {
            return rightBtnText;
        }

        public Builder setRightBtnText(String rightBtnText) {
            this.rightBtnText = rightBtnText;
            return this;
        }

        public String getTitleText() {
            return titleText;
        }

        public Builder setTitleText(String titleText) {
            this.titleText = titleText;
            return this;
        }

        public String getContentText() {
            return contentText;
        }

        public Builder setContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        public boolean isSingleMode() {
            return isSingleMode;
        }

        public Builder setSingleMode(boolean singleMode) {
            isSingleMode = singleMode;
            return this;
        }

        public boolean isNoBtnMode() {
            return noBtnMode;
        }

        public Builder setNoBtnMode(boolean noBtnMode) {
            this.noBtnMode = noBtnMode;
            return this;
        }

        public LocalDialogInterface.OnLeftAndRightClickListener<NormalDialog> getOnclickListener() {
            return onclickListener;
        }

        public Builder setOnclickListener(LocalDialogInterface
                                                  .OnLeftAndRightClickListener<NormalDialog>
                                                  onclickListener) {
            this.onclickListener = onclickListener;
            return this;
        }

        public LocalDialogInterface.OnSingleClickListener<NormalDialog> getSingleListener() {
            return singleListener;
        }

        public Builder setSingleListener(LocalDialogInterface.OnSingleClickListener<NormalDialog>
                                                 singleListener) {
            this.singleListener = singleListener;
            return this;
        }

        public boolean getTitleVisible() {
            return isTitleVisible;
        }

        public Builder setTitleVisible(boolean isVisible) {
            isTitleVisible = isVisible;
            return this;
        }

        public boolean isTouchOutside() {
            return isTouchOutside;
        }

        public Builder setCanceledOnTouchOutside(boolean isTouchOutside) {

            this.isTouchOutside = isTouchOutside;
            return this;
        }

        public float getWidth() {
            return width;
        }

        public Builder setWidth(float width) {
            this.width = width;
            return this;
        }

        public NormalDialog build() {
            return new NormalDialog(this);
        }
    }


}
