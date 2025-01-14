// ISSUE: KT-64257

interface Base {
    fun <R> fold(initial: R, operation: (R, String) -> R): R = operation(initial, "K")
}

interface Derived<T> : Base

class Impl<T> : Derived<T>

fun box(): String {
    val impl = Impl<String>()
    val delegated = object : Derived<String> by impl {}
    return delegated.fold("O") { a, b -> a + b }
}
