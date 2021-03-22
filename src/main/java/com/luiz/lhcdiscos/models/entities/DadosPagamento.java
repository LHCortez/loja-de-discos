package com.luiz.lhcdiscos.models.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class DadosPagamento implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    private String stripeId;

    private String stripeStatus;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime data;

    private BigDecimal valorPago;

    @OneToOne(mappedBy = "pagamento")
    private Pedido pedido;

    public DadosPagamento(){
    }

    public String getStripeId() {
        return stripeId;
    }

    public void setStripeId(String id) {
        this.stripeId = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public String getStripeStatus() {
        return stripeStatus;
    }

    public void setStripeStatus(String stripeStatus) {
        this.stripeStatus = stripeStatus;
    }
}
