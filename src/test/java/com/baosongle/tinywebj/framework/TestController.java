package com.baosongle.tinywebj.framework;

@Controller
public class TestController {
    @GetMapping("/index")
    public String index(@RequestParam("name") String name) {
        return "index";
    }
}
