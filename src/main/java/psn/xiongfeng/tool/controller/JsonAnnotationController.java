package psn.xiongfeng.tool.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psn.xiongfeng.tool.model.Result;
import psn.xiongfeng.tool.model.User;

@RestController
@RequestMapping(value = "/jsonAnnotation")
public class JsonAnnotationController {

    @GetMapping(value = "/jsonIgnore")
    public Result JsonController () {
        User user = new User("xiongfeng", "Ted");
        return Result.success(user);
    }

}
