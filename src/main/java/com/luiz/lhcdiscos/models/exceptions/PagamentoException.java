package com.luiz.lhcdiscos.models.exceptions;

public class PagamentoException extends Exception {

    private static final long serialVersionUid = 1L;

    public PagamentoException(String msg) {
        super(msg);
    }

    public PagamentoException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
