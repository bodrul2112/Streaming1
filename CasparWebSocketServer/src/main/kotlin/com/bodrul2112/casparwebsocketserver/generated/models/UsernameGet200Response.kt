package com.bodrul2112.casparwebsocketserver.generated.models

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import javax.validation.Valid
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 
 * @param name 
 * @param rank 
 */
data class UsernameGet200Response(

    @Schema(example = "John Doe", description = "")
    @get:JsonProperty("name") val name: kotlin.String? = null,

    @Schema(example = "1", description = "")
    @get:JsonProperty("rank") val rank: kotlin.Int? = null
) {

}

