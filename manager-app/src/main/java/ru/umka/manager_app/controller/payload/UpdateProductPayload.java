package ru.umka.manager_app.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProductPayload (
        @NotNull
        @Size(min = 3, max = 30)
        String title,

        @Size(max = 300)
        String details){

}
