package org.unicome.cms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unicome.cms.singleton.QrCodeSingleton;

@RestController
@RequestMapping(value="/qrcode")
public class QrCodeController {

    @GetMapping(value="")
    public String qrCode(@RequestParam String url,
                         @RequestParam(required=false, defaultValue="300") int width,
                         @RequestParam(required=false, defaultValue="300") int height) throws Exception {
        return QrCodeSingleton.getInstance().encode2Base64(url, width, height);
    }
}
