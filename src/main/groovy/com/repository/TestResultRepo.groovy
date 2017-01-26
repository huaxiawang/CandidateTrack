package com.repository

import com.domain.TestResult
import org.springframework.data.repository.CrudRepository

/**
 * TODO: Explain This
 *
 * Created by hwang on 1/25/17.
 */
interface TestResultRepo extends CrudRepository<TestResult, Long> {
  List<TestResult> findByPassed(boolean passed)
}