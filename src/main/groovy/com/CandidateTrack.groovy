package com

import com.domain.Candidate
import com.domain.TestResult
import com.repository.CandidateRepo
import com.repository.TestResultRepo
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Component

@Slf4j
@SpringBootApplication
public class CandidateTrack {

  public static void main(String[] args)
  {
    SpringApplication.run(CandidateTrack.class, args)
  }
}

@Component
public class Init implements ApplicationRunner {

  @Autowired TestResultRepo testResultRepo
  @Autowired CandidateRepo candidateRepo

  @Override
  void run(ApplicationArguments args) throws Exception {

    Candidate candidate = new Candidate(firstName: 'John', lastName: 'Smith')
    List<TestResult> testResults = [
        new TestResult(passed: true, passedTestCaseNumber: 10, candidate: candidate),
        new TestResult(passed: false, passedTestCaseNumber: 1, candidate: candidate),
        new TestResult(passed: false, passedTestCaseNumber: 5, candidate: candidate)
    ]
    candidate.testResult = testResults

    candidateRepo.save(candidate)
  }
}
