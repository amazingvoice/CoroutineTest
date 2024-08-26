package com.example.coroutinetest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class DispatcherTestRule : TestRule {
    val testDispatcher = StandardTestDispatcher()

//    @ExperimentalCoroutinesApi
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun apply(base: Statement?, description: Description?): Statement {
        try {
            Dispatchers.setMain(testDispatcher)
//            base?.evaluate() 自定义rule里不能显式调用evaluate
        } catch (e: Exception) {
        } finally {
            Dispatchers.resetMain()
//            testDispatcher.cleanupTestCoroutines()
        }
        return base!!
    }
}