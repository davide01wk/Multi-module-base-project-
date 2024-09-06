plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.deme.tracker.domain"
}

dependencies {
    implementation(project(Modules.core))
}