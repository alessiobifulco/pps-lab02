package it.unibo.pps.u02

object Lab2 extends App:

  // Tasks - part 2 (functions)
  // 3a)
  def positiveMethod(x: Int): String = x match
    case x if x >= 0 => "positive"
    case _ => "negative"

  val positiveLambda: Int => String =
    case x if x >= 0 => "positive"
    case _ => "negative"

  // 3b)
  val empty: String => Boolean = _ == ""
  def neg(p: String => Boolean): String => Boolean = s => !p(s)
  val notEmpty = neg(empty)

  println(positiveMethod(10) == "positive")
  println(positiveLambda(-5) == "negative")
  println(notEmpty("foo"))
  println(!notEmpty(""))
  println((notEmpty("foo") && !notEmpty("")))

  // 4)
  val p1: Int => Int => Int => Boolean = x => y => z => x <= y && y == z
  val p2: (Int, Int, Int) => Boolean = (x, y, z) => x <= y && y == z
  def p3(x: Int)(y: Int)(z: Int): Boolean = x <= y && y == z
  def p4(x: Int, y: Int, z: Int): Boolean = x <= y && y == z

  println(p1(1)(2)(2) == true)
  println(p1(5)(2)(2) == false)
  println(p2(1, 2, 2) == true)
  println(p3(1)(2)(2) == true)
  println(p4(1, 2, 2) == true)

  // 5)
  def compose(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))

  println(compose(_ - 1, _ * 2)(5) == 9)

  // Tasks - part 3 (recursion)
  // 7)
  def power(b: Double, e: Int): Double =
    @annotation.tailrec
    def _power(b: Double, e: Int, a: Double): Double = e match
      case 0 => a
      case _ => _power(b, e - 1, a * b)
    _power(b, e, 1)

  // 8)
  def reverseNumber(n: Int): Int =
    @annotation.tailrec
    def _reverse(n: Int, a: Int): Int = n match
      case 0 => a
      case _ => _reverse(n / 10, a * 10 + (n % 10))
    _reverse(n, 0)

  println(power(2, 3) == 8.0)
  println(power(5, 2) == 25.0)
  println(reverseNumber(12345) == 54321)

  // Tasks - part 4 (sum types, product types, modules)
  // 8)
  enum Expr:
    case Literal(n: Int)
    case Add(a: Expr, b: Expr)
    case Multiply(a: Expr, b: Expr)

  object Expr:
    def evaluate(e: Expr): Int = e match
      case Literal(n) => n
      case Add(a, b) => evaluate(a) + evaluate(b)
      case Multiply(a, b) => evaluate(a) * evaluate(b)

    def show(e: Expr): String = e match
      case Literal(n) => n.toString
      case Add(a, b) => "(" + show(a) + " + " + show(b) + ")"
      case Multiply(a, b) => "(" + show(a) + " * " + show(b) + ")"

  import Expr.*
  val exprAdd = Add(Literal(1), Literal(2))
  val exprMult = Multiply(Literal(1), Literal(2))
  println(evaluate(exprAdd) == 3)
  println(evaluate(exprMult) == 2)
  println(show(exprAdd) == "(1 + 2)")

  // Tasks - part 5 (more functional combinators)
  // 9)
  enum OptionalInt:
    case Just(v: Int)
    case Empty()

  object OptionalInt:
    def isEmpty(opt: OptionalInt): Boolean = opt match
      case Empty() => true
      case _       => false

    def orElse(opt: OptionalInt, d: Int): Int = opt match
      case Just(v) => v
      case _       => d

    def mapInt(opt: OptionalInt)(f: Int => Int): OptionalInt = opt match
      case Just(v) => Just(f(v))
      case _       => Empty()

    def filter(opt: OptionalInt)(f: Int => Boolean): OptionalInt = opt match
      case Just(v) if f(v) => opt
      case _               => Empty()

  import OptionalInt.*
  val just5 = Just(5)
  val emptyOpt = Empty()

  println(mapInt(just5)(_ + 1) == Just(6))
  println(mapInt(emptyOpt)(_ + 1) == Empty())
  println(filter(just5)(_ > 2) == Just(5))
  println(filter(just5)(_ > 8) == Empty())
  println(filter(emptyOpt)(_ > 2) == Empty())