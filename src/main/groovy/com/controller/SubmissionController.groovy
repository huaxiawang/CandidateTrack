package com.controller

import com.compiler.CodeCompiler
import com.compiler.CompileResult
import com.domain.Candidate
import com.domain.TestResult
import com.repository.CandidateRepo
import com.repository.TestResultRepo
import com.tester.SolutionTester
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * TODO: Explain This
 *
 * Created by hwang on 1/25/17.
 */
@RestController
@Slf4j
class SubmissionController {

  @Autowired CandidateRepo candidateRepo
  @Autowired TestResultRepo testResultRepo

  @RequestMapping(method = RequestMethod.POST, path = '/submit')
  TestResult submit(@RequestBody String code, @RequestParam(value='candidate') Long candidateId) {
    Candidate candidate = candidateRepo.findOne(candidateId)

    candidate.code = code
    candidate.submitTimes += 1

    CodeCompiler compiler = new CodeCompiler()
    SolutionTester tester = new SolutionTester()
    CompileResult compileResult = compiler.getSolution(code)
    TestResult testResult

    if (compileResult.errorMessage) {
      log.debug "Error Compiling"
      testResult = new TestResult(errorMessage: compileResult.errorMessage, candidate: candidate)
    } else {
      testResult = tester.getResult(compileResult.solution)
      testResult.candidate = candidate
    }

    testResultRepo.save(testResult)
    if (candidate.testResult) {
      candidate.testResult.add(testResult)
    } else {
      candidate.testResult = [testResult]
    }

    candidateRepo.save(candidate)

    testResult
  }
}
