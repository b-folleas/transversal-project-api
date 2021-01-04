package com.projettransversal.api.Exception;

import com.projettransversal.api.ProjetTransversalExceptionEnum;

import java.util.Locale;

public abstract class ProjetTransversalException extends Exception {

    public ProjetTransversalException(ProjetTransversalExceptionEnum projetTransversalExceptionEnum) {
        super(projetTransversalExceptionEnum.getErrorDescripion());
    }

    public ProjetTransversalException(ProjetTransversalExceptionEnum projetTransversalExceptionEnum, Object... sup) {
        super(String.format(Locale.FRANCE, projetTransversalExceptionEnum.getErrorDescripion(), sup));
    }
}
