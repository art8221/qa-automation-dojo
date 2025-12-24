plugins {
    kotlin("jvm")
}

kotlin {
    jvmToolchain(21)  // ������ Kotlin 1.9.22 ������������ Java 21
}

dependencies {
    // Testing only
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
    
    // API Testing
    testImplementation("io.rest-assured:rest-assured:5.3.0")
    testImplementation("io.rest-assured:json-schema-validator:5.3.0")
    testImplementation("io.rest-assured:kotlin-extensions:5.3.0")
    
    // JSON
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
}
