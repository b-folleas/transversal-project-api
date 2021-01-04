package com.projettransversal.api.Exception;

import com.projettransversal.api.ProjetTransversalExceptionEnum;

public class TruckNotFoundException extends ProjetTransversalException {

    public TruckNotFoundException(ProjetTransversalExceptionEnum projetTransversalExceptionEnum) {
        super(projetTransversalExceptionEnum);
    }

    public TruckNotFoundException(ProjetTransversalExceptionEnum projetTransversalExceptionEnum, Object... sup) {
        super(projetTransversalExceptionEnum, sup);
    }
}
