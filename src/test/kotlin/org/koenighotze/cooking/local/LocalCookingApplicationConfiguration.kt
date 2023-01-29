package org.koenighotze.cooking.local

import org.koenighotze.cooking.config.DbInit
import org.koenighotze.cooking.config.Profiles.LOCAL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
class Foo

@Configuration
@Profile(LOCAL)
@ComponentScan(
    basePackageClasses = [
        DbInit::class
    ]
)
class LocalCookingApplicationConfiguration {
    @Bean
    fun fooBean(): Foo {
        return Foo()
    }
}
