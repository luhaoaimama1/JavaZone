package 红点;

import 红点.lib.UnReadNode;
import 红点.lib.UnReadTree;

/**
 * Created by Zone on 2018/5/13.
 */
public class Activity implements UnReadNode.UnReadCallback {

    UnReadNode unReadNode;
    public Activity() {
        unReadNode = UnReadTree.setListener(this);
        unReadNode.addUnReadCount(3);
    }

    @Override
    public void unreadCount(int count) {
        System.out.println("Activity:"+count);
    }

}
