plugins {
    id("com.android.library")
}

android {
    namespace = "com.unnamed.b.atv"
    compileSdk = 33

    defaultConfig {
      //  applicationId = "com.unnamed.b.atv"
        minSdk = 21
        targetSdk = 33
      //  versionCode = 1
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
  //  compile(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.appcompat:appcompat:1.6.1")
}