package com.myplantdiary.enterprise.dao;

import com.myplantdiary.enterprise.dto.Specimen;

public interface ISpecimenDAO {
    Specimen save(Specimen specimen) throws Exception;
}
