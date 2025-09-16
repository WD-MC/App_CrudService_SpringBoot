package com.example.registration.stepDefinitions;


import com.example.registration.employee.equipment.dto.EquipmentRequest;
import com.example.registration.employee.equipment.repository.EquipmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class EquipmentSteps {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private EquipmentRequest equipmentRequest;
    private MvcResult response;
    private EquipmentRepository equipmentRepository;

    @Given("un équipement avec les données suivantes:")
    public void un_equipement_avec_les_donnees_suivantes(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
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

}
