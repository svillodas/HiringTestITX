package com.inditex.hiring.test.infrastructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.hiring.application.OfferService;
import com.inditex.hiring.application.dto.OfferDto;
import com.inditex.hiring.application.utils.DateParser;
import com.inditex.hiring.infrastructure.rest.OfferController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(OfferController.class)
public class OfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OfferService offerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateOffer() throws Exception {
        // Arrange
        OfferDto offerDto = new OfferDto(1L,
                                1234,
                                "2024-07-05 10:00",
                                "2024-08-05 10:00",
                                5678L,
                                "ABC123",
                                1,
                                new BigDecimal("99.99"),
                                "USD"
                                );


        when(offerService.saveOffer(any(OfferDto.class))).thenReturn(offerDto);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/offer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(offerDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.offerId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("99.99"));

        verify(offerService, times(1)).saveOffer(any(OfferDto.class));
    }

    @Test
    public void testDeleteOfferById() throws Exception {
        // Arrange
        Long offerId = 1L;
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/offer/{id}", offerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(offerId)));

        verify(offerService, times(1)).deleteOffer(offerId);
    }

    @Test
    public void testGetOfferById() throws Exception {
        // Arrange
        Long offerId = 1L;
        OfferDto offerDto = new OfferDto();
        offerDto.setOfferId(offerId);
        offerDto.setStartDate(DateParser.parseLocalDateTimetoString(LocalDateTime.now()));
        offerDto.setEndDate(DateParser.parseLocalDateTimetoString(LocalDateTime.now().plusMonths(1)));
        offerDto.setPrice(new BigDecimal("99.99"));

        when(offerService.getOfferById(offerId)).thenReturn(offerDto);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/offer/{id}", offerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.offerId").value(offerId));

        verify(offerService, times(1)).getOfferById(offerId);
    }

    @Test
    public void testDeleteAllOffers() throws Exception {
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/offer"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(offerService, times(1)).deleteAllOffers();
    }

    @Test
    public void testGetAllOffers() throws Exception {
        // Arrange
        OfferDto offerDto1 = new OfferDto();
        offerDto1.setOfferId(1L);
        offerDto1.setStartDate(DateParser.parseLocalDateTimetoString(LocalDateTime.now()));
        offerDto1.setEndDate(DateParser.parseLocalDateTimetoString(LocalDateTime.now().plusMonths(1)));
        offerDto1.setPrice(new BigDecimal("99.99"));

        OfferDto offerDto2 = new OfferDto();
        offerDto2.setOfferId(2L);
        offerDto2.setStartDate(DateParser.parseLocalDateTimetoString(LocalDateTime.now()));
        offerDto2.setEndDate(DateParser.parseLocalDateTimetoString(LocalDateTime.now().plusMonths(1)));
        offerDto2.setPrice(new BigDecimal("129.99"));

        List<OfferDto> offerList = Arrays.asList(offerDto1, offerDto2);

        when(offerService.getAllOffers()).thenReturn(offerList);

        // Act & Assert
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/offer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].offerId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].offerId").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value("99.99"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value("129.99"))
                .andReturn();


        verify(offerService, times(1)).getAllOffers();
    }

    // Método auxiliar para convertir objetos a JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}