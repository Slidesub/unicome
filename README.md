# Spring Security 5.0.4

DelegatingFilterProxy ->
SpringSecurityFilterChain(FilterChainProxy) ->
AuthenticationFilter ->
AuthenticationManager -> 配置认证用户信息
AuthenticationProvider ->
UserDetails ->
AuthenticationToken ->
SecurityContextHolder

## 最简单的Spring Security认证 -- 基于http basic认证
## 最简单的Spring Security认证 -- Form Login

1. 自定义登陆过滤器
AbstractAuthenticationProcessingFilter
2. 自定义登陆provider
AuthenticationProvider