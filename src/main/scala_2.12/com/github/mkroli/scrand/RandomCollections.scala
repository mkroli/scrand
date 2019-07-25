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

import scala.collection.generic.CanBuildFrom
import scala.language.higherKinds

trait RandomCollections {
  implicit def randomCanBuildFrom1[T: RandomGenerator, M[T]](implicit cbf: CanBuildFrom[Nothing, T, M[T]]): RandomGenerator[M[T]] = new RandomGenerator[M[T]] {
    override def rand(settings: Random) = (1 to scala.util.Random.nextInt(settings.maxCollectionSize + 1)).foldLeft(cbf()) {
      case (cbf, _) => cbf += implicitly[RandomGenerator[T]].rand(settings)
    }.result()
  }

  implicit def randomCanBuildFrom2[K: RandomGenerator, V: RandomGenerator, M[K, V]](implicit cbf: CanBuildFrom[Nothing, (K, V), M[K, V]]): RandomGenerator[M[K, V]] = new RandomGenerator[M[K, V]] {
    override def rand(settings: Random) = (1 to scala.util.Random.nextInt(settings.maxCollectionSize + 1)).foldLeft(cbf()) {
      case (cbf, _) => cbf += (implicitly[RandomGenerator[K]].rand(settings) -> implicitly[RandomGenerator[V]].rand(settings))
    }.result()
  }

  implicit def randomStream[T: RandomGenerator] = random { settings =>
    def randomStream: Stream[T] = implicitly[RandomGenerator[T]].rand(settings) #:: randomStream

    randomStream
  }
}
