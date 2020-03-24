package org.wjanaszek.medicalresultsengine.result

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.wjanaszek.medicalresultsengine.result.domain.ResultCommandFacade
import org.wjanaszek.medicalresultsengine.result.domain.ResultCommandFacadeImpl
import org.wjanaszek.medicalresultsengine.result.domain.ResultQueryFacade
import org.wjanaszek.medicalresultsengine.result.domain.ResultQueryFacadeImpl
import org.wjanaszek.medicalresultsengine.result.domain.ResultRepository

@Configuration
internal class ResultConfiguration {
  @Bean
  fun resultCommandFacade(resultRepository: ResultRepository): ResultCommandFacade {
    return ResultCommandFacadeImpl(resultRepository)
  }

  @Bean
  fun resultQueryFacade(resultRepository: ResultRepository): ResultQueryFacade {
    return ResultQueryFacadeImpl(resultRepository)
  }
}
