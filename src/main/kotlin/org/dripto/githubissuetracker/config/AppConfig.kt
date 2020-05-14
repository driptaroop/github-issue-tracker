package org.dripto.githubissuetracker.config

import org.dripto.githubissuetracker.components.SampleComponent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun sampleComponent() = SampleComponent()
}
