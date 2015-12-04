package bentaang.chonlakant.com.drawer.event;

import java.util.List;

import bentaang.chonlakant.com.drawer.model.Message2;


/**
 * Created by matthewlogan on 9/3/14.
 */
public class HistoryEventSuccess {

    public List<Message2> content;
    public HistoryEventSuccess(List<Message2> content) {
        this.content = content;
    }

}
