package task5

import org.junit.*
import org.junit.Assert.*
import it.unibo.pps.u02.Expr.*

class ExprTest {

  @Test def testLiteralEvaluate(): Unit =
    val expr = Expr.Literal(5)
    assertEquals(5, expr.evaluate(expr))

  @Test def testAddEvaluate() : Unit =
    val a = Expr.Literal(1)
    val b = Expr.Literal(2)
    val expr = Expr.Add(a,b)
    assertEquals(3, expr.evaluate(expr))

  @Test def testMultiplyEvaluate(): Unit =
    val a = Expr.Literal(1)
    val b = Expr.Literal(2)
    val expr = Expr.Multiply(a, b)
    assertEquals(2, expr.evaluate(expr))

  @Test def testLiteralShow(): Unit =
    val expr = Expr.Literal(5)
    assertEquals("5", expr.show(expr))

  @Test def testAddShow(): Unit =
    val a = Expr.Literal(1)
    val b = Expr.Literal(2)
    val expr = Expr.Add(a, b)
    assertEquals("(1 + 2)", expr.show(expr))

  @Test def testMultiplyShow(): Unit =
    val a = Expr.Literal(1)
    val b = Expr.Literal(2)
    val expr = Expr.Multiply(a, b)
    assertEquals("(1 * 2)", expr.show(expr))
}
