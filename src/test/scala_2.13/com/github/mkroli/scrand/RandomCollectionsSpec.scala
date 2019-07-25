/*
 * Copyright 2019 Michael Krolikowski
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
