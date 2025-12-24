plugins {
    kotlin("jvm") version "1.9.22" apply false  // Обновили с 1.9.0 до 1.9.22
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
