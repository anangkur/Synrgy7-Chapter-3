plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.anangkur.synrgychapter3.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL_TMDB", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "BASE_URL_REQRES", "\"https://reqres.in/api/\"")
            buildConfigField("String", "TMDB_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YjliZmIwZTgzZGUyYTRhZmIxN2MxNTdjY2IyNTRmMyIsInN1YiI6IjViZWFmNjExMGUwYTI2M2JmMzA1N2I4YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.MrSIS0bBQ6yG-KQIeDGTxgnFHd2n9pcKRw38Z-rlpao\"")
        }
        create("staging") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL_TMDB", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "BASE_URL_REQRES", "\"https://reqres.in/api/\"")
            buildConfigField("String", "TMDB_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YjliZmIwZTgzZGUyYTRhZmIxN2MxNTdjY2IyNTRmMyIsInN1YiI6IjViZWFmNjExMGUwYTI2M2JmMzA1N2I4YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.MrSIS0bBQ6yG-KQIeDGTxgnFHd2n9pcKRw38Z-rlpao\"")
        }
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL_TMDB", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "BASE_URL_REQRES", "\"https://reqres.in/api/\"")
            buildConfigField("String", "TMDB_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YjliZmIwZTgzZGUyYTRhZmIxN2MxNTdjY2IyNTRmMyIsInN1YiI6IjViZWFmNjExMGUwYTI2M2JmMzA1N2I4YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.MrSIS0bBQ6yG-KQIeDGTxgnFHd2n9pcKRw38Z-rlpao\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.room)
    implementation(libs.room.ktx)
    implementation(libs.androidx.core)
    ksp(libs.room.compiler)

    implementation("androidx.datastore:datastore-preferences:1.1.1")

    implementation(libs.gson)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")

    testImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")
    testImplementation(libs.junit)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
}