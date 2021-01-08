package org.unicome.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//@RestController
public class Sample {

//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @GetMapping("/sample")
//    public void sendMsg() {
//        rabbitTemplate.convertAndSend("spring-boot-exchange", "foo.bar.baz", "Hello World!");
//    }
//
//    @RabbitListener(queues = "spring-boot")
//    public String receive(String message) {
//        return message;
//    }

    public static void main(String[] args) {
        final Map<String, String> map = new HashMap<String, String>();
        map.put("h", "he");
        System.out.println(map.get("h"));
    }
}
