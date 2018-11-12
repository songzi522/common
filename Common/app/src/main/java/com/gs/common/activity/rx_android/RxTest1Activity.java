package com.gs.common.activity.rx_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gs.common.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class RxTest1Activity extends AppCompatActivity {
    private static final String TAG = "RxTest1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test1);

//        sender.subscribe(receiver);
//        normalObservable.subscribe(receiver);
//        justObservable.subscribe(receiver);


//        fromObservable.subscribe((Consumer<? super List<String>>) receiver);  会报错
//        intervalObservable.subscribe(receiver); 会报错
//        rangeObservable.subscribe(receiver);  会报错


    }

    Observable<String> sender = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> e) throws Exception {
            //发送数据"Hello World"
            e.onNext("Hello World");
        }
    });

    //1.使用create( ),最基本的创建方式：
    Observable<String> normalObservable = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> e) throws Exception {
            e.onNext("create1");//发射一个"create1"的String
            e.onNext("create2");//发射一个"create2"的String
            e.onComplete();//发射完成,这种方法需要手动调用onCompleted，才会回调Observer的onCompleted方法
        }
    });

    //2.使用just( )，将为你创建一个Observable并自动为你调用onNext( )发射数据：
    Observable<String> justObservable = Observable.just("just1", "just2");

    //3.使用from( )，遍历集合，发送每个item：
    List<String> list = new ArrayList<>();

    private List<String> methodList() {
        list.add("from1");
        list.add("from2");
        list.add("from3");
        return list;
    }

    Observable<List<String>> fromObservable = Observable.fromArray(methodList());

    //4.使用defer( )，有观察者订阅时才创建Observable，并且为每个观察者创建一个新的Observable：
    Observable<String> deferObservable=Observable.defer(new Callable<ObservableSource<? extends String>>() {
        @Override
        public ObservableSource<? extends String> call() throws Exception {
            return null;
        }
    });

    //5.使用interval( ),创建一个按固定时间间隔发射整数序列的Observable，可用作定时器：
    Observable<Long> intervalObservable = Observable.interval(1, TimeUnit.SECONDS);//每隔一秒发送一次

    //6.使用range( ),创建一个发射特定整数序列的Observable，第一个参数为起始值，第二个为发送的个数，如果为0则不发送，负数则抛异常：
    Observable<Integer> rangeObservable = Observable.range(10, 5);//将发送整数10，11，12，13，14

    //7.使用timer( ),创建一个Observable，它在一个给定的延迟后发射一个特殊的值，等同于Android中Handler的postDelay( )方法：
    Observable<Long> timeObservable = Observable.timer(3, TimeUnit.SECONDS);  //3秒后发射一个值

    //8.使用repeat( ),创建一个重复发射特定数据的Observable:
    Observable<String> repeatObservable = Observable.just("repeatObservable").repeat(3);//重复发射3次





    Observer<String> receiver = new Observer<String>() {

        @Override
        public void onSubscribe(Disposable d) {

        }

        //正常接收数据调用
        @Override
        public void onNext(String value) {
            //将接收到来自sender的问候"Hi，World！"
            Log.e(TAG, value);
        }

        //发生错误调用
        @Override
        public void onError(Throwable e) {

        }

        //数据接收完成时调用
        @Override
        public void onComplete() {

        }
    };


}
