# 阅读[《SpringMVC 学习指南》](http://152.136.139.89/book/SpringMVC.pdf)写的一些练习例子

## 目录

### 第一章 Spring 框架

- 什么是Spring？
> 是一个开源的企业级开发框架

- 什么是SpringMvc？
> Spring的一个子框架

- 什么是控制反转（IoC）？
> 将创建类实例的控制权移交给Spring容器，叫做控制反转

- (1.2)如何使用Spring的控制反转？
> Spring提供两种方式配置Spring控制反转：XML文件和注解，还需要一个ApplicationContext对象作为控制反转容器，ApplicationContext接口有两个实现 ClassPathXmlApplicationContext和FileSystemXmlApplicationContext，本书主要使用的是ClassPathXmlApplicationContext

- 使用XML配置Bean 用来实例化类
> 

- 使用 Spring 提供的 ClassPathXmlApplicationContext 类实现控制反转

```xml
<beans xmlns="...">
	<bean name="product" class="com.app.beans.Product" />
</beans>
```

``` java
ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
Student student = (Student) context.getBean("student3", Student.class);
```

- 实例化XML时的钩子函数，Factory和 Destroy
> factory-method: 指定一个工厂类，生成类的时候做一定的加工，有静态，动态的区别
> destroy-method: 实例被销毁之前调用这个方法

- 实例化时传递参数

```xml
<beans xmlns="...">
	<bean name="product" class="com.app.beans.Product">
		<constructor-arg name="name"  value="Bob"/>
		<constructor-arg name="age"  value="18"/>
		...
	</bean>
</beans>
```

- XML配置类的依赖关系
> Spring提供两种配置依赖关系的元素
> 使用`<property ref="目标类" />`指定setter来设置值，前提是接受依赖的类必须有一个setter方法
> 使用`<constructor-arg ref="目标类" />`调用构造方法设置值，在构造方法中需要接受并赋值给指定字段

[例子](https://github.com/Dyinfalse/JavaLean/tree/master/springIocDemo)

---
### 第二章 至 第四章

- 主要介绍模式2，以及MVC概念
- 举了一个简单的产品表单例子
- DispatcherServlet 分发请求动作
- 一个SpringMVC应用配置方法
- 主要介绍了三种实现SpringMVC的方式以及配置方法
 > 1#继承Controller类，使用doGet，doPost方法响应接口 
 > 特点：一个类的doGet或doPost方法响应一个请求（代码不包含该例子）
 
 > 2#实现Controller接口，以及handleRequest方法响应接口
 > 特点：一个类的handRequest响应一个请求，在SpringMVC配置XML文件中用bean指定请求地址与类的对应关系
 
 > 3#使用@Controller注解和@RequestMapping注解实现
 > 特点：一个类的每个带有@RequestMapping注解的方法响应一个请求，配置包路径，自动扫描注解
 
- @Autowired 依赖注入（member例子）
- 配合@Autowired 的 @Service 注解
- 主要介绍了如何重定向，以及为什么要重定向
- 重定向如何传递参数
 1. 使用`RedirectAttributes.addFlashAttribute`增加一个Flash参数
 2. 在被定向的方法内，使用`RequestContextUtils.getInputFlashMap`从`HttpServletRequest`上获取增加的参数
- 请求时携带参数路径参数和请求体参数
 1. 路径参数 -> 首先在方法上配置`@RequestMapping(value = {"/getParams/{pathParam}"}` 然后在方法参数上使用`@PathVariable String pathParam`注入
 2. 请求体参数 -> 直接在方法参数上使用`@RequestParam(value = "httpParam", defaultValue = "default HttpParam", required = false) String httpParam` 进行配置，并注入到方法内

[例子](https://github.com/Dyinfalse/JavaLean/tree/master/comservletweb)

---
### 第五章

- jsp提交表单，如果要使用jsp提供的表单组件，需要在jsp文件开头加上`<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>`，组件属性详细见5.2(p69-p78)
 1. 主要相关的属性:
 2. `commandName` form标签的属性，用来指明该表单映射的对象(之前在Controller里使用`ModelAndView.addObject()`方法传入jsp的对象)
 3. `path` 单个表单标签的属性，指明当前标签对应的是commandName对象上的哪一个字段