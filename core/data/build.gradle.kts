plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.deme.core.data"
}
dependencies {
    implementation (project(Modules.coreDomain))
}
