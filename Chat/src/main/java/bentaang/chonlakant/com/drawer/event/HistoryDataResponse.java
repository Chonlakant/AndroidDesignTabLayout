package bentaang.chonlakant.com.drawer.event;

import java.util.List;

import bentaang.chonlakant.com.drawer.bean.Message;
import bentaang.chonlakant.com.drawer.model.Message2;


/**
 * Created by matthewlogan on 9/3/14.
 */
public class HistoryDataResponse {

    public List<Message2> content;
    public HistoryDataResponse(List<Message2> content) {
        this.content = content;
    }

}
