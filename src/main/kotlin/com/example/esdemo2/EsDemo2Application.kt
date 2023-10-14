package com.example.esdemo2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EsDemo2Application
fun main(args: Array<String>) {
//	SpringApplication.run(EsDemo2Application::class.java, *args)
	runApplication<EsDemo2Application>(*args)
}
