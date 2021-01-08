package org.unicome.sample.design.chain.sample;

public class ProjectManagerHandler extends Handler {
    @Override
    public void handle(int day) {
        if (day <= 3) {
            System.out.println("项目经理只处理3天以内的请假！");
        } else {
            if (getNext() != null) {
                System.out.println("大于3天的请假转给其他人处理！");
                getNext().handle(day);
            }
        }
    }
}
