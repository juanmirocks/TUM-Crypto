java.lang.ExceptionInInitializerError
    at xsbt.boot.Update.settings$lzycompute(Update.scala:76)
    at xsbt.boot.Update.settings(Update.scala:71)
    at xsbt.boot.Update.ivyLockFile$lzycompute(Update.scala:93)
    at xsbt.boot.Update.apply(Update.scala:100)
    at xsbt.boot.Launch.update(Launch.scala:350)
    at xsbt.boot.Launch.xsbt$boot$Launch$$retrieve$1(Launch.scala:208)
    at xsbt.boot.Launch$$anonfun$3.apply(Launch.scala:216)
    at scala.Option.getOrElse(Option.scala:120)
    at xsbt.boot.Launch.xsbt$boot$Launch$$getAppProvider0(Launch.scala:216)
    at xsbt.boot.Launch$$anon$2.call(Launch.scala:196)
    at xsbt.boot.Locks$GlobalLock.withChannel$1(Locks.scala:93)
    at xsbt.boot.Locks$GlobalLock.xsbt$boot$Locks$GlobalLock$$withChannelRetries$1(Locks.scala:78)
    at xsbt.boot.Locks$GlobalLock$$anonfun$withFileLock$1.apply(Locks.scala:97)
    at xsbt.boot.Using$.withResource(Using.scala:10)
    at xsbt.boot.Using$.apply(Using.scala:9)
    at xsbt.boot.Locks$GlobalLock.ignoringDeadlockAvoided(Locks.scala:58)
    at xsbt.boot.Locks$GlobalLock.withLock(Locks.scala:48)
    at xsbt.boot.Locks$.apply0(Locks.scala:31)
    at xsbt.boot.Locks$.apply(Locks.scala:28)
    at xsbt.boot.Launch.locked(Launch.scala:238)
    at xsbt.boot.Launch.app(Launch.scala:147)
    at xsbt.boot.Launch.app(Launch.scala:145)
    at xsbt.boot.Launch$.run(Launch.scala:102)
    at xsbt.boot.Launch$$anonfun$apply$1.apply(Launch.scala:35)
    at xsbt.boot.Launch$.launch(Launch.scala:117)
    at xsbt.boot.Launch$.apply(Launch.scala:18)
    at xsbt.boot.Boot$.runImpl(Boot.scala:41)
    at xsbt.boot.Boot$.main(Boot.scala:17)
    at xsbt.boot.Boot.main(Boot.scala)
Caused by: java.lang.RuntimeException: The SHA1 algorithm is not available in your classpath
    at org.apache.ivy.core.cache.DefaultRepositoryCacheManager.<clinit>(DefaultRepositoryCacheManager.java:86)
    ... 29 more
Caused by: java.security.NoSuchAlgorithmException: SHA1 MessageDigest not available
    at sun.security.jca.GetInstance.getInstance(GetInstance.java:159)
    at java.security.Security.getImpl(Security.java:695)
    at java.security.MessageDigest.getInstance(MessageDigest.java:167)
    at org.apache.ivy.core.cache.DefaultRepositoryCacheManager.<clinit>(DefaultRepositoryCacheManager.java:84)
    ... 29 more
Error during sbt execution: java.lang.ExceptionInInitializerError
