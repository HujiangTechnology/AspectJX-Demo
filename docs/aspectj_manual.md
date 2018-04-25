#Aspectj Manual

## 匹配表达式

* 注解？ 类的全限定名字
 1. 注解： 可选，类型上持有的注解，如@Deprecated
 2. 类的全限定名: 必填，可以是任何类全限定名

```
注解？ 修饰符? 返回值类型 类型声明?方法名(参数列表) 异常列表？ 
```
 1. 注解：可选，方法上持有的注解，如@Deprecated；
 2. 修饰符：可选，如public、protected；
 3. 返回值类型：必填，可以是任何类型模式；“*”表示所有类型；
 4. 类型声明：可选，可以是任何类型模式；
5. 方法名：必填，可以使用“*”进行模式匹配；
6. 参数列表：“()”表示方法没有任何参数；“(..)”表示匹配接受任意个参数的方法，“(..,java.lang.String)”表示匹配接受java.lang.String类型的参数结束，且其前边可以接受有任意个参数的方法；“(java.lang.String,..)” 表示匹配接受java.lang.String类型的参数开始，且其后边可以接受任意个参数的方法；“(*,java.lang.String)” 表示匹配接受java.lang.String类型的参数结束，且其前边接受有一个任意类型参数的方法；
7. 异常列表：可选，以“throws 异常全限定名列表”声明，异常全限定名列表如有多个以“，”分割，如throws java.lang.IllegalArgumentException, java.lang.ArrayIndexOutOfBoundsException。

## 切入点组合方式

```
&&, ||, !
```

## execution


* `public * *(..)`
	> 任何公共方法的执行
	
* `* cn.javass..IPointcutService.*()`
	> cn.javass包及所有子包下IPointcutService接口中的任何无参方法
	
* `* cn.javass..*.*(..)`
	> cn.javass包及所有子包下任何类的任何方法
	
* `* cn.javass..IPointcutService.*(*)`
	> cn.javass包及所有子包下IPointcutService接口的任何只有一个参数方法
	
* `* (!cn.javass..IPointcutService+).*(..)`
	> 非“cn.javass包及所有子包下IPointcutService接口及子类型”的任何方法
	
* `* cn.javass..IPointcut*.test*(java.util.Date)`
	> cn.javass包及所有子包下IPointcut前缀类型的的以test开头的只有一个参数类型为java.util.Date的方法，注意该匹配是根据方法签名的参数类型进行匹配的，而不是根据执行时传入的参数类型决定的
如定义方法：public void test(Object obj);即使执行时传入java.util.Date，也不会匹配的；

* `* cn.javass..IPointcut*.test*(..)  throws IllegalArgumentException, ArrayIndexOutOfBoundsException`
	> cn.javass包及所有子包下IPointcut前缀类型的的任何方法，且抛出IllegalArgumentException和ArrayIndexOutOfBoundsException异常
	
* `* (cn.javass..IPointcutService+ && java.io.Serializable+).*(..)`
	> 任何实现了cn.javass包及所有子包下IPointcutService接口和java.io.Serializable接口的类型的任何方法
	
* `@java.lang.Deprecated * *(..)`
	> 任何持有@java.lang.Deprecated注解的方法
	
* `@java.lang.Deprecated @cn.javass..Secure  * *(..)`
	> 任何持有@java.lang.Deprecated和@cn.javass..Secure注解的方法
	
* `@(java.lang.Deprecated || cn.javass..Secure) * *(..)`
	> 任何持有@java.lang.Deprecated或@ cn.javass..Secure注解的方法
	
* `(@cn.javass..Secure  *)  *(..)`
	> 任何返回值类型持有@cn.javass..Secure的方法
	
* `*  (@cn.javass..Secure *).*(..)`
	任何定义方法的类型持有@cn.javass..Secure的方法
	
* `* *(@cn.javass..Secure (*) , @cn.javass..Secure (*))`
	> 任何签名带有两个参数的方法，且这个两个参数都被@ Secure标记了，如public void test(@Secure String str1,@Secure String str1);
	
* `* *((@ cn.javass..Secure *))或*(@ cn.javass..Secure *)`
	> 任何带有一个参数的方法，且该参数类型持有@ cn.javass..Secure；如public void test(Model model);且Model类上持有@Secure注解
	
* `* *(@cn.javass..Secure (@cn.javass..Secure *) ,@ cn.javass..Secure (@cn.javass..Secure *))`
	> 任何带有两个参数的方法，且这两个参数都被@ cn.javass..Secure标记了；且这两个参数的类型上都持有@ cn.javass..Secure;
	
* `* *(java.util.Map<cn.javass..Model, cn.javass..Model>, ..)`
	> 任何带有一个java.util.Map参数的方法，且该参数类型是以< cn.javass..Model, cn.javass..Model >为泛型参数；注意只匹配第一个参数为java.util.Map,不包括子类型；如public void test(HashMap<Model, Model> map, String str);将不匹配，必须使用“* *(java.util.HashMap<cn.javass..Model,cn.javass..Model>, ..)”进行匹配；而public void test(Map map, int i);也将不匹配，因为泛型参数不匹配
	
* `* *(java.util.Collection<@cn.javass..Secure *>)`
	> 任何带有一个参数（类型为java.util.Collection）的方法，且该参数类型是有一个泛型参数，该泛型参数类型上持有@cn.javass..Secure注解；
