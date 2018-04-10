package com.transport.transportation.swagger;

import io.swagger.annotations.*;

@SwaggerDefinition(
        info = @Info(
                description = "Transportation",
                version = "V1.0",
                title = "Transportation Resource API",
                contact = @Contact(
                        name = "Sajan",
                        email = "spknair@hotmail.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS}
)
public class ApiDocumentationConfig {
}
