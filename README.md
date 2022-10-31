这是学院数据库实验的要求，设计一个数据库学生管理系统，不需要输入SQL语句来实现增删改查，这里我用java做了一个，简易命令行系统，博客地址：[https://fx-zpy.github.io/posts/fc319166.html]https://fx-zpy.github.io/posts/fc319166.html

# 使用前修改部分

```java
static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //在3306/后面是你要连接的数据库的名称，我这里名称中带有学号，隐去了
    static final String DB_URL = "jdbc:mysql://localhost:3306/S_T_XXXXXXXXXX?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    static final String USER = "root";//mysql用户（注册的时候的用户，一般都为root）
    static final String PASS = "***********";//mysql密码
```

以上是MySQL版本在8.0以上使用的，如果是8.0以下，则需要设置为

```java
static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //在3306/后面是你要连接的数据库的名称，我这里名称中带有学号，隐去了
    static final String DB_URL = "jdbc:mysql://localhost:3306/S_T_XXXXXXXXXX
```

