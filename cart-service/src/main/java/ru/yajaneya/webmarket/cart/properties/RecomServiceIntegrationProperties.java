package ru.yajaneya.webmarket.cart.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.recom-service")
@Data
@NoArgsConstructor
public class RecomServiceIntegrationProperties {
    private String url;
}
