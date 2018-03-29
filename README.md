ObjectConverter
====
某些业务场景下，比如：
- 来自不同系统的数据源需要转换成统一的对象来进行业务处理；
- 原先一些业务代码基于老的对象去实现，但是某些数据通讯方式修改了，返回的对象并不一定一样或者采用了不同的数据传输方式。

以上两种情况下，可能需要对代码做一些修改才能兼容，为了减少这种情况下的代码修改，所以开发了ObjectConvert，目的是为了无缝转换和兼容原先已经存在的对象。
原本打算是通过Java的反射实现，但是考虑到性能问题，采用注解生成代码的方式去实现。

用法：
```java
  User user = new User("super2b", "super2b", 1);

  Object obj = ObjectConvert2.tryToConvert(UserWrapper.class.getName(), user);
  if (obj instanceof UserWrapper) {
    ... // Do business things.
  }
```

关于如何调试AbstractProcessor
===
1. 在你代码中对init()方法或process()方法设置代码断点！
2. 设置gradle daemon端口和JVM参数。把下面两行加入到你的gradle.properties文件。
 ```
org.gradle.daemon=true
org.gradle.jvmargs=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005
```
在命令行中运行gradle daemon来启动守护线程。
```
gradle --daemon
```

3. 在Android Studio建立Remote Debugger并运行它。
Remote Debugger 配置
我们在这里使用默认设置，IP:localhost，端口:5005。一旦你完成并运行它，那它就会连接到daemon线程中了。

最后我们用gradle命令来运行构建。
```
gradle clean assembleDebug
```
既然我们已经启动了守护线程，Remote Debugger将触发断点并挂起构建运行。完成！！！