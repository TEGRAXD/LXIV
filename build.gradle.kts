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

import Dependencies

apply(plugin = "de.marcphilipp.nexus-publish")
apply(plugin = "io.codearte.nexus-staging")
apply(plugin = "org.jetbrains.dokka")

buildscript {
    val kotlin_version = "1.4.30"

    repositories {
        google()
        jcenter()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        maven("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
    }
    dependencies {
        classpath(Dependencies.androidGradlePlugin)
        classpath(Dependencies.kotlinGradlePlugin)
        classpath(Dependencies.navigationSafeArgsGradlePlugin)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        classpath(Dependencies.gradleNexusPublishPlugin)
        classpath(Dependencies.gradleNexusStagingPlugin)
        classpath(Dependencies.dokka)
    }

}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        maven("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}