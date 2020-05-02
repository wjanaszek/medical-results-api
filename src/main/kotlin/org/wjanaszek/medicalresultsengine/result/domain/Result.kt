package org.wjanaszek.medicalresultsengine.result.domain

import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "RESULTS")
internal data class Result(
  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @Column(name = "NAME", length = 50, nullable = false)
  val name: String,

  @Column(name = "DESCRIPTION", length = 1500)
  val description: String,

  @OneToMany(mappedBy = "result")
  val norms: Set<ResultNorm> = setOf(),

  @Column(name = "ABOVE_NORM", length = 1500)
  val aboveNorm: String,

  @Column(name = "BELOW_NORM", length = 1500)
  val belowNorm: String
)
