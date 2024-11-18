
# trsm
tomcat redis session manager
 
## Description
this is tomcat redis session manager 
 
## Installation

 
1. add jar to tomcat libs。

2. edit tomcat context.xml

```xml
<Valve className="com.redission.catalina.session.RedisSessionHandlerValve"/>
<Manager className="com.redission.catalina.session.RedisSessionManager" 
				 host="127.0.0.1" 
				 port="6379" 
				database="0" 
				 maxInactiveInterval="3600"/>
