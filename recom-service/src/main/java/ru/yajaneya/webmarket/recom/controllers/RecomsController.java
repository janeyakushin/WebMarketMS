package ru.yajaneya.webmarket.recom.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yajaneya.webmarket.api.recoms.RecomProductDto;
import ru.yajaneya.webmarket.recom.services.RecomServiceFabric;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recoms")
@RequiredArgsConstructor
public class RecomsController {
    private final RecomServiceFabric recomServiceFabric;

    @GetMapping
    public List<RecomProductDto> getRecoms(@RequestParam String type) {
        return recomServiceFabric.getRecomeService(type).findAll();
    }

    @PostMapping
    public void create (@RequestBody List<RecomProductDto> recomProductDtos) {
        String type = recomProductDtos.get(0).getType();
        recomServiceFabric.getRecomeService(type).save(recomProductDtos);
    }

}
