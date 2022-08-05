package spring.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String getInfoForAllEmployee(){
        return "view_for_all_cars";
    }

    @GetMapping("/secretary_info")
    public String getInfoForSecretary(){
        return "view_for_secretary";
    }

    @GetMapping("/director_info")
    public String getInfoForDerictor(){
        return "view_for_director";
    }
}
