package org.dripto.githubissuetracker.controller

import org.dripto.githubissuetracker.service.HelloService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/hello")
class HelloController (private val helloService: HelloService) {
    @GetMapping("/world")
    suspend fun world() = "Hello, World!!"

    @PostMapping("/addition")
    suspend fun addition(@RequestBody input: InputValues) = helloService.doAddition(*input.input.toIntArray())
}

data class InputValues(val input: List<Int>)
