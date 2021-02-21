/*
 *    Copyright 2021 Tegar Bangun Suganda, ASTARIA.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import com.github.suganda8.lxiv.Configuration
import com.github.suganda8.lxiv.Dependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
}

ext {
    set("PUBLISH_GROUP_ID", Configuration.artifactGroup)
    set("PUBLISH_VERSION", Configuration.versionName)
    set("PUBLISH_ARTIFACT_ID", Configuration.artifactLXIV)
}

apply {
    from("${rootProject.projectDir}/scripts/publish-mavencentral.gradle")
}

android {
    compileSdkVersion(Configuration.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Configuration.minSdkVersion)
        targetSdkVersion(Configuration.targetSdkVersion)
        versionName = Configuration.versionName
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.kotlinStdLib)
}