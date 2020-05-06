package org.dripto.githubissuetracker.service

import io.mockk.junit5.MockKExtension
import kotlin.random.Random
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

@ExtendWith(MockKExtension::class)
internal class HelloServiceTest {
    private val service = HelloService()

    @Test
    fun `Test doAddition`() {
        val input: List<Int> = List(Random.nextInt(1000)) {
            Random.nextInt(1000)
        }
        expectThat(service.doAddition(*input.toIntArray()))
                .isA<Int>()
                .isEqualTo(input.fold(0) { a, b -> a + b })
    }

    @Test
    fun `Test subtraction`() {
        val i = Random.nextInt()
        val j = Random.nextInt()

        expectThat(service.subtraction(i, j))
                .isA<Int>()
                .isEqualTo(i - j)
    }
}
