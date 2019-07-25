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

import shapeless._

import scala.concurrent.{ExecutionContext, Future}
import scala.language.higherKinds

case class Random(maxCollectionSize: Int = 10) {
  def apply[T: RandomGenerator]: T = RandomGenerator[T](this)
}

object Random {
  def apply[T: RandomGenerator]: T = RandomGenerator[T](Random())
}

trait RandomGenerator[T] {
  def rand(settings: Random): T
}

object RandomGenerator extends RandomCollections {
  implicit def randomBoolean = random(_ => scala.util.Random.nextBoolean)

  implicit def randomByte = random(_ => scala.util.Random.nextInt.toByte)

  implicit def randomChar = random(_ => scala.util.Random.nextInt.toChar)

  implicit def randomShort = random(_ => scala.util.Random.nextInt.toShort)

  implicit def randomInt = random(_ => scala.util.Random.nextInt)

  implicit def randomLong = random(_ => scala.util.Random.nextLong)

  implicit def randomBigInt = random(_ => BigInt(scala.util.Random.nextLong))

  implicit def randomFloat = random(_ => scala.util.Random.nextFloat)

  implicit def randomDouble = random(_ => scala.util.Random.nextDouble)

  implicit def randomBigDecimal = random(_ => BigDecimal(scala.util.Random.nextDouble))

  implicit def randomString = random((settings) => scala.util.Random.nextString(scala.util.Random.nextInt(settings.maxCollectionSize + 1)))

  implicit def randomOption[T: RandomGenerator] = random { settings =>
    if (randomBoolean.rand(settings))
      Some(implicitly[RandomGenerator[T]].rand(settings))
    else
      None
  }

  implicit def randomEither[L: RandomGenerator, R: RandomGenerator]: RandomGenerator[Either[L, R]] = random { settings =>
    if (randomBoolean.rand(settings))
      Left(implicitly[RandomGenerator[L]].rand(settings))
    else
      Right(implicitly[RandomGenerator[R]].rand(settings))
  }

  implicit def randomFuture[T: RandomGenerator](implicit ec: ExecutionContext) = random { settings =>
    Future(implicitly[RandomGenerator[T]].rand(settings))
  }

  implicit def randomHNil: RandomGenerator[HNil] = random(_ => HNil)

  implicit def randomHList[H, T <: HList](implicit h: Lazy[RandomGenerator[H]], t: Lazy[RandomGenerator[T]]): RandomGenerator[H :: T] = new RandomGenerator[H :: T] {
    override def rand(settings: Random) = h.value.rand(settings) :: t.value.rand(settings)
  }

  implicit def randomGeneric[T, L <: HList](implicit gen: Generic.Aux[T, L], l: RandomGenerator[L]): RandomGenerator[T] = new RandomGenerator[T] {
    override def rand(settings: Random) = gen.from(l.rand(settings))
  }

  def apply[T: RandomGenerator](randomSettings: Random) = implicitly[RandomGenerator[T]].rand(randomSettings)
}

object PrintableChars {
  implicit def randomChar = random(_ => scala.util.Random.nextPrintableChar)

  implicit def randomString = random(settings => (1 to scala.util.Random.nextInt(settings.maxCollectionSize)).map(_ => scala.util.Random.nextPrintableChar()).mkString)
}
