# 状态模式 - State Pattern

> 使用场景
> - 行为随状态改变而改变
> - 替代条件、分支语句
> - 工作流

## 实现
- 状态接口 
    - State: 定义行为方法
- 状态实现类
    - StartState: 定义 Start 状态时的行为
    - StopState: 定义 Stop 状态时的行为
- 状态上下文 
    - StateContext: 
