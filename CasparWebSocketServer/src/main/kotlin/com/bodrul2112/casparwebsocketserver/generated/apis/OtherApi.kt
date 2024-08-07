/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.7.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
*/
package com.bodrul2112.casparwebsocketserver.generated.apis

import com.bodrul2112.casparwebsocketserver.generated.models.OtherGet200Response
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.enums.*
import io.swagger.v3.oas.annotations.media.*
import io.swagger.v3.oas.annotations.responses.*
import io.swagger.v3.oas.annotations.security.*
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*
import org.springframework.validation.annotation.Validated
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.beans.factory.annotation.Autowired

import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import javax.validation.Valid

import kotlin.collections.List
import kotlin.collections.Map

@Validated
interface OtherApi {

    @Operation(
        tags = ["default",],
        summary = "Get username data",
        operationId = "otherGet",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Successful response", content = [Content(schema = Schema(implementation = OtherGet200Response::class))])
        ]
    )
    @RequestMapping(
            method = [RequestMethod.GET],
            value = ["/other"],
            produces = ["application/json"]
    )
    fun otherGet(): ResponseEntity<OtherGet200Response> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
