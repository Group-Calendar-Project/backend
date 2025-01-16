package com.gc.api.customer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication(
  scanBasePackages = [
    "com.gc", "com.gc.api.customer"
  ],
  exclude = [DataSourceAutoConfiguration::class]
)
@EnableMongoRepositories(basePackages = ["com.gc.adapter.out.infra.persistence"])
@EnableMongoAuditing(dateTimeProviderRef = "mongoAuditingDateTimeProvider")
class CalendarApplication

fun main(args: Array<String>) {
  runApplication<CalendarApplication>(*args)
}