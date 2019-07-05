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