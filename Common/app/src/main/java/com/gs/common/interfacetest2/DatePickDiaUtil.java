package com.gs.common.interfacetest2;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.gs.common.R;
import com.gs.common.interfacetest2.datePick.NumericWheelAdapter;
import com.gs.common.interfacetest2.datePick.OnWheelScrollListener;
import com.gs.common.interfacetest2.datePick.WheelView;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/7/4.
 */
public class DatePickDiaUtil {

    private LayoutInflater inflater = null;
    private WheelView year;
    private WheelView month;
    private WheelView day;
    private WheelView hour;
    private WheelView mins;
    PopupWindow menuWindow;
    private int curYear;

    private String str;

    private Context mContext;

    public StrPost strPost;

    public DatePickDiaUtil(Context mContext, StrPost strPost) {
        this.mContext = mContext;
        this.strPost = strPost;
    }

    public interface StrPost {
        void postData(String str);
    }

    /**
     * 初始化popupWindow
     *
     * @param view
     */
    public void showPopwindow(View view) {
        menuWindow = new PopupWindow(view, ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        menuWindow.setFocusable(true);
        menuWindow.setBackgroundDrawable(new BitmapDrawable());
        menuWindow.showAtLocation(view, Gravity.CENTER_HORIZONTAL, 0, 0);
        menuWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                menuWindow = null;
            }
        });
    }

    public View getDataPick() {
        Calendar c = Calendar.getInstance();
        curYear = c.get(Calendar.YEAR);
        int curMonth = c.get(Calendar.MONTH) + 1;//通过Calendar算出的月数要+1
        int curDate = c.get(Calendar.DATE);
        int curHour = c.get(Calendar.HOUR_OF_DAY);
        int curMinute = c.get(Calendar.MINUTE);

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.datepick, null);

        year = (WheelView) view.findViewById(R.id.year);

        year.setAdapter(new NumericWheelAdapter(curYear - 5, curYear + 5));

        year.setLabel("年");
        year.setCyclic(true);
        year.addScrollingListener(scrollListener);

        month = (WheelView) view.findViewById(R.id.month);
        month.setAdapter(new NumericWheelAdapter(1, 12));
        month.setLabel("月");
        month.setCyclic(true);
        month.addScrollingListener(scrollListener);

        day = (WheelView) view.findViewById(R.id.day);
        initDay(curYear, curMonth);
        day.setLabel("日");
        day.setCyclic(true);

        hour = (WheelView) view.findViewById(R.id.hour);
        hour.setAdapter(new NumericWheelAdapter(0, 23));
        hour.setLabel("时");
        hour.setCyclic(true);

        mins = (WheelView) view.findViewById(R.id.mins);
        mins.setAdapter(new NumericWheelAdapter(0, 59));
        mins.setLabel("分");
        mins.setCyclic(true);

        year.setCurrentItem(curYear - (curYear - 5));
        month.setCurrentItem(curMonth - 1);
        day.setCurrentItem(curDate - 1);
        hour.setCurrentItem(curHour);
        mins.setCurrentItem(curMinute);

        Button bt = (Button) view.findViewById(R.id.set);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = (Integer.parseInt(year.getCurrentItem()) + (curYear - 5)) + "-" +
                        (Integer.parseInt(month.getCurrentItem()) + 1) + "-"
                        + (Integer.parseInt(day.getCurrentItem()) + 1) + " " + hour.getCurrentItem()
                        + ":" + mins.getCurrentItem();
                strPost.postData(str);
                menuWindow.dismiss();
            }
        });
        Button cancel = (Button) view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuWindow.dismiss();
            }
        });
        return view;
    }


    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {

        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            int n_year = Integer.parseInt(year.getCurrentItem()) + 1950;
            int n_month = Integer.parseInt(month.getCurrentItem()) + 1;
            initDay(n_year, n_month);
        }
    };

    /**
     * @param year
     * @param month
     * @return
     */
    private int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = flag ? 29 : 28;
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }

    private void initDay(int arg1, int arg2) {
        day.setAdapter(new NumericWheelAdapter(1, getDay(arg1, arg2), "%02d"));
    }

}
