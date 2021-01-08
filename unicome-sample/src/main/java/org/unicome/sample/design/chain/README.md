# 责任链模式

## 责任链模式
- 抽象处理者: [Handler.java]
    - 下一个处理者: [next]
    - 抽象处理方法: [handle]
- 具体处理者
    - ProjectManagerHandler
    - DeptManagerHandler
    - GeneralManagerHandlers

## javax.servlet 中的责任链
- Filter
- FilterChain
    - 过滤器链，记录当前所有的过滤器[n]以及当前执行过滤器位置[pos]
    - ApplicationFilterChain
        - filters[ApplicationFilterConfig[]]
        - servlet[DispatchServlet]
    - filterChain.doFilter 实际上调用的是下一个 filter.doFilter
- FilterConfig
    - 在 Filter 初始化的过程中，ServletContext 通过 FilterConfig 传递信息给 Filter，即 Filter 通过 FilterConfig 访问 ServletContext
    
## spring-boot 中 filter 的初始化

ServletContextInitializerBeans.logMappings