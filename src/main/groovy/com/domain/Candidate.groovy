package com.domain

import com.fasterxml.jackson.annotation.JsonManagedReference
import groovy.transform.Canonical

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

/**
 * TODO: Explain This
 *
 * Created by hwang on 1/25/17.
 */
@Entity(name = 'candidate')
@Canonical
class Candidate {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = 'candidate_id')
  Long id

  String firstName

  String lastName

  @Column(length = 5000)
  String code

  int submitTimes = 0

  @OneToMany(mappedBy = 'candidate', fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JsonManagedReference
  List<TestResult> testResult

  @Override
  String toString() {
    "$firstName $lastName"
  }
}
