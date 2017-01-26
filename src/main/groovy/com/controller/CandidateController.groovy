package com.controller

import com.domain.Candidate
import com.repository.CandidateRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


/**
 * TODO: Explain This
 *
 * Created by hwang on 1/25/17.
 */
@RestController
class CandidateController {

  @Autowired CandidateRepo candidateRepo

  @PostMapping("/candidate")
  Candidate create(@RequestBody Candidate candidate) {
    if (candidate != null) {
      return candidateRepo.save(candidate)
    }
    null
  }

  @GetMapping('/candidate/{id}')
  Candidate get(@PathVariable('id') Long id) {
    candidateRepo.findOne(id)
  }

  @GetMapping('/candidate')
  Candidate[] getAll() {
    candidateRepo.findAll()
  }
}
