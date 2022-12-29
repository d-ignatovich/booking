package rest.api;
import rest.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public ModelAndView userList(ModelAndView model) {
        model.getModel().put("allUsers", userService.allUsers());
        model.setViewName("admin");
        return model;
    }

    @PostMapping("/admin")
    public RedirectView deleteUser(@RequestParam(defaultValue = "" ) Long userId,
                                   @RequestParam(defaultValue = "" ) String action,
                                   ModelAndView model) {
        if (action.equals("delete")){
            userService.deleteUser(userId);
        }
        model.clear();
        return new RedirectView("/admin");

    }

    @GetMapping("/admin/get/{userId}")
    public ModelAndView gtUser(@PathVariable("userId") Long userId, ModelAndView model) {
        model.getModel().put("allUsers", userService.usergtList(userId));
        model.setViewName("admin");
        return model;
    }
}
