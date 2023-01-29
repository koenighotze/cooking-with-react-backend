package org.koenighotze.cooking

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
class RedisBootstrapTest {
    @Container
    var redis: GenericContainer<*> = GenericContainer(DockerImageName.parse("redis:5.0.3-alpine"))
        .withExposedPorts(6379)

    var port: Int = 0
    var address: String = ""

    @BeforeEach
    fun setup() {
        port = redis.firstMappedPort
        address = redis.host
    }

    @Test
    fun testSimple() {
        println("Redis running at $address on port $port")
    }
}
