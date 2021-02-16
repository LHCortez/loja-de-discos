package com.luiz.lhcdiscos.controllers;

import com.luiz.lhcdiscos.dto.NovoUsuarioDTO;
import com.luiz.lhcdiscos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value="/create", method = RequestMethod.GET)
    public ModelAndView createUserForm(@ModelAttribute("usuario") NovoUsuarioDTO usuario) {
        return new ModelAndView("createUser");
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ModelAndView doCreateUser(@ModelAttribute("usuario") @Valid NovoUsuarioDTO usuario, BindingResult result) {
        if (result.hasErrors()) {
            return createUserForm(usuario);
        }

        usuarioService.save(usuario);
        return new ModelAndView("redirect:/user/login");
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView loginForm() {
        return new ModelAndView("login");
    }




}
