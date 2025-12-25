plugins {
    kotlin("jvm")
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("io.rest-assured:rest-assured:5.3.0")
    testImplementation("io.rest-assured:json-schema-validator:5.3.0")
    testImplementation("io.rest-assured:kotlin-extensions:5.3.0")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    testImplementation("ch.qos.logback:logback-classic:1.4.11")
    testImplementation("org.slf4j:slf4j-api:2.0.9")
}

// 🔧 КОДИРОВКА
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

// 🔧 ГАРАНТИЯ UTF-8 ВЕЗДЕ
tasks.withType<Test> {
    systemProperties = mapOf(
        "file.encoding" to "UTF-8",
        "sun.jnu.encoding" to "UTF-8",
        "sun.stdout.encoding" to "UTF-8",
        "sun.stderr.encoding" to "UTF-8"
    )

    jvmArgs = listOf(
        "-Dfile.encoding=UTF-8",
        "-Dsun.stdout.encoding=UTF-8",
        "-Dsun.stderr.encoding=UTF-8"
    )
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "failed", "skipped")
        showStandardStreams = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL

        // В CI - детальные логи
        if (System.getenv("CI") == "true") {
            events("passed", "failed", "skipped", "standard_out", "standard_error")
            showCauses = true
            showStackTraces = true
        }
    }

    // Отчёты
    reports {
        html.required.set(true)
        junitXml.required.set(true)
    }
}