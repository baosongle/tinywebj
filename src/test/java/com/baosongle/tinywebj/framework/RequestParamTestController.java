package com.baosongle.tinywebj.framework;

@Controller
public class RequestParamTestController {
    @GetMapping("/String")
    public String _string(@RequestParam("string") String string) {
        return "_string";
    }

    @GetMapping("/Long")
    public String _Long(@RequestParam("long") Long value) {
        return "_Long";
    }

    @GetMapping("/long")
    public String _long(@RequestParam("long") long value) {
        return "_long";
    }

    @GetMapping("/Double")
    public String _Double(@RequestParam("double") Double value) {
        return "_Double";
    }

    @GetMapping("/double")
    public String _double(@RequestParam("double") double value) {
        return "_double";
    }

    @GetMapping("/Integer")
    public String _Integer(@RequestParam("integer") Integer value) {
        return "_integer";
    }

    @GetMapping("/integer")
    public String _integer(@RequestParam("integer") int value) {
        return "_integer";
    }

    @GetMapping("/Short")
    public String _Short(@RequestParam("short") Short value) {
        return "_Short";
    }

    @GetMapping("/short")
    public String _short(@RequestParam("short") short value) {
        return "_short";
    }

    @GetMapping("/Float")
    public String _Float(@RequestParam("float") Float value) {
        return "_Float";
    }

    @GetMapping("/float")
    public String _float(@RequestParam("float") float value) {
        return "_float";
    }

    @GetMapping("/intLong")
    public String _intLong(@RequestParam("i") int i, @RequestParam("l") long l) {
        return String.format("%s-%s", i, l);
    }
}
