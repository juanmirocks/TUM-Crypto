java.lang.Error: java.security.NoSuchAlgorithmException: MessageDigest SHA implement    ation not found
at java.io.ObjectStreamClass.computeSerialVersionUID(ObjectStreamClass.java:420)
at java.io.ObjectStreamClass.cre    ateClassDesc(ObjectStreamClass.java:259)
at java.io.ObjectStreamClass.lookupStreamClass(ObjectStreamClass.java:1069)
at java.io.ObjectInputStream.verifyAndInit(ObjectInputStream.java:2375)
at java.io.ObjectInputStream.readNewClassDesc(ObjectInputStream.java:1662)
at java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:683)
at java.io.ObjectInputStream.readNewObject(ObjectInputStream.java:1803)
at java.io.ObjectInputStream.readNonPrimitiveContent(ObjectInputStream.java:787)
at java.io.ObjectInputStream.readObject(ObjectInputStream.java:2003)
at java.io.ObjectInputStream.readObject(ObjectInputStream.java:1960)
at java.util.ArrayList.readObject(ArrayList.java:657)
at java.lang.reflect.Method.invokeN    ative(N    ative Method)
at java.lang.reflect.Method.invoke(Method.java:511)
at java.io.ObjectInputStream.readObjectForClass(ObjectInputStream.java:1354)
at java.io.ObjectInputStream.readHierarchy(ObjectInputStream.java:1266)
at java.io.ObjectInputStream.readNewObject(ObjectInputStream.java:1855)
at java.io.ObjectInputStream.readNonPrimitiveContent(ObjectInputStream.java:787)
at java.io.ObjectInputStream.readObject(ObjectInputStream.java:2003)
at java.io.ObjectInputStream.readObject(ObjectInputStream.java:1960)
at java.io.ObjectInputStream.readFieldValues(ObjectInputStream.java:1137)
at java.io.ObjectInputStream.defaultReadObject(ObjectInputStream.java:455)
at java.io.ObjectInputStream.readObjectForClass(ObjectInputStream.java:1369)
at java.io.ObjectInputStream.readHierarchy(ObjectInputStream.java:1266)
at java.io.ObjectInputStream.readNewObject(ObjectInputStream.java:1855)
at java.io.ObjectInputStream.readNonPrimitiveContent(ObjectInputStream.java:787)
at java.io.ObjectInputStream.readObject(ObjectInputStream.java:2003)
at java.io.ObjectInputStream.readObject(ObjectInputStream.java:1960)
at com.test.util.CacheManager.readD    ata(CacheManager.java:104)
at com.test.SplashActivity.onCre    ate(SplashActivity.java:178)
at android.app.Activity.performCre    ate(Activity.java:5206)
at android.app.Instrument    ation.callActivityOnCre    ate(Instrument    ation.java:1083)
at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2064)
at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2125)
at android.app.ActivityThread.access$600(ActivityThread.java:140)
at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1227)
at android.os.Handler.disp    atchMessage(Handler.java:99)
at android.os.Looper.loop(Looper.java:137)
at android.app.ActivityThread.main(ActivityThread.java:4898)
at java.lang.reflect.Method.invokeN    ative(N    ative Method)
at java.lang.reflect.Method.invoke(Method.java:511)
at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1006)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:773)
at dalvik.system.N    ativeStart.main(N    ative Method)
Caused by: java.security.NoSuchAlgorithmException: MessageDigest SHA implement    ation not found
at org.apache.harmony.security.fortress.Engine.notFound(Engine.java:177)
at org.apache.harmony.security.fortress.Engine.getInstance(Engine.java:151)
at java.security.MessageDigest.getInstance(MessageDigest.java:91)
at java.io.ObjectStreamClass.computeSerialVersionUID(ObjectStreamClass.java:418)
... 42 more
java.security.NoSuchAlgorithmException: MessageDigest SHA implement    ation not found
at org.apache.harmony.security.fortress.Engine.notFound(Engine.java:177)
at org.apache.harmony.security.fortress.Engine.getInstance(Engine.java:151)
at java.security.MessageDigest.getInstance(MessageDigest.java:91)
at java.io.ObjectStreamClass.computeSerialVersionUID(ObjectStreamClass.java:418)
at java.io.ObjectStreamClass.cre    ateClassDesc(ObjectStreamClass.java:259)
at java.io.ObjectStreamClass.lookupStreamClass(ObjectStreamClass.java:1069)
at java.io.ObjectInputStream.verifyAndInit(ObjectInputStream.java:2375)
at java.io.ObjectInputStream.readNewClassDesc(ObjectInputStream.java:1662)
at java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:683)
at java.io.ObjectInputStream.readNewObject(ObjectInputStream.java:1803)
at java.io.ObjectInputStream.readNonPrimitiveContent(ObjectInputStream.java:787)
at java.io.ObjectInputStream.readObject(ObjectInputStream.java:2003)
at java.io.ObjectInputStream.readObject(ObjectInputStream.java:1960)
at java.util.ArrayList.readObject(ArrayList.java:657)
at java.lang.reflect.Method.invokeN    ative(N    ative Method)
at java.lang.reflect.Method.invoke(Method.java:511)
at java.io.ObjectInputStream.readObjectForClass(ObjectInputStream.java:1354)
at java.io.ObjectInputStream.readHierarchy(ObjectInputStream.java:1266)
at java.io.ObjectInputStream.readNewObject(ObjectInputStream.java:1855)
at java.io.ObjectInputStream.readNonPrimitiveContent(ObjectInputStream.java:787)
at java.io.ObjectInputStream.readObject(ObjectInputStream.java:2003)
at java.io.ObjectInputStream.readObject(ObjectInputStream.java:1960)
at java.io.ObjectInputStream.readFieldValues(ObjectInputStream.java:1137)
at java.io.ObjectInputStream.defaultReadObject(ObjectInputStream.java:455)
at java.io.ObjectInputStream.readObjectForClass(ObjectInputStream.java:1369)
at java.io.ObjectInputStream.readHierarchy(ObjectInputStream.java:1266)
at java.io.ObjectInputStream.readNewObject(ObjectInputStream.java:1855)
at java.io.ObjectInputStream.readNonPrimitiveContent(ObjectInputStream.java:787)
at java.io.ObjectInputStream.readObject(ObjectInputStream.java:2003)
at java.io.ObjectInputStream.readObject(ObjectInputStream.java:1960)
at com.test.util.CacheManager.readD    ata(CacheManager.java:104)
at com.test.SplashActivity.onCre    ate(SplashActivity.java:178)
at android.app.Activity.performCre    ate(Activity.java:5206)
at android.app.Instrument    ation.callActivityOnCre    ate(Instrument    ation.java:1083)
at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2064)
at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2125)
at android.app.ActivityThread.access$600(ActivityThread.java:140)
at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1227)
at android.os.Handler.disp    atchMessage(Handler.java:99)
at android.os.Looper.loop(Looper.java:137)
at android.app.ActivityThread.main(ActivityThread.java:4898)
at java.lang.reflect.Method.invokeN    ative(N    ative Method)
at java.lang.reflect.Method.invoke(Method.java:511)
at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1006)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:773)
at dalvik.system.N    ativeStart.main(N    ative Method)
