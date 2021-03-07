package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.Pedido;
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
    private JavaMailSender emailSender;

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
                .append("<p style='padding: 0; margin: 0'>www.xxxxxxxx.com</p>");
//        TODO: Ao fazer deploy, alterar o domínio

        sendMessage(email,
                "Pedido nº " + pedido.getId() + " efetuado com sucesso!",
                stringBuilder.toString());
    }

    private void sendMessage(String to, String subject, String text) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            message.setFrom("noreply@lhcdiscos.com.br");
            helper.setTo(to);
            helper.setSubject(subject);
            message.setContent(text, "text/html; charset=utf-8");

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
