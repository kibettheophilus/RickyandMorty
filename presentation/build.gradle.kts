plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        viewBinding = true
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Dependancies.androidXCore)
    testImplementation(Dependancies.androidXCore)
    implementation(Dependancies.appCompat)
    implementation(Dependancies.materialM)
    implementation(Dependancies.legacySupport)
    testImplementation(Dependancies.jUnit4)
    androidTestImplementation(Dependancies.androidJUnit)
    androidTestImplementation(Dependancies.espresso)
    implementation(project(Modules.Domain))
    implementation(project(Modules.Data))

    // Navigation Components
    implementation(Dependancies.navigationUI)
    implementation(Dependancies.fragmentKtx)
    implementation(Dependancies.navigationKtx)

    // koin
    implementation(Dependancies.koin)

    // Lifecycle Dependencies
    implementation(Dependancies.viewmodel)

    // KTX for viewModels()
    implementation(Dependancies.activityKtx)

    // circle image
    implementation(Dependancies.circleImage)

    // glide
    implementation(Dependancies.glide)
    kapt(Dependancies.glideCompiler)

    // Coroutines
    implementation(Dependancies.coroutinesCore)
    implementation(Dependancies.coroutinesAndroid)
    testImplementation(Dependancies.coroutinesTest)

    // truth
    testImplementation(Dependancies.googleTruth)
    androidTestImplementation(Dependancies.googleTruth)
}
