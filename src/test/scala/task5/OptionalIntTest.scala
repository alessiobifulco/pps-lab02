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

  @Test def mapShouldApplyFunction(): Unit =
    val nonEmpty = OptionalInt.Just(5)
    val function: Int => Int = _ + 1
    assertEquals(OptionalInt.Just(6), OptionalInt.map(nonEmpty)(function))

  @Test def mapShouldReturnDefaultWhenEmpty(): Unit =
    val empty = OptionalInt.Empty()
    val function: Int => Int = _ + 1
    assertEquals(OptionalInt.Empty(), OptionalInt.map(empty)(function))

  @Test def filterShouldReturnValueWhenPredicateTrue(): Unit =
    val nonEmpty = OptionalInt.Just(5)
    val function: Int => Boolean = _ > 2
    assertEquals(nonEmpty, OptionalInt.filter(nonEmpty)(function))


  @Test def filterShouldReturnEmptyWhenPredicateFalse(): Unit =
    val nonEmpty = OptionalInt.Just(1)
    val function: Int => Boolean = _ > 2
    assertEquals(OptionalInt.Empty(), OptionalInt.filter(nonEmpty)(function))

  @Test def filterShouldReturnEmptyWhenValueIsEmpty(): Unit =
    val empty = OptionalInt.Empty()
    val function: Int => Boolean = _ > 2
    assertEquals(OptionalInt.Empty(), OptionalInt.filter(empty)(function))
