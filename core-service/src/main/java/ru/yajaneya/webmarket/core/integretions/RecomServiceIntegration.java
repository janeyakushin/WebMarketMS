package ru.yajaneya.webmarket.core.integretions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.yajaneya.webmarket.api.recoms.RecomProductDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RecomServiceIntegration {
    private final WebClient recomServiceWebClient;

    public void sendProductRecom(List<RecomProductDto> recomProductDtos) {
        recomServiceWebClient.post()
                .uri("/api/v1/recoms")
                .syncBody(recomProductDtos)
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }

}
