package com.repository

import com.domain.Candidate
import org.springframework.data.repository.CrudRepository

/**
 * TODO: Explain This
 *
 * Created by hwang on 1/25/17.
 */
interface CandidateRepo extends CrudRepository<Candidate, Long> {

  List<Candidate> findByLastName(String lastName)

  List<Candidate> findByFirstName(String firstName)
}
