package com.dojo.jsonplaceholder.annotations

import com.dojo.jsonplaceholder.extensions.ParallelApiTestExtension
import org.junit.jupiter.api.extension.ExtendWith

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ExtendWith(ParallelApiTestExtension::class)
annotation class ParallelApiTest