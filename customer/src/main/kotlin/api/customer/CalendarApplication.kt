package api.customer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
  scanBasePackages = [
    "common", "api.customer"
  ],
  exclude = [DataSourceAutoConfiguration::class]
)
class CalendarApplication

fun main(args: Array<String>) {
  runApplication<CalendarApplication>(*args)
}