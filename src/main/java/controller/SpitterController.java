package controller;

import dao.SpitterRepository;
import model.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

/**
 * Created by yh on 17/11/24.
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterRepository spitterRepository;

    public SpitterController() {
    }

    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
//        模型中的key是根据对象类型推断得到的
        model.addAttribute("spitter", new Spitter());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@Valid Spitter spitter, Errors errors) {
        //使用注解启用校验
        if (errors.hasErrors()) {
            return "registerForm";
        }
        spitterRepository.save(spitter);
        //重定向到基本信息页
        return "redirect:/spitter/" + spitter.getUsername();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable String username, Model model) {
        Spitter spitter = spitterRepository.findByUserName(username);
        model.addAttribute(spitter);
        return "profile";

    }

    // multipart样式的数据处理 ，使用Spring的requestPart注解和MultipartFile接口来处理
    @RequestMapping(value = "/registerMultiPart", method = RequestMethod.POST)
    public String processRegistrationMulti(@RequestPart("profilePicture") MultipartFile profilePicture) {
        try {
//            spring提供的写入文件的方法
            profilePicture.transferTo(new File("/data/spittr" + profilePicture.getName()));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return "registerForm";

    }

}
