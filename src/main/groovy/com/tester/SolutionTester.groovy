package com.tester

import com.domain.TestResult
import groovy.util.logging.Slf4j

/**
 * TODO: Explain This
 *
 * Created by hwang on 1/25/17.
 */
@Slf4j
class SolutionTester {

  static final int[][] miniSet = [// Start list of lists
                                  [1, 2, 3],
                                  [3, 4, 6],
                                  [4, 6, 7],
                                  /*end list of lists*/] as int[][]

  static final int[][] decaSet = readFile('input10.txt')
  static final int[][] hectoSet = readFile('input100.txt')
  static final int[][] kiloSet = readFile('input1000.txt')

  TestResult getResult(GroovyObject solution) {
    int passed = 0
    try {
      testInit()
      passed += 1
      testOne(solution)
      passed += 1
      testPositive(solution)
      passed += 1
      testNegative(solution)
      passed += 1
      testDecaPositive(solution)
      passed += 1
      testDecaNegative(solution)
      passed += 1
      testHectoPositive(solution)
      passed += 1
      testHectoNegative(solution)
      passed += 1
      testKiloPositive(solution)
      passed += 1
      testKiloNegative(solution)
      passed += 1
    } catch (AssertionError e) {
      log.debug "Get error test result ..., ${e.message}"
      return new TestResult(passed: false, errorMessage: e.message, passedTestCaseNumber: passed)
    }
    return new TestResult(passed: true, passedTestCaseNumber: 10)
  }

  static int[][] readFile(String fileName) {
    def text = SolutionTester.class.classLoader.getResourceAsStream(fileName).text
    def lines = text.split('\n')
    lines[1..-1].collect {it.split(' ').collect {Integer.parseInt(it)}}
  }

  private int valueAt(int[][] grid, List pos) {
    grid[pos[0]][pos[1]]
  }

  void testInit() {
    assert decaSet
    assert hectoSet
    assert kiloSet
  }

  void testOne(GroovyObject solution) {
    // This just primes the system
    solution.findInGrid(miniSet, 2000)
  }

  void testPositive(GroovyObject groovyObject) {
    assert valueAt(miniSet, groovyObject.findInGrid(miniSet, 3)) == 3
    assert valueAt(miniSet, groovyObject.findInGrid(miniSet, 2)) == 2
    assert valueAt(miniSet, groovyObject.findInGrid(miniSet, 6)) == 6
    assert valueAt(miniSet, groovyObject.findInGrid(miniSet, 7)) == 7
    assert valueAt(miniSet, groovyObject.findInGrid(miniSet, 1)) == 1
  }

  void testNegative(GroovyObject groovyObject) {
    assert !groovyObject.findInGrid(miniSet, 5)
    assert !groovyObject.findInGrid(miniSet, 0)
    assert !groovyObject.findInGrid(miniSet, 8)
  }

  void testDecaPositive(GroovyObject groovyObject) {
    assert valueAt(decaSet, groovyObject.findInGrid(decaSet, 83)) == 83
    assert valueAt(decaSet, groovyObject.findInGrid(decaSet, 79)) == 79
    assert valueAt(decaSet, groovyObject.findInGrid(decaSet, 55)) == 55
    assert valueAt(decaSet, groovyObject.findInGrid(decaSet, 67)) == 67

  }

  void testDecaNegative(GroovyObject groovyObject) {
    assert !groovyObject.findInGrid(decaSet, 72)
    assert !groovyObject.findInGrid(decaSet, 0)
    assert !groovyObject.findInGrid(decaSet, 170)
  }

  void testHectoPositive(GroovyObject groovyObject) {
    assert valueAt(hectoSet, groovyObject.findInGrid(hectoSet, 70)) == 70
    assert valueAt(hectoSet, groovyObject.findInGrid(hectoSet, 5066)) == 5066
    assert valueAt(hectoSet, groovyObject.findInGrid(hectoSet, 5191)) == 5191
    assert valueAt(hectoSet, groovyObject.findInGrid(hectoSet, 5750)) == 5750
  }

  void testHectoNegative(GroovyObject groovyObject) {
    assert !groovyObject.findInGrid(hectoSet, 5751)
    assert !groovyObject.findInGrid(hectoSet, 69)
    assert !groovyObject.findInGrid(hectoSet, 14487)
    assert !groovyObject.findInGrid(hectoSet, 14600)
  }

  void testKiloPositive(GroovyObject groovyObject) {
    assert valueAt(kiloSet, groovyObject.findInGrid(kiloSet, 566)) == 566
    assert valueAt(kiloSet, groovyObject.findInGrid(kiloSet, 501189)) == 501189
    assert valueAt(kiloSet, groovyObject.findInGrid(kiloSet, 510923)) == 510923
    assert valueAt(kiloSet, groovyObject.findInGrid(kiloSet, 1497341)) == 1497341
    assert valueAt(kiloSet, groovyObject.findInGrid(kiloSet, 791906)) == 791906
  }

  void testKiloNegative(GroovyObject groovyObject) {
    assert !groovyObject.findInGrid(kiloSet, 791901)
    assert !groovyObject.findInGrid(kiloSet, 69)
    assert !groovyObject.findInGrid(kiloSet, 1497342)
    assert !groovyObject.findInGrid(kiloSet, 1497340)
  }
}
