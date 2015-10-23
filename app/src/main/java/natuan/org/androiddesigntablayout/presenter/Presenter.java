package natuan.org.androiddesigntablayout.presenter;


public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}