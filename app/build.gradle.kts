plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.gms.google-services")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "dev.kibet.rickyandmorty"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(Dependancies.appCompat)
    implementation(Dependancies.materialM)
    implementation(Dependancies.constraintLayout)
    testImplementation(Dependancies.jUnit4)
    androidTestImplementation(Dependancies.androidJUnit)
    androidTestImplementation(Dependancies.espresso)
    implementation(project(Modules.Presentation))
    implementation(project(Modules.Domain))
    implementation(project(Modules.Data))

    // koin
    implementation(Dependancies.koin)

    // firebase
    implementation(platform(Dependancies.firabaseBom))
    implementation(Dependancies.firebaseAnalytics)
}
