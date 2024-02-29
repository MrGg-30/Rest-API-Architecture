package org.example.controller.error;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ApiResponse(
        responseCode = "500",
        description = "Unhandled exception occurred during request processing",
        content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ApiErrorResponse.class),
                examples = {
                        @ExampleObject(
                                name = "internal-server-error",
                                value = OpenApiErrorResponseExamples.INTERNAL_SERVER_ERROR_EXAMPLE
                        ),
                        @ExampleObject(
                                name = "external-server-error",
                                value = OpenApiErrorResponseExamples.EXTERNAL_SERVER_ERROR_EXAMPLE
                        )
                }
        ))
public @interface OpenApi500ErrorResponse {

}
