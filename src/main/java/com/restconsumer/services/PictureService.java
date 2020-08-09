package com.restconsumer.services;

import com.restconsumer.dtos.PictureDto;
import com.restconsumer.models.Item;

import java.util.List;
import java.util.Optional;

public interface PictureService {

    void updateData(String token);

    List<PictureDto> getAll();

    Optional<Item> getItemById(String id);

    List getPage(Integer page);

    List searchItems(String searchTerm);
}
