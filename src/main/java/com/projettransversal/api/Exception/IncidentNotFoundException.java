package com.projettransversal.api.Exception;

import com.projettransversal.api.ProjetTransversalExceptionEnum;

public class IncidentNotFoundException extends ProjetTransversalException {

    public IncidentNotFoundException(ProjetTransversalExceptionEnum projetTransversalExceptionEnum) {
        super(projetTransversalExceptionEnum);
    }

    public IncidentNotFoundException(ProjetTransversalExceptionEnum projetTransversalExceptionEnum, Object... sup) {
        super(projetTransversalExceptionEnum, sup);
    }
}
