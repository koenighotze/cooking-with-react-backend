package org.koenighotze.cooking.config

import org.koenighotze.cooking.logging.LoggerDelegate
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Container
import java.time.Duration

class DbInit : ApplicationContextInitializer<ConfigurableApplicationContext> {
    private val logger by LoggerDelegate()

    companion object PostgreSQLTestImages {
        const val imageName = "postgres:15-alpine"
    }

    @Container
    var postgres: PostgreSQLContainer<*> = PostgreSQLContainer(imageName).withDatabaseName("cooking")
        .withUsername("usr")
        .withPassword("pwd")

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        logger.info("Setting up PostgreSQL test container")
        postgres.waitingFor(
            Wait.forLogMessage(".*database system is ready to accept connections.*$", 2)
                .withStartupTimeout(Duration.ofSeconds(10))
        ).start()

        TestPropertyValues.of(
            "spring.datasource.url=" + postgres.jdbcUrl,
            "spring.datasource.username=" + postgres.username,
            "spring.datasource.password=" + postgres.password
        ).applyTo(applicationContext.environment)
    }
}
