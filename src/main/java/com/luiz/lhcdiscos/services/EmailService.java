package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviaEmailConfirmacaoDoPedido(String email, BigDecimal valorPago, Pedido pedido) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("<p>Prezado(a) cliente, obrigado por comprar conosco!</p>")
                .append("Este é o e-mail de confirmação do seu pedido n° ").append(pedido.getId())
                .append(", realizado no dia ").append(pedido.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .append(" às ").append(pedido.getData().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).append(" horas.");
        stringBuilder
                .append("<p style='font-weight:bold'>Resumo da compra:</p>")
                .append("<table>")
                .append("<tr>")
                .append("<th style='padding:10px'>Nome</th>")
                .append("<th style='padding:10px'>Tipo</th>" )
                .append("<th style='padding:10px'>Valor Unitário</th>")
                .append("<th style='padding:10px'>Quantidade</th>")
                .append("</tr>");

        pedido.getItens().forEach(itemPedido -> {
            stringBuilder
                    .append("<tr>")
                    .append("<td style='padding:10px'>").append(itemPedido.getNome()).append("</td>")
                    .append("<td style='padding:10px'>").append(itemPedido.getTipo()).append("</td>")
                    .append("<td style='padding:10px'> R$ ").append(itemPedido.getValorUnitario()).append("</td>")
                    .append("<td style='padding:10px'>").append(itemPedido.getQuantidade()).append("</td>")
                    .append("</tr>");
        });

        stringBuilder
                .append("</table>")
                .append("<p style='font-weight:bold'>Valor total pago: R$").append(valorPago).append("</p>");
        stringBuilder
                .append("<p style='padding-top: 20px; margin: 0'>Atenciosamente,</p>")
                .append("<p style='padding: 0; margin: 0'>LHC DISCOS</p>")
                .append("<p style='padding: 0; margin: 0'>lhcdiscos@gmail.com</p>")
                .append("<p style='padding: 0; margin: 0'>www.lhcdiscos.herokuapp.com</p>");
//        TODO: Ao fazer deploy, alterar o domínio

        enviaMensagem(email,
                "Pedido nº " + pedido.getId() + " efetuado com sucesso!",
                stringBuilder.toString());
    }

    public void enviaMensagemContato(String nome, String emailInformado, String mensagem) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<p style='font-weight: bold;'>Nome:</p>")
                .append("<p>" + nome + "</p>")
                .append("<p style='font-weight: bold;'>E-mail:</p>")
                .append("<p>" + emailInformado + "</p>")
                .append("<p style='font-weight: bold;'>Mensagem:</p>")
                .append("<p>" + mensagem + "</p>");

        enviaMensagem("lhcdiscos@gmail.com", "ContatoForm: " + emailInformado, stringBuilder.toString());
    }

    private void enviaMensagem(String destinatario, String assunto, String conteudo) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            message.setFrom("noreply@lhcdiscos.com.br");
            helper.setTo(destinatario);
            helper.setSubject(assunto);
            message.setContent(conteudo, "text/html; charset=utf-8");

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
