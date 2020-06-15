package 算法.每日一题

object L1360日期之间间隔多少天 {


    @JvmStatic
    fun main(args: Array<String>) {
//        println(daysBetweenDates("2019-06-29", "2019-06-30"))
//        println(daysBetweenDates("2020-01-15",  "2019-12-31"))
//        println(daysBetweenDates( "2008-03-21",      "2041-05-08"))
        println(daysBetweenDates("2008-03-21", "2009-05-08"))
//        365+30+8+9
    }

    //date2>date1
    fun daysBetweenDates(date1: String, date2: String): Int {
        var d1: String = date1
        var d2: String = date2
        if (Integer.parseInt(date2.replace("-", "")) < Integer.parseInt(date1.replace("-", ""))) {
            d1 = date2
            d2 = date1
        }

        val date1Split = d1.split("-")
        val date2Split = d2.split("-")
        var result = 0
        //中间的年  12-14  13
        val d1Year = Integer.parseInt(date1Split[0])
        val d1Moth = Integer.parseInt(date1Split[1])
        val d1Day = Integer.parseInt(date1Split[2])

        val d2Year = Integer.parseInt(date2Split[0])
        val d2Moth = Integer.parseInt(date2Split[1])
        val d2Day = Integer.parseInt(date2Split[2])
        for (i in d1Year + 1 until d2Year) {
            result += if (isLeapYear(i)) 366 else 365
        }

        if (d1Year == d2Year) {
            result += timeDays(d2Year, d2Moth, d2Day, true) - timeDays(d1Year, d1Moth, d1Day, true)
        } else {
            //某年 某月的后多少天  包含当天
            result += timeDays(d1Year, d1Moth, d1Day, false)
            //某年 某月的前多少天 不包含当天
            result += timeDays(d2Year, d2Moth, d2Day, true)
        }
        return result
    }

    //某年 某月的后多少天  包含当天

    //某年 某月的前多少天 不包含当天
    fun timeDays(year: Int, month: Int, day: Int, isBefore: Boolean): Int {
        var result = 0
        //计算之前月的总天数
        for (i in 1 until month) {
            result += if (is31(i)) 31
            else if (i == 2) {
                if (isLeapYear(year)) 29
                else 28
            } else 30
        }
        //计算当月天数  但是不含当天 那么最后就是-1
        result += (day - 1)
        return if (isBefore) result
        else (if (isLeapYear(year)) 366 else 365) - result
    }


    //因为提示里给定的日期是 1971 年到 2100 年之间的有效日期。 所以算距离1971，1，1的天数
    fun gap(year: Int, month: Int, day: Int): Int {

        val Month = arrayOf(
                arrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31),
                arrayOf(0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31))

        val Day = intArrayOf(365, 366)
        val flag = if (isLeapYear(year)) 1 else 0
        var result = 0

        for (i in 1971 until year) {   //从年开始
            result += Day[if (isLeapYear(i)) 1 else 0]
        }
        for (i in 1 until month) { //从月开始
            result += Month[flag][i]
        }
        result += day
        return result
    }


    fun isLeapYear(year: Int): Boolean {
        val cond1 = year % 400 == 0
        val cond2 = year % 4 == 0 && year % 100 != 0
        return cond1 || cond2
    }

    //1、3、5、7、8、10、12月每月31天
    //2月闰年29天,不是闰年就是28天.
    //其他的月份就是三十天每月.
    fun is31(month: Int): Boolean {
        return when (month) {
            1, 3, 5, 7, 8, 10, 12 -> true
            else -> false
        }
    }
}