package org.unicome.sample.design.chain.sample;

import lombok.Data;

@Data
public abstract class Handler {

    // 后续责任对象
    protected Handler next;

    // 处理方法
    public abstract void handle(int day);
}
