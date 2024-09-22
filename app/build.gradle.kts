plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.s375058_mappe1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.s375058_mappe1"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.preference:preference:1.2.0")

    // JUnit for Unit testing
    testImplementation("junit:junit:4.13.2")

    // JUnit for AndroidX Instrumentation Tests
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    // Espresso for UI Testing
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
