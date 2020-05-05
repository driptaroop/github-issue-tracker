package org.dripto.githubissuetracker.service

import org.springframework.stereotype.Service

@Service
class HelloService {
    fun doAddition(vararg i: Int) = i.fold(0) { a, b -> a + b }

    fun subtraction(i: Int, j: Int) = i - j
}
