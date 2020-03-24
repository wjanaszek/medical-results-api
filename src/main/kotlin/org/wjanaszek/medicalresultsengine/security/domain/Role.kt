package org.wjanaszek.medicalresultsengine.security.domain

import org.wjanaszek.medicalresultsengine.security.value.AuthorityName
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "AUTHORITIES")
data class Role(
  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long? = null,

  @Column(name = "NAME")
  @Enumerated(EnumType.STRING)
  val name: AuthorityName? = null
)
