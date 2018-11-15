package 算法.游戏


/**
 * Created by fuzhipeng on 2018/7/11.
 */
class View(val width: Int, val height: Int) {
    var x = 0
    var y = 0

    fun left(): Int = x - width / 2;
    fun right(): Int = x + width / 2;

    fun onTouch(x: Float, y: Float) {
        //check outRect正好大于 view大小

        var outRect = Rect(10F, 10F, 1000F, 1500F)
        if (left()+x < outRect.x) {
            this@View.x = (outRect.x + width / 2).toInt()
        }else{
            this@View.x += x.toInt()
        }

        if (right() > outRect.x2) {
            this@View.x = (outRect.x2 - width / 2).toInt()
        }
        print_()
    }

    fun print_()= print("left=${left()} \t right=${right()}")
}

fun main(args: Array<String>) {

    View(100, 150).onTouch(10000F, 0F)

}

class Rect(val x: Float, val y: Float, val x2: Float, val y2: Float)