如public void test(Collection<Model> collection);Model类型上持有@cn.javass..Secure

* `* *(java.util.Set<? extends HashMap>)`
	> 任何带有一个参数的方法，且传入的参数类型是有一个泛型参数，该泛型参数类型继承与HashMap；

* `* *(java.util.List<? super HashMap>)`
	> 任何带有一个参数的方法，且传入的参数类型是有一个泛型参数，该泛型参数类型是HashMap的基类型；如public voi test(Map map)；

* `* *(*<@cn.javass..Secure *>)`
	> 任何带有一个参数的方法，且该参数类型是有一个泛型参数，该泛型参数类型上持有@cn.javass..Secure注解；

## within

* `within(cn.javass..*)`

	> cn.javass包及子包下的任何方法执行
* `within(cn.javass..IPointcutService+)`

	> cn.javass包或所有子包下IPointcutService类型及子类型的任何方法
* `within(@cn.javass..Secure *)`

	> 持有cn.javass..Secure注解的任何类型的任何方法必须是在目标对象上声明这个注解，在接口上声明的对它不起作用

## this

> 使用“this(类型全限定名)”匹配当前AOP代理对象类型的执行方法；注意是AOP代理对象的类型匹配，这样就可能包括引入接口方法也可以匹配；注意this中使用的表达式必须是类型全限定名，不支持通配符；

* `this(cn.javass.spring.chapter6.service.IPointcutService)`

	> 当前AOP对象实现了 IPointcutService接口的任何方法
* `this(cn.javass.spring.chapter6.service.IIntroductionService)`
	
	> 当前AOP对象实现了 IIntroductionService接口的任何方法
也可能是引入接口

## target

> 使用“target(类型全限定名)”匹配当前目标对象类型的执行方法；注意是目标对象的类型匹配，这样就不包括引入接口也类型匹配；注意target中使用的表达式必须是类型全限定名，不支持通配符；

* `target(cn.javass.spring.chapter6.service.IPointcutService)`

	> 当前目标对象（非AOP对象）实现了 IPointcutService接口的任何方法
	
* `target(cn.javass.spring.chapter6.service.IIntroductionService)`
	
	> 当前目标对象（非AOP对象） 实现了IIntroductionService 接口的任何方法
不可能是引入接口

## args

> 使用“args(参数类型列表)”匹配当前执行的方法传入的参数为指定类型的执行方法；注意是匹配传入的参数类型，不是匹配方法签名的参数类型；参数类型列表中的参数必须是类型全限定名，通配符不支持；args属于动态切入点，这种切入点开销非常大，非特殊情况最好不要使用；

* `args (java.io.Serializable,..)`

> 任何一个以接受“传入参数类型为 java.io.Serializable” 开头，且其后可跟任意个任意类型的参数的方法执行，args指定的参数类型是在运行时动态匹配的

## @within
* `@within cn.javass.spring.chapter6.Secure)`

	> 任何目标对象对应的类型持有Secure注解的类方法；
必须是在目标对象上声明这个注解，在接口上声明的对它不起作用

## @target
* `@target (cn.javass.spring.chapter6.Secure)`

	> 任何目标对象持有Secure注解的类方法；
必须是在目标对象上声明这个注解，在接口上声明的对它不起作用

## @args

* `@args (cn.javass.spring.chapter6.Secure)`

	> 任何一个只接受一个参数的方法，且方法运行时传入的参数持有注解 cn.javass.spring.chapter6.Secure；动态切入点，类似于arg指示符；
	
## @annotation
* `@annotation(cn.javass.spring.chapter6.Secure )`

	> 当前执行方法上持有注解 cn.javass.spring.chapter6.Secure将被匹配

## reference pointcut 引用其他命名切入点
```
@Pointcut(value="bean(*Service)")
private void pointcut1(){}

@Pointcut(value="@args(cn.javass.sprint.Secure)")
private void pointcut2(){}

@Before(value="pointcut1() && pointcut2()")
public void referencePointcutTest1(JoinPoint jp) {
}
```

## 通知参数
> JointPoint(Around通知是ProceedingJoinPoint)

* JoinPoint 

```
package org.aspectj.lang;  
import org.aspectj.lang.reflect.SourceLocation;  
public interface JoinPoint {  
    String toString();         //连接点所在位置的相关信息  
    String toShortString();     //连接点所在位置的简短相关信息  
    String toLongString();     //连接点所在位置的全部相关信息  
    Object getThis();         //返回AOP代理对象  
    Object getTarget();       //返回目标对象  
    Object[] getArgs();       //返回被通知方法参数列表  
    Signature getSignature();  //返回当前连接点签名  
    SourceLocation getSourceLocation();//返回连接点方法所在类文件中的位置  
    String getKind();        //连接点类型  
    StaticPart getStaticPart(); //返回连接点静态部分  
}  
```
* ProceedingJoinPoint

```
public interface ProceedingJoinPoint extends JoinPoint {  
    public Object proceed() throws Throwable;  
    public Object proceed(Object[] args) throws Throwable;  
}  
```

* JoinPoint.StaticPart

```
public interface StaticPart {  
Signature getSignature();    //返回当前连接点签名  
String getKind();          //连接点类型  
    int getId();               //唯一标识  
String toString();         //连接点所在位置的相关信息  
    String toShortString();     //连接点所在位置的简短相关信息  
    String toLongString();     //连接点所在位置的全部相关信息  
}  
```