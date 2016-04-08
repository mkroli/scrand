scrand
======

scrand (SCala-RANDom) generates random instances of various types of Scala classes.

Usage
-----

### sbt
```scala
resolvers += "bintray" at "http://dl.bintray.com/mkroli/maven"

libraryDependencies += "com.github.mkroli" %% "scrand" % "0.1"
```

Examples
--------

```scala
scala> import com.github.mkroli.scrand._

scala> Random[Int]
res0: Int = 12345
```
For more examples see [src/test/scala/.../RandomSpec.scala](https://github.com/mkroli/scrand/blob/master/src/test/scala/com/github/mkroli/scrand/RandomSpec.scala).
