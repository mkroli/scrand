scrand
======

[![Build Status](https://travis-ci.org/mkroli/scrand.svg?branch=master)](https://travis-ci.org/mkroli/scrand)
[![Coverage Status](https://coveralls.io/repos/github/mkroli/scrand/badge.svg?branch=master)](https://coveralls.io/github/mkroli/scrand?branch=master)

scrand (SCala-RANDom) generates random instances of various types of Scala classes.

Usage
-----

### sbt
```scala
resolvers += "bintray" at "http://dl.bintray.com/mkroli/maven"

libraryDependencies += "com.github.mkroli" %% "scrand" % "0.3.0"
```

Examples
--------

```scala
scala> import com.github.mkroli.scrand._

scala> Random[Int]
res0: Int = 12345

scala> Random[Either[Int, Boolean]]
res1: Either[Int,Boolean] = Right(false)

scala> Random[(Int, Option[(Int, Int)])]
res2: (Int, Option[(Int, Int)]) = (12345,Some((12345,12345)))

scala> case class Inner(i: Int)
scala> case class Outer(b: Boolean, i: Inner)
scala> Random[Outer]
res3: Outer = Outer(true,Inner(12345))
```
For more examples see [src/test/scala/.../RandomSpec.scala](https://github.com/mkroli/scrand/blob/master/src/test/scala/com/github/mkroli/scrand/RandomSpec.scala).
