package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");

        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http header/ body 부분의 body부분을 return으로 그대로 보냄
    public String helloString(@RequestParam("name") String name) {

        return "hello " + name; // "hello string!!!"
    }

    @GetMapping("hello-api")
    @ResponseBody // api 객체 자체를 전달하기위해 spring은 기본적으로 json형식으로 보냄
    public Hello helloAPI(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        
        return hello;
    }

    static class Hello{
        private String name;

        // setter getter
        // alt + insert
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
