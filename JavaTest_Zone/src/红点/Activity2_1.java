package 红点;

import 红点.lib.UnReadNode;
import 红点.lib.UnReadTree;

/**
 * Created by Zone on 2018/5/13.
 */
public class Activity2_1 implements UnReadNode.UnReadCallback {

    UnReadNode unReadNode;
    public Activity2_1() {
        unReadNode = UnReadTree.setListener(this);
        unReadNode.addUnReadCount(5);
    }

    @Override
    public void unreadCount(int count) {
        System.out.println("Activity2_1:"+count);
    }
}
