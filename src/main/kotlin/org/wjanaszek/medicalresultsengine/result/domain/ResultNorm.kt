package org.wjanaszek.medicalresultsengine.result.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "RESULT_NORMS")
internal data class ResultNorm(
  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @Column(name = "GRAMS_PER_LITER")
  val gramsPerLiter: Int,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "RESULT")
  @JsonIgnore
  val result: Result
)
