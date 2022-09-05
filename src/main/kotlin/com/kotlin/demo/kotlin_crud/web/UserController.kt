package com.kotlin.demo.kotlin_crud.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.util.*
import javax.validation.Valid

@Controller
@RequestMapping("/user")
class UserController(private val userRepository: UserRepository) {
    @GetMapping("/list")
    fun list(model: Model): String {
        val list: List<User> = userRepository.findAll();
        model.addAttribute("userList", list);
        return "user/list";
    }

    @GetMapping("/create")
    fun create(): ModelAndView {
        var modelAndView: ModelAndView = ModelAndView("user/create")
        var user: User = User()
        modelAndView.addObject("userForm", user)
        return modelAndView;
    }

    @PostMapping("/save")
    fun save(
        @Valid @ModelAttribute("userForm") userForm: User,
        result: BindingResult,
        redirectMsg: RedirectAttributes
    ): ModelAndView {
        if (result.hasErrors()) {
            return ModelAndView("user/create")
        }
        userRepository.save(userForm);
        redirectMsg.addFlashAttribute("message", "Successfully Created!")
        return ModelAndView("redirect:/user/list")
    }

    @GetMapping("/edit/{id}")
    fun edit(@PathVariable("id") id: Long): ModelAndView {
        var modelAndView = ModelAndView("/user/edit")
        var userOptional: Optional<User> = userRepository.findById(id)
        var user: User = User()
        user.id = userOptional.get().id
        user.name = userOptional.get().name
        user.email = userOptional.get().email
        user.phone = userOptional.get().phone
        modelAndView.addObject("userForm", user)
        return modelAndView
    }

    @PostMapping("/update")
    fun update(
        @Valid @ModelAttribute("userForm") user: User,
        result: BindingResult,
        redirectMsg: RedirectAttributes
    ): ModelAndView {
        var modelAndView: ModelAndView = ModelAndView("/user/edit")
        if (result.hasErrors()) {
            return modelAndView
        }
        userRepository.save(user)
        redirectMsg.addFlashAttribute("message", "Successfully Updated!")
        return ModelAndView("redirect:/user/list")
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable("id") id: Long, redirectMsg: RedirectAttributes): String {
        userRepository.deleteById(id)
        redirectMsg.addFlashAttribute("message", "Successfully Deleted!")
        return "redirect:/user/list"
    }
}