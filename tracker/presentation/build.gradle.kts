plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.deme.tracker.presentation"
}

dependencies {
    implementation(project(Modules.trackerDomain))
    implementation(project(Modules.corePresentation))
    implementation(project(Modules.coreDomain))
}