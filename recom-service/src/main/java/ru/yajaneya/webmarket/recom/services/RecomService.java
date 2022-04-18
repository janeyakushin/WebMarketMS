package ru.yajaneya.webmarket.recom.services;

import org.springframework.stereotype.Service;
import ru.yajaneya.webmarket.api.recoms.RecomProductDto;
import ru.yajaneya.webmarket.recom.entities.RecomProduct;

import java.util.List;
import java.util.Optional;

@Service
public interface RecomService {
    List<RecomProductDto> findAll ();
    Optional<RecomProduct> findByID (Long id);
    void save (List<RecomProductDto> recomProductDtos);

}
