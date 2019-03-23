package kotlinHolderPayloads.UI属性探究

import kotlinHolderPayloads.UI属性探究.drama.DramaListExtras
import kt.Constructors
import kt.MB

/**
 * Created by fuzhipeng on 2019/3/22.
 */

//这种类要放到和类名相同的文件夹下 ,例如 Drama类的的UI属性要建一个drama的文件夹 里面放UI属性 方便找UI界面都了好找。

fun onbind(card: Card){
    val musicObj = card.map.getNonNull(DramaListExtras::class.java)
    musicObj.isShow=true
    musicObj.getName()
}
