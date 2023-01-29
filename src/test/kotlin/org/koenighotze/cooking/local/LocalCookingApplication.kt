package org.koenighotze.cooking.local

import org.koenighotze.cooking.CookingApplication
import org.koenighotze.cooking.config.DbInit
import org.koenighotze.cooking.config.Profiles.LOCAL
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class LocalCookingApplication

fun main(args: Array<String>) {
    val application = SpringApplication(CookingApplication::class.java)
    application.setAdditionalProfiles(LOCAL)
    application.addInitializers(DbInit())
    val ctx = application.run(*args)
//    ctx.beanDefinitionNames.iterator().forEach { println(it) }
}
