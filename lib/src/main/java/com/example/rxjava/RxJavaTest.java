//package com.example.rxjava;
//
//import com.example.Log;
//import java.io.File;
//import rx.Observable;
//import rx.Observer;
//import rx.Subscriber;
//import rx.functions.Action0;
//import rx.functions.Action1;
//import rx.functions.Func1;
//import rx.schedulers.Schedulers;
//
///**
// * RxJava的基本原理：
// * 元素有：观察者(Observer)、被观察者(Observable)、订阅方法(subscribe)、事件(onNext,onError,onCompleted)
// * 核心过程(工作的流程控制在被观察者这边)：
// * 1.被观察者通过订阅方法关联观察者，从而被观察者在有需要的时候，通过被观察者的引用，将事件传递出来
// * 2.观察者有两个类：Observer和其子类Subscriber,被观察者调用subscribe方法订阅观察者后，
// * 被观察者 首先判断观察者是否是Subscriber,如果是Observer，则会将你Observer构造成其子类Subscriber.
// * 被观察者 然后调用Subscriber的onStart方法
// * 被观察者 然后调用自身方法call,call方法中定义了主要的工作流程，逻辑处理和观察者回调顺序
// */
//
//public class RxJavaTest {
//    public static final String TAG = RxJavaTest.class.getSimpleName();
//
//    public static void main(String[] args) {
//        lambda();
//    }
//
//    /**
//     * 自定义Observer\Observable
//     * 回调逻辑由被观察者call中的代码定义
//     */
//    private static void rxJavaMethod_Observer() {
//        //自定义观察者
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                Log.i(TAG, "onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.i(TAG, "onError:" + e.getMessage());
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.i(TAG, "onNext:" + s);
//            }
//
//        };
//        //自定义被观察者,call方法中,定义了观察者回调的逻辑
//        Observable<String> oob = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("1");
//                subscriber.onNext("2");
//                subscriber.onNext("3");
//                //if happen error,call the method
////                subscriber.onError();
//                subscriber.onCompleted();
//
//            }
//        });
//
//        oob.subscribe(observer);
//    }
//
//    /**
//     * 自定义Subscriber\Observable
//     * 回调逻辑由call中的代码定义
//     * <p>
//     * Subscriber 继承于Observer
//     * 主要区别是多了onStart和unsubscribe方法
//     * 在subscribe订阅方法的时候,会调用onStart方法
//     */
//    private static void rxJavaMethod_Subscriber() {
//        //自定义观察者
//        Subscriber<String> observer = new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                Log.i(TAG, "onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.i(TAG, "onError:" + e.getMessage());
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.i(TAG, "onNext:" + s);
//            }
//
//            @Override
//            public void onStart() {
//                super.onStart();
//                Log.i(TAG, "onStart:");
//            }
//        };
//        //自定义被观察者,call方法中,定义了观察者回调的逻辑
//        Observable<String> oob = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("1");
//                subscriber.onNext("2");
//                subscriber.onNext("3");
//                //if happen error,call the method
////                subscriber.onError();
//                subscriber.onCompleted();
//
//            }
//        });
//
//        oob.subscribe(observer);
//
//        if (!observer.isUnsubscribed()) {
//            observer.unsubscribe();
//        }
//    }
//
//    /**
//     * 其他的一些语法
//     */
//    private static void rxJavaMethod_just_from() {
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onCompleted() {
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.i("rxJavaMethod_just_from", s);
//            }
//        };
//
//        Observable<String> observable = Observable.from(new String[]{"1", "2"});
//        observable.subscribe(observer);
//
//        Observable<String> observable1 = Observable.just("11", "22");
//        observable1.subscribe(observer);
//    }
//
//    /**
//     * action组合替换观察者
//     */
//    private static void rxJavaMethod_Action() {
//        Observable<String> observable = Observable.from(new String[]{"11", "22"});
//        //Action0 无参无返回,常用于onComlete
//        Action0 action0 = new Action0() {
//            @Override
//            public void call() {
//                Log.i(TAG, "onComplete");
//            }
//        };
//        //Action1 有参无返回,常用于onNext和onError
//        Action1<String> actionNext = new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.i(TAG, "actionNext:" + s);
//            }
//        };
//
//        Action1<Throwable> actionError = new Action1<Throwable>() {
//            @Override
//            public void call(Throwable s) {
//                Log.i(TAG, "actionError:" + s.getMessage());
//            }
//        };
//
//        observable.subscribe(actionNext, actionError);
//        observable.subscribe(actionNext, actionError, action0);
//    }
//
//    /**
//     * flatMap,filter,map
//     * 这三个方法的参数都是Func1<param1,param2>
//     * param1，泛型参数类型实例类型，即传入的类型
//     * param2,在三个方法中各有不同
//     */
//    private static void rxJavaMethod_flatMap_filter_map() {
//        File[] folders = new File[]{new File("1"), new File("2")};
//
//        Observable.from(folders)
//                .flatMap(new Func1<File, Observable<File>>() {
//                    @Override
//                    public Observable<File> call(File file) {
//                        return Observable.from(file.listFiles());
//                    }
//                })
//                .filter(new Func1<File, Boolean>() {
//                    @Override
//                    public Boolean call(File file) {
//                        return file.getName().endsWith(".png");
//                    }
//                })
//                .map(new Func1<File, String>() {
//                    @Override
//                    public String call(File file) {
//                        return file.toString();
//                    }
//                }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String bitmap) {
//            }
//        });
//    }
//
//    /**
//     * 有待进一步验证
//     */
//    private static void rxJavaMethod_Schedule() {
//        Log.i(TAG, "local threadName :" + Thread.currentThread().getName());
//        Observable.just("1", "2")
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.newThread())
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Log.i(TAG, "Action1 call :" + Thread.currentThread().getName());
//                    }
//                });
//    }
//
//    /**
//     * map方法,映射，通常用于一对一的映射，将事件对象 由一种类型 映射成 另一种类型
//     * 比如指定一组学生Student
//     * 来获取学生的姓名
//     */
//    private static void rxJavaMethod_map() {
//        Student std0 = new Student("std0", 0);
//        Student std1 = new Student("std1", 1);
//        Student[] ss = {std0, std1};
//        Observable.from(ss).map(new Func1<Student, String>() {
//            @Override
//            public String call(Student student) {
//                return student.getName();
//            }
//        }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.i(TAG, s);
//            }
//        });
//    }
//
//    /**
//     * flatMap:一对多的映射关系,将事件对象  转化成  该事件对象所关联的多个属性
//     * 比如指定一组学生，得到 每个学生的多个课程
//     * 由于可以在嵌套的 Observable 中添加异步代码,flatMap() 也常用于嵌套的异步操作,例如嵌套的网络请求.
//     */
//    private static void rxJavaMethod_flatMap() {
//        Student std0 = new Student("std0", 0);
//        Student std1 = new Student("std1", 1);
//        Student[] ss = {std0, std1};
//        Observable.from(ss).flatMap(new Func1<Student, Observable<String>>() {
//            @Override
//            public Observable<String> call(Student student) {
//                //可以做一些异步操作
//                //比如网络请求
//                //相比于传统的异步来说，此处可以直接写请求代码，同步实现
//                Log.i(TAG, "---------");
//                return Observable.from(student.getCourse());
//            }
//        }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.i(TAG, s);
//            }
//        });
//    }
//    /**
//     * lift是Observable的实例方法，是map和flatMap的核心实现方法!
//     * lift的核心代码原型如下：
//     * public <R> Observable<R> lift(Operator<? extends R, ? super T> operator) {
//     * return
//     * Observable.create(new OnSubscribe<R>()
//     * {
//     *          @Override public void call(Subscriber subscriber) {
//     *          Subscriber newSubscriber = operator.call(subscriber);
//     *          newSubscriber.onStart();
//     *          onSubscribe.call(newSubscriber)}
//     * });
//     * }
//     */
//    private static void rxJavaMethod_lift() {
//        Observable ob = Observable.from(new String[]{"1", "2"});
//        ob.lift(new Observable.Operator<String, String>() {
//            @Override
//            public Subscriber<? super String> call(Subscriber<? super String> subscriber) {
//                return null;
//            }
//        });
//    }
//    private static void lambda(){
//        Observable.just("Hello, world!")
//                .map(s -> s + " -Dan")
//                .subscribe(ss -> System.out.println(ss));
//    }
//}
