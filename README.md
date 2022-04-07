## 简介
通过`ASM`实现的一套AOP库，支持前置、后置、环绕、正常退出、异常退出。相比于`aspect`新增了对lambda的支持。该库语法匹配以类型描述为基础上新增了额外的`*`和`**`，如果你熟悉这些的话那么就可以直接上手了。

## 集成
项目根目录`build.gradle`
```
buildscript {
	repositories {
    	...
        maven { url 'https://jitpack.io' }
    }
    dependencies {
    	classpath 'com.github.lyqiai:easyaopPlugin:TAG'
    }
}

allprojects {
	repositories {
    	...
        maven { url 'https://jitpack.io' }
    }
}
```
## 使用文档
### @Aop
用于标记类，只有标记此注解后类内声明的各种切入点注解才会生效

### @Before(clzName: String, methodDesc: String)
此注解在匹配成功的方法执行前将执行此方法体

- clzName
所需匹配类的全限定名，或者使用*匹配所有类。如:`java/lang/Object`

- methodDesc
所需匹配方法名及方法签名，格式如`方法名(方法参数类型描述)`。如：`aop(Ljava/lang/String;I)`
```
class AopTest {
	public void aop(String name, int age) {}
}
```
方法匹配支持使用`*`和`**`用于匹配任意类型和可变任意类型,所以上面可以使用`aop(**)`,`aop(*I)`或者`aop(Ljava/lang/String;*)`

### @After(clzName: String, methodDesc: String)
此注解在匹配成功的方法执行后执行此方法体，参数于`@Before`一致

### @Around(clzName: String, methodDesc: String)
此注解在匹配成功的方法执行前和执行后可以分别炽入代码，标记此方法的注解方法参数需要接收一个类型为`com/river/easyaop/Method`的参数，该类型通过`invoke`方法表示原方法的调用。如:
```
@Aop
class AopTest {
    @Around(clzName = "*", methodDesc = "hello()")
    fun hello(method: Method) {
        val now = System.currentTimeMillis()
        method.invoke()
        println(System.currentTimeMillis() - now)
    }
}
```

### @AfterReturning(clzName: String, methodDesc: String)
此注解在匹配成功的方法正常退出前执行此方法体

### AfterThrowing
此注解在匹配成功的方法异常前执行此方法体



