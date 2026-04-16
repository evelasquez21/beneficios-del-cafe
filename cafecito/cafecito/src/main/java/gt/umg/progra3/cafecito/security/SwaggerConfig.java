package gt.umg.progra3.cafecito.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de mi Proyecto",
                version = "1.0",
                description = "Documentación de los endpoints del backend"
        ),
        security = @SecurityRequirement(name = "bearerAuth") // Aplica la seguridad a todos los endpoints
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Pega aquí tu token JWT (sin la palabra Bearer)",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
