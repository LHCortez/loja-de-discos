package com.luiz.lhcdiscos.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class DadosPagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String stripeId;

    @Column(nullable = false)
    private String stripeStatus;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime data;

    @Column(nullable = false)
    private BigDecimal valorPago;

    @OneToOne(mappedBy = "pagamento", fetch = FetchType.LAZY)
    @JsonIgnore
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
