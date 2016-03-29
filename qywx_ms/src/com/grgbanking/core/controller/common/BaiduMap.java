package com.grgbanking.core.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaiduMap {
    @RequestMapping(value="/map/baiduMap")
    public String mapInfo(Model model,String longitude,String latitude,String addrName){
        model.addAttribute("longitude", longitude);
        model.addAttribute("latitude", latitude);
        model.addAttribute("addrName", addrName);
        return "/common/baiduPosition";
    }
}
