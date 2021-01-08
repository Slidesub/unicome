package org.unicome.sample.spi;

import java.util.ServiceLoader;

public class SpiApp {
    public static void main(String[] args) {
        // 加载所有SpiSample接口的实现
        ServiceLoader<SpiService> spiSamples = ServiceLoader.load(SpiService.class);
        for (SpiService s : spiSamples) {
            s.print();
        }
    }
}
