package com.luiz.lhcdiscos.validation;

import com.luiz.lhcdiscos.models.entities.Patch;
import com.luiz.lhcdiscos.services.PatchService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePatchValidator implements ConstraintValidator<UniquePatch, Patch> {

    @Autowired
    private PatchService patchService;

    @Override
    public void initialize(UniquePatch constraintAnnotation) {
    }

    @Override
    public boolean isValid(Patch patch, ConstraintValidatorContext constraintValidatorContext) {

        if (patch.getId() == null) {
            return patchService.estaDisponivelParaPersistir(patch);
        }

        Patch patchNoBd = patchService.buscaPorId(patch.getId());

        if (patchNoBd.getNome().equalsIgnoreCase(patch.getNome())
            && (patchNoBd.getBanda().equals(patch.getBanda()))) {
            return true;
        } else {
            return patchService.estaDisponivelParaPersistir(patch);
        }

    }

}
