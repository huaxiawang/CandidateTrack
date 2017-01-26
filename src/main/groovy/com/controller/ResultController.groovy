package com.controller

import com.domain.TestResult
import com.repository.TestResultRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * TODO: Explain This
 *
 * Created by hwang on 1/25/17.
 */
@RestController
class ResultController {

  @Autowired TestResultRepo repository

  @RequestMapping(method = RequestMethod.GET, path = '/result/{id}')
  TestResult getResult(@PathVariable('id') Long id) {
    repository.findOne(id)
  }

  @RequestMapping(method = RequestMethod.GET, path = '/result')
  TestResult[] getResults() {
    repository.findAll()
  }
}
