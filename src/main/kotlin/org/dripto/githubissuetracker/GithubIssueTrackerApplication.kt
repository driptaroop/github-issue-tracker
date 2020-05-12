package org.dripto.githubissuetracker

import javax.sql.DataSource
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@SpringBootApplication
class GithubIssueTrackerApplication

fun main(args: Array<String>) {
    runApplication<GithubIssueTrackerApplication>(*args)
}

@Configuration
class AppConfig {

    @Profile("local")
    @Bean
    fun localDatasource(): DataSource = DataSourceBuilder.create()
        .url("jdbc:h2:mem:testdb")
        .driverClassName("org.h2.Driver")
        .username("sa")
        .password("sa")
        .build()
}
