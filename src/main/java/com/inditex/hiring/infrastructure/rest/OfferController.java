package com.inditex.hiring.infrastructure.rest;

import java.util.List;
import java.util.Optional;

import com.inditex.hiring.application.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.inditex.hiring.application.dto.OfferDto;


import javax.validation.Valid;


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
		return this.offerService.saveOffer(offerdto);
	}

	//Borrar por id
	@RequestMapping(value="/offer/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public Optional<Long> deleteOfferById(@PathVariable("id") Long id){
		this.offerService.deleteOffer(id);
		return Optional.ofNullable(id);
	}

	//Obtener por id
	@RequestMapping(value="/offer/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public OfferDto getOfferById(@PathVariable Long id){
		return this.offerService.getOfferById(id);
	}

	//Eliminar todas las ofertas
	@RequestMapping(value = "/offer", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllOffers() {
		this.offerService.deleteAllOffers();
	}
	
	//Endopint para optener todas las ofertas
	@RequestMapping(value = "/offer", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<OfferDto> getAllOffers() {
		return this.offerService.getAllOffers();

	}

	
}