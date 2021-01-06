package com.projettransversal.api.Exception;

import com.projettransversal.api.ProjetTransversalExceptionEnum;

public class MapItemNotFoundException extends ProjetTransversalException {

    public MapItemNotFoundException(ProjetTransversalExceptionEnum projetTransversalExceptionEnum) {
        super(projetTransversalExceptionEnum);
    }

    public MapItemNotFoundException(ProjetTransversalExceptionEnum projetTransversalExceptionEnum, Object... sup) {
        super(projetTransversalExceptionEnum, sup);
    }
}
