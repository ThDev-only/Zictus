
plugins {
    id("com.android.application")
    
}

android {
    namespace = "io.github.thdevonly.zictus"
    compileSdk = 33
    
    defaultConfig {
        applicationId = "io.github.thdevonly.zictus"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        
        vectorDrawables { 
            useSupportLibrary = true
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        
    }
}

dependencies {
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    
    //treeView for FileTree
    implementation(project(":treeview"))
    
    //CodeView
    implementation("io.github.amrdeveloper:codeview:1.3.9")
}
