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