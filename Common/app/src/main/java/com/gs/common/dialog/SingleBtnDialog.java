package com.gs.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gs.common.R;



/**
 * Created by Administrator on 2018/1/18.
 */

public class SingleBtnDialog {

    TextView tvMiddle;
    TextView tvLeft;
    TextView tvRight;
    TextView tvDetails;
    TextView tvBottom;

    ImageView ivLogo;


    RelativeLayout rlClickPay;

    private Builder mBuilder;

    private Dialog mDialog;
    private View mDialogView;

    public SingleBtnDialog(Builder builder) {
        this.mBuilder = builder;

        mDialog = new Dialog(mBuilder.getContext(), R.style.NormalDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.dialog_single_btn, null);

        tvMiddle = (TextView) mDialogView.findViewById(R.id.tvMiddle);
        tvLeft = (TextView) mDialogView.findViewById(R.id.tvLeft);
        tvRight = (TextView) mDialogView.findViewById(R.id.tvRight);
        tvDetails = (TextView) mDialogView.findViewById(R.id.tvDetails);
        tvBottom = (TextView) mDialogView.findViewById(R.id.tvBottom);

        ivLogo = (ImageView) mDialogView.findViewById(R.id.ivLogo);

        rlClickPay = (RelativeLayout) mDialogView.findViewById(R.id.rlClickPay);

        mDialog.setContentView(mDialogView);

        initDialog(builder);
    }

    private void initDialog(Builder builder) {
        mDialog.setCanceledOnTouchOutside(builder.isTouchOutside());

        tvMiddle.setText(builder.getTvMiddle());
        tvRight.setText(builder.getTvRight());
        tvLeft.setText(builder.getTvLeft());
        tvDetails.setText(builder.getTvDetails());
        tvBottom.setText(builder.getTvBottom());

        ivLogo.setImageResource(builder.getImageResId());

        rlClickPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getSingleListener() != null) {
                    mBuilder.getSingleListener().clickSingleButton(SingleBtnDialog.this,
                            rlClickPay);
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
        private LocalDialogInterface.OnSingleClickListener<SingleBtnDialog> singleListener;
        private Context mContext;

        private String tvMiddle;
        private String tvLeft;
        private String tvRight;
        private String tvDetails;
        private String tvBottom;

        private int imageResId = -1;

        private boolean isTouchOutside;

        public Builder(Context context) {
            mContext = context;
            isTouchOutside = true;
            singleListener = null;

            tvMiddle = "1";
            tvLeft = "支付";
            tvRight = "块钱";
            tvDetails = "即可查看完整的结果详情";
            tvBottom = "支付看结果";

            imageResId = R.mipmap.pay_icon;
        }


        public Context getContext() {
            return mContext;
        }

        public int getImageResId() {
            return imageResId;
        }

        public Builder setImageResId(int imageResId) {
            this.imageResId = imageResId;
            return this;
        }

        public String getTvBottom() {
            return tvBottom;
        }

        public Builder setTvBottom(String tvBottom) {
            this.tvBottom = tvBottom;
            return this;
        }

        public String getTvDetails() {
            return tvDetails;
        }

        public Builder setTvDetails(String tvDetails) {
            this.tvDetails = tvDetails;
            return this;
        }

        public String getTvRight() {
            return tvRight;
        }

        public Builder setTvRight(String tvRight) {
            this.tvRight = tvRight;
            return this;
        }

        public String getTvLeft() {
            return tvLeft;
        }

        public Builder setTvLeft(String tvLeft) {
            this.tvLeft = tvLeft;
            return this;
        }

        public String getTvMiddle() {
            return tvMiddle;
        }

        public Builder setTvMiddle(String txPayMoney) {
            this.tvMiddle = txPayMoney;
            return this;
        }

        public boolean isTouchOutside() {
            return isTouchOutside;
        }

        public Builder setCanceledOnTouchOutside(boolean isTouchOutside) {
            this.isTouchOutside = isTouchOutside;
            return this;
        }


        public LocalDialogInterface.OnSingleClickListener<SingleBtnDialog> getSingleListener() {
            return singleListener;
        }

        public Builder setSingleListener(LocalDialogInterface.OnSingleClickListener<SingleBtnDialog>
                                                 singleListener) {
            this.singleListener = singleListener;
            return this;
        }

        public SingleBtnDialog build() {
            return new SingleBtnDialog(this);
        }


    }


}
