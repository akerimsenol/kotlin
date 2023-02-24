// IGNORE_BACKEND_K2_LIGHT_TREE: JVM_IR
//   Reason: KT-56755
// The local variables `y` and `i` are visible in finally blocks with old backend.
// IGNORE_BACKEND: JVM
// WITH_STDLIB
// FILE: test.kt

fun box(): String {
    try {
        for (i in 0 until 1) {
            try {
                val x = "x"
                throw RuntimeException(x)
            } catch (e: Exception) {
                val y = "y"
                return "FAIL1"
            } finally {
                return "FAIL2"
            }
        }
    } finally {
        return "OK"
    }
    return "FAIL3"
}

// EXPECTATIONS JVM JVM_IR
// test.kt:9 box:
// test.kt:10 box:
// test.kt:11 box: i:int=0:int
// test.kt:12 box: i:int=0:int
// test.kt:13 box: i:int=0:int, x:java.lang.String="x":java.lang.String
// test.kt:14 box: i:int=0:int
// test.kt:15 box: i:int=0:int, e:java.lang.Exception=java.lang.RuntimeException
// test.kt:16 box: i:int=0:int, e:java.lang.Exception=java.lang.RuntimeException, y:java.lang.String="y":java.lang.String
// test.kt:18 box: i:int=0:int
// test.kt:22 box:

// EXPECTATIONS JS_IR
// test.kt:10 box:
// test.kt:10 box:
// test.kt:10 box:
// test.kt:10 box: i=0:number
// test.kt:12 box: i=0:number
// test.kt:13 box: i=0:number, x="x":kotlin.String
// test.kt:14 box: i=0:number, x="x":kotlin.String
// test.kt:14 box: i=0:number, x="x":kotlin.String
// test.kt:15 box: i=0:number, x="x":kotlin.String, e=kotlin.RuntimeException
// test.kt:16 box: i=0:number, x="x":kotlin.String, e=kotlin.RuntimeException, y="y":kotlin.String
// test.kt:18 box: i=0:number, x="x":kotlin.String, e=kotlin.RuntimeException, y="y":kotlin.String
// test.kt:22 box: i=0:number, x="x":kotlin.String, e=kotlin.RuntimeException, y="y":kotlin.String
