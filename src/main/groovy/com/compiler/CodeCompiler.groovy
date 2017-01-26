package com.compiler

import groovy.util.logging.Slf4j

/**
 * TODO: Explain This
 *
 * Created by hwang on 1/25/17.
 */
@Slf4j
class CodeCompiler {

  GroovyClassLoader groovyClassLoader = new GroovyClassLoader()

  CompileResult getSolution(String code) {
    CompileResult result = new CompileResult()
    try {

      Class groovyClass = groovyClassLoader.parseClass(code)
      GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance()
      if (!groovyObject.metaClass.respondsTo(groovyObject, 'findInGrid', int[][], int)) {
        result.errorMessage = "Cannot find method 'findInGrid'"
      }

      result.solution = groovyObject
    } catch (Exception e) {
      log.debug "Compiling failed, ${e.message}"
      result.errorMessage = e.message
    }

    result
  }
}
