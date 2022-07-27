package com.car.carAdverts.api;

import com.car.carAdverts.dto.Advert;
import com.car.carAdverts.service.AdvertService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class CarAdvertController {

    private final AdvertService advertService;

    @PostMapping("/car/adverts")
    public ResponseEntity<Advert> createAdvert(@RequestBody @Valid Advert advertData) {
        return new ResponseEntity<>(advertService.createAdvert(advertData), HttpStatus.CREATED);
    }

    @DeleteMapping("/car/adverts/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAdvert(@PathVariable("id") Long id) {
        advertService.deleteAdvert(id);
    }

    @GetMapping("/car/adverts/{id}")
    public Advert getAdvert(@PathVariable("id") Long id) {
        return advertService.getAdvert(id);
    }

    @GetMapping("/car/adverts")
    public List<Advert> getAdverts(@RequestParam(value = "sortBy", required = false) String sortBy) {
        return advertService.getAdverts(sortBy);
    }

    @PutMapping("/car/adverts/{id}")
    public Advert modifyAdvert(@PathVariable("id") Long id, @RequestBody @Valid Advert newAdvertData) {
        return advertService.modifyAdvert(id, newAdvertData);
    }
}
