package com.luiz.lhcdiscos.model.entity;

import com.luiz.lhcdiscos.model.validation.UniquePatch;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"nome", "banda_id"}))
@UniquePatch
public class Patch extends Produto {

    @Override
    public String getTipo() {
        return "Patch";
    }

}
