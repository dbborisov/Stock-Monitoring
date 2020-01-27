package net.lesno.stock.app.web;

import net.lesno.stock.services.services.TestWebRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    private final TestWebRead testWebRead;

    @Autowired
    public TestController(TestWebRead testWebRead) {
        this.testWebRead = testWebRead;
    }


    @GetMapping("/test/jsoup/{name}")
    private Object testPageWithVariable(@PathVariable String name) {

        ModelAndView modelAndView = new ModelAndView("testPage");
        modelAndView.addObject("view", this.testWebRead.jsoupGoogleSearch(name));
        return modelAndView;
    }
    @GetMapping("/test/jsoup/")
    private Object testPage() {

        ModelAndView modelAndView = new ModelAndView("testPage");

        return modelAndView;
    }


}
