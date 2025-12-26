package com.dojo.jsonplaceholder.annotations

import com.dojo.jsonplaceholder.extensions.ParallelApiTestExtension
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import org.junit.jupiter.api.extension.ExtendWith

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(ParallelApiTestExtension::class) // ТОЛЬКО наш extension
annotation class ParallelApiTest