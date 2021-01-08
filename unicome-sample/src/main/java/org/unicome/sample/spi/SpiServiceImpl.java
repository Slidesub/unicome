package org.unicome.sample.spi;

public class SpiServiceImpl implements SpiService {
    @Override
    public void print() {
        System.out.println("实现1：" + SpiServiceImpl.class.getName());
    }
}
