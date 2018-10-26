# Spring Security 5.0.4

DelegatingFilterProxy -> 代理
SpringSecurityFilterChain(FilterChainProxy) -> 安全认证链
AuthenticationFilter ->
AuthenticationManager -> 配置认证用户信息
AuthenticationProvider -> 提供endpoint,如/oauth/authorize
UserDetails ->
AuthenticationToken ->
SecurityContextHolder

## 最简单的Spring Security认证 -- 基于http basic认证
## 最简单的Spring Security认证 -- Form Login

1. 自定义登陆过滤器
AbstractAuthenticationProcessingFilter
2. 自定义登陆provider
AuthenticationProvider


# Spring Security OAuth2
OAuth2 server = Security + AuthorizationEndpoint + TokenEndpoint
resource server = OAuth2AuthenticationPrrocessingFilter:加载Authentication

@EnableAuthorizationServer ->
ClientDetailsServiceConfigurer
AuthorizationServerSecurityConfigurer
AuthorizationServerEndpointConfigurer

---

# Authorization Server Configuration
## 基本使用
1. 注解：
```
@Configuration
@EnableAuthorizationServer
```
2. 三个configurer
* ClientDetailsServiceConfigurer: 配置自定义的clientDetailsService等
    clientDetailsService类主要是用来获取客户端信息， 包括客户端ID、客户端secret、授权类型等数据，
    在使用时可以自定义clientDetailsService类(继承clientDetailsService)，
    然后在ClientDetailsServiceConfigurer中使用withClientDetails()使用自定义的clientDetailsService;
* AuthorizationServerSecurityConfigurer: 在endpoint上加一下限制，比如允许表单验证等
* AuthorizationServerEndpointsConfigurer: 配置endpoint以及tokenService等（包含授权类型service的使用）
    自定义tokenService：
## 自定义开发
1. 自定义Provider
> Provider负责暴露OAuth2的
