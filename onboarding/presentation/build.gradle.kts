plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.deme.onboarding.presentation"
}

dependencies {
    implementation(project(Modules.onboardingDomain))
}