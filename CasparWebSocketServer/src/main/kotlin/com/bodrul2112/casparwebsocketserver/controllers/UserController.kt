package com.bodrul2112.casparwebsocketserver.controllers

import com.bodrul2112.casparwebsocketserver.generated.apis.UsernameApi
import com.bodrul2112.casparwebsocketserver.generated.models.UsernameGet200Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserDataController : UsernameApi {

    override fun usernameGet(): ResponseEntity<UsernameGet200Response> {
        return ResponseEntity.ok(UsernameGet200Response(name="John Doe", rank=1))
    }
}