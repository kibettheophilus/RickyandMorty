plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(Dependancies.coreTest)
    testImplementation(Dependancies.jUnit4)
    androidTestImplementation(Dependancies.androidJUnit)
    androidTestImplementation(Dependancies.espresso)
    implementation(project(Modules.Domain))

    // koin
    implementation(Dependancies.koin)

    // Retrofit and Gson
    implementation(Dependancies.retrofit)
    implementation(Dependancies.gsonConverter)

    // Coroutines
    implementation(Dependancies.coroutinesCore)
    implementation(Dependancies.coroutinesAndroid)

    // room
    implementation(Dependancies.roomRuntime)
    kapt(Dependancies.roomCompiler)
    implementation(Dependancies.roomKtx)

    // truth
    testImplementation(Dependancies.googleTruth)
    androidTestImplementation(Dependancies.googleTruth)
    // Mockito
    testImplementation(Dependancies.mockito)
    androidTestImplementation(Dependancies.mockito)

    // Robolectric
    testImplementation(Dependancies.robolectric)
    androidTestImplementation(Dependancies.robolectric)

    // MockWebServer
    testImplementation(Dependancies.mockwebserver)
}
