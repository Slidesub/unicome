# Spring Security

> form login用于页面登录，http basic用于Web Service API


## 认证流程

request
-> UsernamePasswordAuthenticationFilter
-> AuthenticationManager
-> AuthenticationProvider
-> UserDetailsService
-> UserDetails
-> Authentication
-> SecurityContext
-> SecurityContextHolder
-> SecurityContextPersisenceFilter

## Spring Security 基本原理
request
-> SecurityContextPersisenceFilter
-> UsernamePasswordAuthenticationFilter
-> BasicAuthenticationFilter
-> ...
-> ExceptionTranslationFilter
-> FilterSecurityInterceptor
-> rest api

# Spring secur

Java boolean类型  ----------------------------------  Mongodb Boolean类型 
Java char类型        ----------------------------------  Mongodb String类型 
Java String类型      ----------------------------------  Mongodb String类型 
Java byte类型         ----------------------------------  Mongodb 32-bit integer类型 
Java short类型        ----------------------------------  Mongodb 32-bit integer类型 
Java int类型            ----------------------------------  Mongodb 32-bit integer类型 
Java long类型         ----------------------------------  Mongodb 64-bit integer类型 
Java float类型         ----------------------------------  Mongodb Double类型 
Java double类型     ----------------------------------  Mongodb Double类型 
Java util.Date类型   ----------------------------------  Mongodb Date类型 
Java Array类型        ----------------------------------  Mongodb Array类型 
