package 红点.lib;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zone on 2018/5/13.
 * <p>
 * 设计思想：
 * 当某个树形节点收到消息，单链表不行 因为第二层可能是多个。 逆向迭代   弱引用  监听的回调
 * <p>
 * UnReadTree 一次性配置。结构清晰。 相比 activity配置中好太多
 * <p>
 *
 *
 * 设计具体细节：
 * UnReadNode(associate.class)【Compsite 与 class关联】；
 * <p>
 * activity（开始UnReadTree.setLister(UnReadCallback)【包含find的功能通过UnReadCallback.getClass】
 * =能找到这个compiste return,并且这个回调还可以处理 未读消息的通知）
 * <p>
 * 添加消息的时候 ，用这个 开始UnReadTree.setLister 返回的UnReadNode 节点去添加未读消息数量 即可！
 * <p>
 *
 *
 * 规则注意：
 * 每次UnReadTree.setLister 都会对该节点UnReadNode 的消息数量清零；
 * 每次添加消息 ，都是包含该节点 以及父节点迭代 进行 消息通知；
 */
public class UnReadTree {

    private static Map<Class, UnReadNode> map = new HashMap<>();

    public static void configure(UnReadNode comsite) {
        iterate(comsite);
    }

    private static void iterate(UnReadNode compsite) {
        if (map.get(compsite.cls) != null)
            throw new IllegalStateException("节点重复注册！");
        else
            map.put(compsite.cls, compsite);
        if (compsite.childList != null) {
            for (UnReadNode compsite1 : compsite.childList)
                iterate(compsite1);
        }
    }

    /**
     * tips:每次绑定 都会清空以前的消息数
     */
    public static UnReadNode setListener(UnReadNode.UnReadCallback redListener) {
        UnReadNode node = map.get(redListener.getClass());
        if (node == null)
            throw new IllegalStateException("该节点未注册！");
        else {
            node.unReadCount = 0;
            node.setRedListener(redListener);
            return node;
        }
    }
}
