package org.koenighotze.cooking.poc

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
class PostgresBootstrapTest {
    companion object PostgreSQLTestImages {
        const val imageName = "postgres:15-alpine"
    }

    @Container
    var postgres = PostgreSQLContainer(imageName)

    var port: Int = 0
    var address: String = ""

    @BeforeEach
    fun setup() {
        port = postgres.firstMappedPort
        address = postgres.host
    }

    @Test
    fun testSimple() {
        println("Postgres running at $address on port $port")
    }
}
