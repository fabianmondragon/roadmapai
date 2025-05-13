plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.dokkaPlugins)
    alias(libs.plugins.hiltPlugins)
    alias(libs.plugins.googleServices)
    kotlin("kapt")

}

android {
    namespace = "com.fabiandev.roadmapai"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fabiandev.roadmapai"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }


// Add Lint tool configuration
    lint {
        // Enable XML report generation
        xmlReport = true
        // Specify the output file location for lint results
        xmlOutput = file("build/reports/lint-results.xml")
        checkReleaseBuilds = false
        abortOnError = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "META-INF/NOTICE.md"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/LICENSE.txt"

        }
    }
}
kapt {
    javacOptions {
        option("-XaddExports", "jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED")
        option("-XaddExports", "jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED")
        option("-XaddExports", "jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED")
    }
}
kotlin {
    kapt {
        arguments {
            arg("jvmTarget", "17") // Replace with your desired JVM target version
        }
    }
}

configurations.all {
    resolutionStrategy {
        force("com.google.code.gson:gson:2.10.1") // Example: force a specific version
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.navigation.compose)
    implementation(libs.dokka.jetbrains)
    implementation(libs.dagger.hilt)
    implementation(libs.navigation.hilt)
    implementation(libs.javapoet)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)


    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.okhttp)

    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}