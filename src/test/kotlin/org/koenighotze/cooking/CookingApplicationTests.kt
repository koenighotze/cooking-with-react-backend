package org.koenighotze.cooking

import org.junit.jupiter.api.Test
import org.koenighotze.cooking.PostgresBootstrapTest.PostgreSQLTestImages.imageName
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.time.Duration

class DbInit : ApplicationContextInitializer<ConfigurableApplicationContext> {
    companion object PostgreSQLTestImages {
        const val imageName = "postgres:15-alpine"
    }

    @Container
    var postgres: PostgreSQLContainer<*> = PostgreSQLContainer(imageName).withDatabaseName("cooking")
        .withUsername("usr")
        .withPassword("pwd")
    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        postgres.waitingFor(
            Wait
                .forLogMessage(".*database system is ready to accept connections.*$", 2)
                .withStartupTimeout(Duration.ofSeconds(10))
        ).start()

        TestPropertyValues.of(
            "spring.datasource.url=" + postgres.jdbcUrl,
            "spring.datasource.username=" + postgres.username,
            "spring.datasource.password=" + postgres.password
        ).applyTo(applicationContext.environment)
    }
}

@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = [DbInit::class])
class CookingApplicationTests {

    @Test
    fun contextLoads() {
        // TODO write tests
    }
}
