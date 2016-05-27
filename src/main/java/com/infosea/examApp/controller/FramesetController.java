package com.infosea.examApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by infosea on 2016/5/24.
 */
@Controller
public class FramesetController {

    @RequestMapping("/gl/gl_left")
    public String nav() {
        return "gl/gl_left";
    }

    @RequestMapping("/gl/gl_top")
    public String top() {
        return "gl/gl_top";
    }

    @RequestMapping("/gl/blank")
    public String blank() {
        return "gl/blank";
    }

    @RequestMapping("/gl/stgl")
    public String stgl() {
        return "gl/stgl";
    }

    @RequestMapping("/gl/ksgl")
    public String ksgl() {
        return "gl/ksgl";
    }

    @RequestMapping("/gl/kssz")
    public String kssz() {
        return "gl/kssz";
    }

    @RequestMapping("/gl/cjgl")
    public String cjgl() {
        return "gl/cjgl";
    }
}
