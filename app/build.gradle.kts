plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.zj.smart"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zj.smart"
        minSdk = 28
        targetSdk = 34
        versionCode = 7
        versionName = "2.5"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a")
        }
    }

    buildTypes {
        release {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
    packagingOptions {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.vr:sdk-panowidget:1.80.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    // To load images
    implementation("io.coil-kt:coil:2.4.0")

    val composeVersion = "1.4.3"
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-graphics:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.material3:material3:1.1.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")

    val accompanistVersion = "0.30.1"
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-permissions:$accompanistVersion")

    val cameraVersion = "1.2.3"
    implementation("androidx.camera:camera-core:$cameraVersion")
    // CameraX Camera2 extensions[可选]拓展库可实现人像、HDR、夜间和美颜、滤镜但依赖于OEM
    implementation("androidx.camera:camera-camera2:$cameraVersion")
    // CameraX Lifecycle library[可选]避免手动在生命周期释放和销毁数据
    implementation("androidx.camera:camera-lifecycle:$cameraVersion")
    // CameraX View class[可选]最佳实践，最好用里面的PreviewView，它会自行判断用SurfaceView还是TextureView来实现
    implementation("androidx.camera:camera-view:$cameraVersion")

    val glanceVersion = "1.0.0-beta01"
    implementation("androidx.glance:glance:$glanceVersion")
    implementation("androidx.glance:glance-appwidget:$glanceVersion")
    implementation("androidx.glance:glance-material3:$glanceVersion")

    val navVersion = "2.6.0"
    implementation("androidx.navigation:navigation-compose:$navVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}