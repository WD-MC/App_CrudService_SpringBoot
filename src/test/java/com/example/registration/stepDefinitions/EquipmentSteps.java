package com.example.registration.stepDefinitions;

import com.example.registration.employee.equipment.dto.EquipmentRequest;
import com.example.registration.employee.equipment.repository.EquipmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class EquipmentSteps {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private EquipmentRequest equipmentRequest;
    private MvcResult response;
    private EquipmentRepository equipmentRepository;


    @Autowired
    public EquipmentSteps(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Before
    public void cleanDatabase() {
        equipmentRepository.deleteAll();
    }

    /**
     * Création d'un équipement
     */
    @Given("un équipement avec les données suivantes:")
    public void un_equipement_avec_les_donnees_suivantes(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().getFirst();
        this.equipmentRequest = new EquipmentRequest(
                data.get("name"),
                data.get("brand"),
                Integer.parseInt(data.get("quantity")),
                data.get("employee"),
                data.get("status")
        );
    }

    @When("je crée l'équipement via l'API")
    public void je_cree_lequipement_via_lapi() throws Exception {
        String json = objectMapper.writeValueAsString(equipmentRequest);
        this.response = mockMvc.perform(post("/equipments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();
    }

    @Then("la réponse doit avoir le code {int}")
    public void la_reponse_doit_avoir_le_code(int expectedStatus) {
        int actualStatus = response.getResponse().getStatus();
        Assertions.assertEquals(expectedStatus, actualStatus);
    }

    @And("le corps de la réponse doit contenir {string}")
    public void le_corps_de_la_reponse_doit_contenir(String expectedValue) throws Exception {
        String content = response.getResponse().getContentAsString();
        Assertions.assertTrue(content.contains(expectedValue), "La réponse ne contient pas: " + expectedValue);
    }

    /**
     * Récupération de tous les équipements
     */
    @Given("Les équipements suivants existent :")
    public void les_equipement_suivantes_exitent(io.cucumber.datatable.DataTable dataTable) throws Exception {
        List<Map<String, String>> dataList = dataTable.asMaps();

        for (Map<String, String> data : dataList) {
            EquipmentRequest request = new EquipmentRequest(
                    data.get("name"),
                    data.get("brand"),
                    Integer.parseInt(data.get("quantity")),
                    data.get("employee"),
                    data.get("status")
            );

            String json = objectMapper.writeValueAsString(request);
            mockMvc.perform(post("/equipments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andReturn();
        }
    }

    @When("J'envoie une requête GET via l’API")
    public void envoi_une_requete_get_via_lapi() throws Exception {
        this.response = mockMvc.perform(get("/equipments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Then("La reponse doit avoir le code {int}")
    public void la_reponse_get_doit_avoir_le_code(int expectedStatus) {
        int actualStatus = response.getResponse().getStatus();
        Assertions.assertEquals(expectedStatus, actualStatus);
    }

    @And("Le nombre d'équipements retournés doit etre de {int}")
    public void le_nombre_équipements_retournés_doit_etre(Integer expectedCount) throws Exception {
        String content = response.getResponse().getContentAsString();
        List<?> equipements = objectMapper.readValue(content, List.class);
        Assertions.assertEquals(expectedCount.intValue(), equipements.size());
    }

}
