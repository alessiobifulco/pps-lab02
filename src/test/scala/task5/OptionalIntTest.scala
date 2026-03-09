package task5

import org.junit.*
import org.junit.Assert.*
import Optionals.*

class OptionalIntTest:
  @Test def emptyOptionalShouldBeEmpty(): Unit =
    val empty = OptionalInt.Empty()
    assertTrue(OptionalInt.isEmpty(empty))

  @Test def nonEmptyOptionalShouldNotBeEmpty(): Unit =
    val nonEmpty = OptionalInt.Just(0)
    assertFalse(OptionalInt.isEmpty(nonEmpty))

  @Test def orElseShouldReturnDefaultWhenEmpty(): Unit =
    val nonEmpty = OptionalInt.Just(0)
    assertEquals(0, OptionalInt.orElse(nonEmpty, 1))

  @Test def orElseShouldReturnValueWhenNonEmpty(): Unit =
    val empty = OptionalInt.Empty()
    assertEquals(1, OptionalInt.orElse(empty, 1))

  /** Task 5: do test for map **/

  @Test def mapShouldIncrementValue(): Unit =
    val nonEmpty = OptionalInt.Just(5)
    val f: (Int => Int) = _ + 1
    assertEquals(OptionalInt.Just(6), OptionalInt.map(nonEmpty)(f))

  @Test def mapShouldReturnDefaultWhenEmpty(): Unit =
    val empty = OptionalInt.Empty()
    val f: (Int => Int) = _ + 1
    assertEquals(OptionalInt.Empty(), OptionalInt.map(empty)(f))

  @Test def filterShouldReturnValueWhenPredicateTrue(): Unit =
    val nonEmpty = OptionalInt.Just(5)
    val f: (Int => Boolean) = _ > 2
    assertEquals(nonEmpty, OptionalInt.filter(nonEmpty)(f))


  @Test def filterShouldReturnEmptyWhenPredicateFalse(): Unit =
    val nonEmpty = OptionalInt.Just(1)
    val f: (Int => Boolean) = _ > 2
    assertEquals(OptionalInt.Empty(), OptionalInt.filter(nonEmpty)(f))

  @Test def filterShouldReturnEmptyWhenValueIsEmpty(): Unit =
    val empty = OptionalInt.Empty()
    val f: (Int => Boolean) = _ > 2
    assertEquals(OptionalInt.Empty(), OptionalInt.filter(empty)(f))
