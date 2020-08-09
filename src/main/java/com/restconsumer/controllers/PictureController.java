package com.restconsumer.controllers;

import com.restconsumer.exceptions.DataCanNotBeRetrievedException;
import com.restconsumer.exceptions.EntityNotFoundException;
import com.restconsumer.models.AuthResponse;
import com.restconsumer.models.Item;
import com.restconsumer.services.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController
public class PictureController {

    private final PictureService pictureService;
    private final AuthResponse authResponse;

    @GetMapping("/images")
    public List getAll(@RequestParam(required = false) Integer page) {
        updateData();
        if (pictureService.getPage(page).isEmpty()&&pictureService.getAll().isEmpty()){
            throw new DataCanNotBeRetrievedException();
        }
        return page != null ? pictureService.getPage(page) : pictureService.getAll();
    }

    @GetMapping("/images/{id}")
    public Item get(@PathVariable String id) {
        updateData();
        return pictureService.getItemById(id)
                .orElseThrow(() -> new EntityNotFoundException( id));
    }

    @GetMapping("/search/{searchTerm}")
    public List search(@PathVariable String searchTerm) {
        updateData();
        return pictureService.searchItems(searchTerm);
    }

    public void updateData() {
        if (authResponse.isAuthorized()) {
            pictureService.updateData(authResponse.getToken());
        }
    }
}
