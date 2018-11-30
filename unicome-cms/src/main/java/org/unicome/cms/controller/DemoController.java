package org.unicome.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class DemoController {
    @RequestMapping(value="/demo", method= RequestMethod.GET)
    public Map<String, Object> demo() throws Exception{
        throw new Exception("eeeee");
    }
}
