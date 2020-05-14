package org.dripto.githubissuetracker

import org.dripto.githubissuetracker.config.AppConfig
import org.springframework.boot.Banner.Mode.CONSOLE
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(AppConfig::class)
class GithubIssueTrackerApplication

fun main(args: Array<String>) {
    // fluent api for spring application bootstrapping
    // SpringApplicationBuilder().sources(GithubIssueTrackerApplication::class.java).bannerMode(OFF).run(*args)
    runApplication<GithubIssueTrackerApplication>(*args) {
        setBannerMode(CONSOLE)
    }
}
