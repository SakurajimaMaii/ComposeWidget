import cn.govast.plugin.version.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("cn.govast.plugin.version")
}

android {
    compileSdk = Version.compile_sdk_version

    defaultConfig {
        applicationId = "com.example.composewidget"
        minSdk = Version.min_sdk_version
        targetSdk = Version.target_sdk_version
        versionCode = Version.version_code
        versionName = Version.version_name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),"proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = Version.java_version
        targetCompatibility = Version.java_version
    }

    kotlinOptions {
        jvmTarget = Version.java_version.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(AndroidX.core_ktx)
    implementation(Compose.compose_ui)
    implementation(Compose.compose_material)
    implementation(Compose.compose_ui_tooling_preview)
    implementation(AndroidX.lifecycle_runtime_ktx)
    implementation(Compose.compose_activity)
    testImplementation(Libraries.junit)
    androidTestImplementation(AndroidX.junit)
    androidTestImplementation(AndroidX.espresso_core)
    androidTestImplementation(Compose.compose_ui_test_junit4)
    debugImplementation(Compose.compose_ui_tooling)
}