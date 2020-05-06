package org.dripto.githubissuetracker.controller

import org.dripto.githubissuetracker.service.HelloService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.ResponseBody

@RestController
@RequestMapping("/hello")
class HelloController(private val helloService: HelloService) {
    @GetMapping("/world")
    suspend fun world() = "Hello, World!!"

    @PostMapping("/addition")
    suspend fun addition(@RequestBody input: InputValues) = helloService.doAddition(*input.input.toIntArray())

    @GetMapping("/subtract/{i}/{j}")
    suspend fun subtract(@PathVariable i: Int, @PathVariable j: Int) = helloService.subtraction(i, j)
}

data class InputValues(val input: List<Int>)
