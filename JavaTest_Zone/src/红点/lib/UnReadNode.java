package 红点.lib;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zone on 2018/5/13.
 */
public class UnReadNode {

    List<UnReadNode> childList = new ArrayList<>();

    UnReadNode parent;

    int unReadCount;

    Reference<UnReadCallback> reference;

    Class cls;

    public UnReadNode(Class mClass) {
        this.cls = mClass;
    }

    public void setRedListener(UnReadCallback redListener) {
        reference = new WeakReference(redListener);
    }

    public UnReadNode addChild(UnReadNode comonpent) {
        comonpent.parent = this;
        childList.add(comonpent);
        return this;
    }

    public UnReadNode addChilds(UnReadNode... comonpents) {
        for (UnReadNode comonpent : comonpents)
            addChild(comonpent);
        return this;
    }

    public UnReadNode remove(UnReadNode comonpent) {
        childList.remove(comonpent);
        return this;
    }

    public UnReadNode addUnReadCount(int messageCount) {
        this.unReadCount += messageCount;
        notifyUnReadUpdate();
        return this;
    }

    /**
     * 向上通知 迭代消息
     */
    private void notifyUnReadUpdate() {
        UnReadCallback redListener = reference.get();

        //计算本节点的消息
        if (redListener != null) {
            //childList unReadCount+ this.unReadCount
            int totalCount = unReadCount;
            for (UnReadNode compsite : childList) {
                totalCount += compsite.unReadCount;
            }
            redListener.unreadCount(totalCount);
        }
        if (parent != null)
            parent.notifyUnReadUpdate();
    }


    public interface UnReadCallback {
        //获取消息之后
        void unreadCount(int count);


    }
}
