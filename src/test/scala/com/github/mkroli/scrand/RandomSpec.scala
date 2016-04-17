/*
 * Copyright 2016 Michael Krolikowski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.mkroli.scrand

import org.scalatest._

import scala.concurrent.Future

class RandomSpec extends WordSpec {
  "Random" when {
    "no configuration given" must {
      "create random Boolean" in {
        assert(Random[Boolean].getClass === classOf[Boolean])
      }

      "create random Byte" in {
        assert(Random[Byte].getClass === classOf[Byte])
      }

      "create random Char" in {
        assert(Random[Char].getClass === classOf[Char])
      }

      "create random Short" in {
        assert(Random[Short].getClass === classOf[Short])
      }

      "create random Int" in {
        assert(Random[Int].getClass === classOf[Int])
      }

      "create random Long" in {
        assert(Random[Long].getClass === classOf[Long])
      }

      "create random BigInt" in {
        assert(Random[BigInt].getClass === classOf[BigInt])
      }

      "create random Float" in {
        assert(Random[Float].getClass === classOf[Float])
      }

      "create random Double" in {
        assert(Random[Double].getClass === classOf[Double])
      }

      "create random BigDecimal" in {
        assert(Random[BigDecimal].getClass === classOf[BigDecimal])
      }

      "create random String" in {
        assert(Random[String].getClass === classOf[String])
      }

      "create random Option" in {
        val s = Stream.fill(1000)(Random[Option[Int]])
        assert(s.find(_.isInstanceOf[Some[Int]]).isDefined)
        assert(s.find(_ == None).isDefined)
      }

      "create random Either" in {
        val s = Stream.fill(1000)(Random[Either[Int, Long]])
        assert(s.find(_.isInstanceOf[Left[Int, Long]]).isDefined)
        assert(s.find(_.isInstanceOf[Right[Int, Long]]).isDefined)
      }

      "create random Future" in {
        import scala.concurrent.ExecutionContext.Implicits._
        assert(Random[Future[Int]].isInstanceOf[Future[Int]])
      }

      "create random Seq/List/Set/..." in {
        assert(Random[List[Int]].isInstanceOf[List[Int]])
        assert(Random[Seq[Int]].isInstanceOf[Seq[Int]])
        assert(Random[Set[Int]].isInstanceOf[Set[Int]])
        assert(Random[Vector[Int]].isInstanceOf[Vector[Int]])
        assert(Random[Array[Int]].isInstanceOf[Array[Int]])
      }

      "create random Stream" in {
        assert(Random[Stream[Int]].isInstanceOf[Stream[Int]])
      }

      "create random Map" in {
        assert(Random[Map[Int, String]].isInstanceOf[Map[Int, String]])
      }

      "create random Tuples" in {
        assert(Random[Tuple1[Int]].isInstanceOf[Tuple1[Int]])
        assert(Random[(Int, Int)].isInstanceOf[(Int, Int)])
        assert(Random[(Int, Int, Int)].isInstanceOf[(Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)])
        assert(Random[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)].isInstanceOf[(Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int)])
      }

      "create random case class" in {
        case class Test1()
        assert(Random[Test1].getClass === classOf[Test1])

        case class Test2(a: Int)
        assert(Random[Test2].getClass === classOf[Test2])

        case class Test3(a: Int, b: Int)
        assert(Random[Test3].getClass === classOf[Test3])

        case class Test4(a: Int, b: Int, c: Int)
        assert(Random[Test4].getClass === classOf[Test4])

        case class Test5(a: Int, b: Int, c: Int, d: Int)
        assert(Random[Test5].getClass === classOf[Test5])

        case class Test6(a: Int, b: Int, c: Int, d: Int, e: Int)
        assert(Random[Test6].getClass === classOf[Test6])

        case class Test7(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int)
        assert(Random[Test7].getClass === classOf[Test7])

        case class Test8(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int)
        assert(Random[Test8].getClass === classOf[Test8])

        case class Test9(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int)
        assert(Random[Test9].getClass === classOf[Test9])

        case class Test10(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int)
        assert(Random[Test10].getClass === classOf[Test10])

        case class Test11(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int)
        assert(Random[Test11].getClass === classOf[Test11])

        case class Test12(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int)
        assert(Random[Test12].getClass === classOf[Test12])

        case class Test13(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int)
        assert(Random[Test13].getClass === classOf[Test13])

        case class Test14(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int)
        assert(Random[Test14].getClass === classOf[Test14])

        case class Test15(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int)
        assert(Random[Test15].getClass === classOf[Test15])

        case class Test16(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int)
        assert(Random[Test16].getClass === classOf[Test16])

        case class Test17(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int, p: Int)
        assert(Random[Test17].getClass === classOf[Test17])

        case class Test18(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int, p: Int, q: Int)
        assert(Random[Test18].getClass === classOf[Test18])

        case class Test19(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int, p: Int, q: Int, r: Int)
        assert(Random[Test19].getClass === classOf[Test19])

        case class Test20(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int, p: Int, q: Int, r: Int, s: Int)
        assert(Random[Test20].getClass === classOf[Test20])

        case class Test21(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int, p: Int, q: Int, r: Int, s: Int, t: Int)
        assert(Random[Test21].getClass === classOf[Test21])

        case class Test22(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int, p: Int, q: Int, r: Int, s: Int, t: Int, u: Int)
        assert(Random[Test22].getClass === classOf[Test22])

        case class Test23(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int, k: Int, l: Int, m: Int, n: Int, o: Int, p: Int, q: Int, r: Int, s: Int, t: Int, u: Int, v: Int)
        assert(Random[Test23].getClass === classOf[Test23])
      }
    }

    "maxCollectionSize configured" must {
      val maxCollectionSize = 5
      val iterations = 1000

      "create random String" in {
        (1 to iterations).foreach { _ =>
          val rand = Random(maxCollectionSize = maxCollectionSize)[String]
          assert(rand.getClass === classOf[String])
          assert(rand.size <= maxCollectionSize)
        }
      }

      "create random Seq/List/Set/..." in {
        (1 to iterations).foreach { _ =>
          val rand = Random(maxCollectionSize = maxCollectionSize)[List[Int]]
          assert(rand.isInstanceOf[List[Int]])
          assert(rand.size <= maxCollectionSize)
        }
      }

      "create infinite Stream (> maxCollectionSize)" in {
        val rand = Random(maxCollectionSize = maxCollectionSize)[Stream[Int]]
        assert(rand.isInstanceOf[Stream[Int]])
        assert(rand.take(maxCollectionSize + 1).size === (maxCollectionSize + 1))
      }

      "create random Map" in {
        (1 to iterations).foreach { _ =>
          val rand = Random(maxCollectionSize = maxCollectionSize)[Map[Int, Int]]
          assert(rand.isInstanceOf[Map[Int, Int]])
          assert(rand.size <= maxCollectionSize)
        }
      }
    }

    "PrintableChars is imported" must {
      import PrintableChars._

      "create only printable Chars" in {
        val l = List.fill(1000)(Random[Char])
        assert(l.forall(33 to 126 contains _))
      }

      "create Strings only containing printable Chars" in {
        val l = List.fill(100)(Random[String]).flatten
        assert(l.forall(33 to 126 contains _))
      }
    }
  }
}
