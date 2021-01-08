package org.unicome.sample.spi;

public class SpiServiceImpl2 implements SpiService {

    @Override
    public void print() {
        System.out.println("实现2：" + SpiServiceImpl2.class.getName());
    }
}
