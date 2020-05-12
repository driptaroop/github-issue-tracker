package org.dripto.githubissuetracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GithubIssueTrackerApplication

fun main(args: Array<String>) {
    runApplication<GithubIssueTrackerApplication>(*args)
}
