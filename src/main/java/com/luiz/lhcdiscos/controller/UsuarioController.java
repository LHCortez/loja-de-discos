package com.luiz.lhcdiscos.controller;

import com.luiz.lhcdiscos.model.dto.NovoUsuarioLocalDto;
import com.luiz.lhcdiscos.service.UsuarioService;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping ("/create")
    public ModelAndView usuarioForm(@ModelAttribute("usuario") NovoUsuarioLocalDto usuario) {
        return new ModelAndView("usuarioForm");
    }

    @PostMapping("/create")
    public ModelAndView createUsuario(@ModelAttribute("usuario") @Valid NovoUsuarioLocalDto usuario,
                                      BindingResult result, RedirectAttributes model) {
        if (result.hasErrors()) {
            return usuarioForm(usuario);
        }

        usuarioService.saveNovoUsuarioLocal(usuario);
        model.addFlashAttribute("novoUsuario", true);
        return new ModelAndView("redirect:/usuario/login");
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
