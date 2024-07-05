package com.inditex.hiring.infrastructure.rest;

import com.inditex.hiring.application.OfferService;
import com.inditex.hiring.application.dto.OfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class OfferController {

	private final OfferService offerService;

	@Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }
	
	//Crear
	@RequestMapping(value="/offer", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public OfferDto createOffer(@RequestBody @Valid OfferDto offerdto){
		return offerService.saveOffer(offerdto);
	}

	//Borrar por id
	@RequestMapping(value="/offer/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public Optional<Long> deleteOfferById(@PathVariable("id") Long id){
		offerService.deleteOffer(id);
		return Optional.ofNullable(id);
	}

	//Obtener por id
	@RequestMapping(value="/offer/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public OfferDto getOfferById(@PathVariable Long id){
		return offerService.getOfferById(id);
	}

	//Eliminar todas las ofertas
	@RequestMapping(value = "/offer", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllOffers() {
		offerService.deleteAllOffers();
	}
	
	//Endopint para optener todas las ofertas
	@RequestMapping(value = "/offer", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<OfferDto> getAllOffers() {
		return offerService.getAllOffers();

	}

	
}