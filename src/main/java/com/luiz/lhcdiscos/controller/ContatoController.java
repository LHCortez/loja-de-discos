package com.luiz.lhcdiscos.controller;

import com.luiz.lhcdiscos.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("contato")
public class ContatoController {

    @Autowired
    private EmailService emailService;

    @GetMapping()
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("contato");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            modelAndView.addObject("emailEnviado", inputFlashMap.get("emailEnviado"));
        } else {
            modelAndView.addObject("emailEnviado", false);
        }
        return modelAndView;
    }

    @PostMapping("/envia")
    public ModelAndView envia(@RequestParam String nome,
                              @RequestParam String email,
                              @RequestParam String mensagem,
                              RedirectAttributes model) {
        emailService.enviaMensagemContato(nome, email, mensagem);
        model.addFlashAttribute("emailEnviado", true);
        return new ModelAndView("redirect:/contato");
    }



}
