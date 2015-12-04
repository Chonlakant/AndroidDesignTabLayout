package bentaang.chonlakant.com.drawer.presenter;


public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}