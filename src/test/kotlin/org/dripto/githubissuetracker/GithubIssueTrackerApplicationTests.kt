package org.dripto.githubissuetracker

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isGreaterThan
import strikt.assertions.isLessThan

@SpringBootTest
class GithubIssueTrackerApplicationTests {

    @Test
    fun sampleTest() {
        expectThat(1 + 1)
                .isA<Int>()
                .isGreaterThan(1)
                .isLessThan(4)
                .isEqualTo(2)
    }
}
