package it.unibo.pps.u02

object Tasks {

  @main
  def main(): Unit = {

    //3.a
    def positive(x: Int): String = x match
      case x if x >= 0 => "positive"
      case _ => "negative"
    val a = 10
    println(positive(a))

    val positive2: Int => String =
      case x if x >= 0 => "positive"
      case _ => "negative"
    println(positive2(a))

    //3.b
    val neg: (String => Boolean) => (String => Boolean) = predicate => !predicate(_)

    val empty: String => Boolean = _ == "" // predicate on strings
    val notEmpty = neg(empty) // which type of notEmpty?
    println(notEmpty("foo")) // true
    println(notEmpty("")) // false
    println(notEmpty("foo") && !notEmpty("")) // true.. a comprehensive test

    //4
    val p1: Int => Int => Int => Boolean = x => y => z => x match
      case _ => (x <= y && y == z)
    println(p1(1)(2)(2))
    println(p1(5)(2)(2))
    val p2: (Int, Int, Int) => Boolean = (x: Int, y: Int, z: Int) => x match
      case _ => (x <= y && y == z)
    println(p2(1,2,2))
    println(p2(5,2,2))
    def p3(x: Int)(y: Int)(z: Int): Boolean = x match
      case _ => (x <= y && y == z)
    //def p3(x: Int)(y: Int)(z: Int): Boolean = (x <= y && y == z)
    println(p3(1)(2)(2))
    println(p3(5)(2)(2))
    def p4(x: Int, y: Int, z: Int): Boolean = x match
      case _ => (x <= y && y == z)
    println(p4(1, 2, 2))
    println(p4(5, 2, 2))

      //5
      def compose(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))
      println(compose(_ - 1, _ * 2)(5))


      //7
//      def power(base: Double, exponent: Int): Double = base match
//        case _ if (exponent == 0) => 1
//        case _ => base * (power(base, exponent - 1))
//      println((power(2, 3), power(5, 2)))

       def power(base: Double, exp: Int): Double =
        @annotation.tailrec
        def powerRec(base: Double, exp: Int, acc: Double): Double = base match
          case _ if exp == 0 => acc
          case _ => powerRec(base, exp - 1, acc * base)
        powerRec(base, exp, 1)

      println((power(2, 3), power(5, 2)))

      //8.1
      def reverseNumber(n: Int): Int =
        @annotation.tailrec
        def reverse(n: Int, acc: Int): Int = n match
          case 0 => acc
          case _ => reverse(n / 10, acc * 10 + (n % 10))
        reverse(n, 0)

      println(reverseNumber(12345))

  }

}
