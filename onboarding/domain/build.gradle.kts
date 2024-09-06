plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.deme.onboarding.domain"
}

dependencies {
    implementation(project(Modules.coreDomain))
}