package it.unibo.pps.u02

object Expr {

  enum Expr:
    case Literal(n: Int)
    case Add(a: Expr, b: Expr)
    case Multiply(a: Expr, b: Expr)

  def evaluate(expr: Expr): Int = expr match
    case Expr.Literal(n) => n
    case Expr.Add(a, b) => evaluate(a) + evaluate(b)
    case Expr.Multiply(a, b) => evaluate(a) * evaluate(b)

  def show(expr: Expr): String = expr match
    case Expr.Literal(n) => n.toString
    case Expr.Add(a, b) => "(" + show(a) + " + " + show(b) + ")"
    case Expr.Multiply(a, b) => "(" + show(a) + " * " + show(b) + ")"

}
