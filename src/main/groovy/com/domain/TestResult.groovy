package com.domain

import com.fasterxml.jackson.annotation.JsonBackReference

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

/**
 * TODO: Explain This
 *
 * Created by hwang on 1/25/17.
 */
@Entity
class TestResult {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id

  boolean passed = false

  final int totalTestCaseNumber = 10

  int passedTestCaseNumber = 0

  @Column(length = 5000)
  String errorMessage

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = 'candidate_id')
  @JsonBackReference
  Candidate candidate

  @Override
  String toString() {
    "$candidate $passed $passedTestCaseNumber/$totalTestCaseNumber"
  }
}
