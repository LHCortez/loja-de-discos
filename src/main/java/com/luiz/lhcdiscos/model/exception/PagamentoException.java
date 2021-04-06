package com.luiz.lhcdiscos.model.exception;

public class PagamentoException extends Exception {

    private static final long serialVersionUid = 1L;

    public PagamentoException(String msg) {
        super(msg);
    }

    public PagamentoException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
