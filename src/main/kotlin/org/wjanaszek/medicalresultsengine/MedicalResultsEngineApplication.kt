package org.wjanaszek.medicalresultsengine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

const val API_URI = "/api"

@SpringBootApplication
class MedicalResultsEngineApplication

fun main(args: Array<String>) {
	runApplication<MedicalResultsEngineApplication>(*args)
}
