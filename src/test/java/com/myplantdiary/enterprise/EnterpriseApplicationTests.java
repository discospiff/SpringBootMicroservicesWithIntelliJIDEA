package com.myplantdiary.enterprise;

import com.myplantdiary.enterprise.dao.ISpecimenDAO;
import com.myplantdiary.enterprise.dto.Specimen;
import com.myplantdiary.enterprise.service.ISpecimenService;
import com.myplantdiary.enterprise.service.SpecimenService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest
class EnterpriseApplicationTests {

    private ISpecimenService specimenService;
    private Specimen specimen = new Specimen();

    @MockBean
    private ISpecimenDAO specimenDAO;


    @Test
    void contextLoads() {
    }

    @Test
    void fetchSpecimenByID_returnsRedbudForID83() throws Exception  {
        givenSpecimenDataAreAvailable();
        whenSpecimen83AddedIsRedbud();
        whenSearchSpecimenWithID83();
        thenReturnOneEasternRedbudSpecimenForID83();
    }

    private void whenSpecimen83AddedIsRedbud() {
        Specimen redbud = new Specimen();
        redbud.setSpecimenId(83);
        redbud.setDescription("Eastern Redbud");
        Mockito.when(specimenDAO.fetch(83)).thenReturn(redbud);
    }

    private void givenSpecimenDataAreAvailable() throws Exception {
        Mockito.when(specimenDAO.save(specimen)).thenReturn(specimen);
        specimenService = new SpecimenService(specimenDAO);
    }

    private void whenSearchSpecimenWithID83() {
        specimen = specimenService.fetchById(83);
    }

    private void thenReturnOneEasternRedbudSpecimenForID83() {
        String description = specimen.getDescription();
        assertEquals("Eastern Redbud", description);
    }

    @Test
    void saveSpecimen_validateReturnSpecimenWithLatitudeAndLongitude() throws Exception  {
        givenSpecimenDataAreAvailable();
        whenUserCreatesANewSpecimenAndSaves();
        thenCrateNewSpecimenRecordAndReturnIt();
    }

    private void whenUserCreatesANewSpecimenAndSaves() {
        specimen.setLatitude("39.74");
        specimen.setLongitude("-84.51");
    }

    private void thenCrateNewSpecimenRecordAndReturnIt() throws Exception  {
        Specimen createdSpecimen = specimenService.save(specimen);
        assertEquals(specimen, createdSpecimen);
        verify(specimenDAO, atLeastOnce()).save(specimen);
    }

    @Test
    void thisTest_ShouldFail() {
        assertEquals(4, 2+2);
    }

}
