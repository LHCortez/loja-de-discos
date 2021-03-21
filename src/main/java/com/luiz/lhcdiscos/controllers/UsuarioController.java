package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.dto.NovoUsuarioLocalDto;
import com.luiz.lhcdiscos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value="/create", method = RequestMethod.GET)
    public ModelAndView createUserForm(@ModelAttribute("usuario") NovoUsuarioLocalDto usuario) {
        return new ModelAndView("createUser");
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ModelAndView doCreateUser(@ModelAttribute("usuario") @Valid NovoUsuarioLocalDto usuario, BindingResult result, RedirectAttributes model) {
        if (result.hasErrors()) {
            return createUserForm(usuario);
        }

        usuarioService.saveNovoUsuarioLocal(usuario);
        model.addFlashAttribute("novoUsuario", true);
        return new ModelAndView("redirect:/user/login");
    }

    @GetMapping("/login")
    public ModelAndView loginForm(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("login");

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            modelAndView.addObject("novoUsuario", inputFlashMap.get("novoUsuario"));
        } else {
            modelAndView.addObject("novoUsuario", false);
        }

        return modelAndView;
    }


}
