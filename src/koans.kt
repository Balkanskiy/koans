import java.util.*

class JavaCode {
    fun toJSON(collection: Collection<Int>): String {
        val sb = StringBuilder()
        sb.append("[")
        val iterator = collection.iterator()
        while (iterator.hasNext()) {
            val element = iterator.next()
            sb.append(element)
            if (iterator.hasNext()) {
                sb.append(", ")
            }
        }
        sb.append("]")
        return sb.toString()
    }
}

fun containsEven(collection: Collection<Int>) = collection.any { it % 2 === 0 }
fun containsEvenWithIt(collection: Collection<Int>) = collection.any { i -> i % 2 === 0 }

val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"
fun getPattern(): String = """\d{2} $month \d{4}""";

// investigate data annotation for classes
data class Person(val name: String, val age: Int)

fun getPeople(): List<Person> {
    return listOf(Person("Alice", 29), Person("Bob", 31))
}


// safe calls

fun sendMessageToClient(client: Client?, message: String?, mailer: Mailer) {
    if (client == null || message == null) return
    var email = client?.personalInfo?.email

    email?.let{
        mailer.sendMessage(email, message)
    }

}

class Client (val personalInfo: PersonalInfo?)
class PersonalInfo (val email: String?)
interface Mailer {
    fun sendMessage(email: String, message: String)
}

// cast is or !is
fun eval(expr: Expr): Int =
    when (expr) {
        is Num -> expr.value
        is Sum -> eval(expr.left) + eval(expr.right)
        else -> throw IllegalArgumentException("Unknown expression")
    }

interface Expr

// data expression
data class RationalNumber(val numerator: Int, val denominator: Int)

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun Int.r(): RationalNumber = RationalNumber(this, 1);
fun Pair<Int, Int>.r(): RationalNumber = RationalNumber(this.first, this.second);

var a: Int = 12;

//to main
//a.r()
//
//println(a.r())

//object expressions
fun getList(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    Collections.sort(arrayList, object : Comparator<Int> {
        override fun compare(a: Int, b: Int): Int {
            return b - a
        }
    })
    return arrayList
}

//sort with lamda
fun getListWithLambda(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    Collections.sort(arrayList, { x, y -> y.compareTo(x) })
    return arrayList
}

//override operators
data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override operator fun compareTo(other: MyDate): Int {
        if(year != other.year) {
            return year - other.year
        }

        if(month != other.month) {
            return month - other.month
        }

        if(dayOfMonth != other.dayOfMonth) {
            return dayOfMonth - other.dayOfMonth
        }

        return 0

    }
}

// test ranges
class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(date: MyDate): Boolean {
        if (start.year == date.year) {
            if(start.month == date.month) {
                if (start.dayOfMonth == date.dayOfMonth) {
                    return true
                }
                return start.dayOfMonth < date.dayOfMonth
            }

            return start.month < date.month
        }

        if (date.year == endInclusive.year) {
            if(date.month == endInclusive.month) {
                if (date.dayOfMonth == endInclusive.dayOfMonth) {
                    return true
                }
            }

            return date.month < endInclusive.month
        }

        return start.year < date.year && date.year < endInclusive.year

        // or if "<" operator was overridden
        //return start <= date && date <= endInclusive
    }
}


fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in DateRange(first, last)
}
// check range
//val dateStart = MyDate(2014,1,11)
//val dateEnd = MyDate(2019,12,20)
//val currentDate = MyDate(2019,11,31)
//
//
//println(checkInRange(currentDate, dateStart, dateEnd))