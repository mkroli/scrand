package com.github.mkroli.scrand

import org.scalatest.WordSpec

class RandomCollectionsSpec extends WordSpec {
  "Random" when {
    "no configuration given" must {
      "create random Option" in {
        val s = LazyList.fill(1000)(Random[Option[Int]])
        assert(s.find(_.isInstanceOf[Some[Int]]).isDefined)
        assert(s.find(_ == None).isDefined)
      }

      "create random Either" in {
        val s = LazyList.fill(1000)(Random[Either[Int, Long]])
        assert(s.find(_.isInstanceOf[Left[Int, Long]]).isDefined)
        assert(s.find(_.isInstanceOf[Right[Int, Long]]).isDefined)
      }

      "create random LazyList" in {
        assert(Random[LazyList[Int]].isInstanceOf[LazyList[Int]])
      }
    }

    "maxCollectionSize configured" must {
      val maxCollectionSize = 5
      val iterations = 1000

      "create infinite LazyList (> maxCollectionSize)" in {
        val rand = Random(maxCollectionSize = maxCollectionSize)[LazyList[Int]]
        assert(rand.isInstanceOf[LazyList[Int]])
        assert(rand.take(maxCollectionSize + 1).size === (maxCollectionSize + 1))
      }
    }
  }
}
