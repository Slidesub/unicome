package org.unicome.sample.design.chain.sample;

public class GeneralManagerHandler extends Handler {
    @Override
    public void handle(int day) {
        // 有后续处理对象, 转发给后续处理对象
        Handler next = getNext();
        if (next != null) {
            System.out.println("转发给下一个处理器[" + next.getClass().getName() + "]");
            next.handle(day);
        } else {
            // 没有后续对象，直接进行处理
            System.out.println("直接进行处理");
        }
    }
}
