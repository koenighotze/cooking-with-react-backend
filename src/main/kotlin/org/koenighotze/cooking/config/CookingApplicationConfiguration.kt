package org.koenighotze.cooking.config

import org.koenighotze.cooking.CookingApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [CookingApplication::class])
@ConfigurationPropertiesScan
class CookingApplicationConfiguration
