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
