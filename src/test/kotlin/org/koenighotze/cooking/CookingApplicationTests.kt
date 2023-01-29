package org.koenighotze.cooking

import org.junit.jupiter.api.Test
import org.koenighotze.cooking.config.DbInit
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = [DbInit::class])
class CookingApplicationTests {

    @Test
    fun contextLoads() {
        // TODO write tests
    }
}
