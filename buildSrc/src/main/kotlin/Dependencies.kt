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

private const val ANDROID_GRADLE_PLUGIN_VERSION = "4.1.2"
private const val ANDROIDX_APPCOMPAT_VERSION = "1.2.0"
private const val ANDROIDX_KTX_VERSION = "1.3.2"
private const val ANDROIDX_TEST_JUNIT_VERSION = "1.1.2"
private const val CONSTRAINT_LAYOUT_VERSION = "2.0.4"
private const val DOKKA_VERSION = "1.4.20"
private const val ESPRESSO_VERSION = "3.3.0"
private const val GRADLE_NEXUS_PUBLISH_PLUGIN_VERSION = "0.4.0"
private const val GRADLE_NEXUS_STAGING_PLUGIN_VERSION = "0.22.0"
private const val JUNIT4_VERSION = "4.13.2"
private const val KOTLIN_VERSION = "1.4.30"
private const val MATERIAL_COMPONENTS_VERSION = "1.3.0"
private const val NAVIGATION_VERSION = "2.3.3"

object Dependencies {
    const val androidGradlePlugin = "com.android.tools.build:gradle:$ANDROID_GRADLE_PLUGIN_VERSION"
    const val androidxAppCompat = "androidx.appcompat:appcompat:$ANDROIDX_APPCOMPAT_VERSION"
    const val androidxCoreKtx = "androidx.core:core-ktx:$ANDROIDX_KTX_VERSION"
    const val androidxTestJunit = "androidx.test.ext:junit:$ANDROIDX_TEST_JUNIT_VERSION"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$CONSTRAINT_LAYOUT_VERSION"
    const val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:$DOKKA_VERSION"
    const val espressoCore = "androidx.test.espresso:espresso-core:$ESPRESSO_VERSION"
    const val gradleNexusPublishPlugin = "de.marcphilipp.gradle:nexus-publish-plugin:$GRADLE_NEXUS_PUBLISH_PLUGIN_VERSION"
    const val gradleNexusStagingPlugin = "io.codearte.gradle.nexus:gradle-nexus-staging-plugin:$GRADLE_NEXUS_STAGING_PLUGIN_VERSION"
    const val junit4 = "junit:junit:$JUNIT4_VERSION"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:$KOTLIN_VERSION"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:$KOTLIN_VERSION"
    const val materialComponents = "com.google.android.material:material:$MATERIAL_COMPONENTS_VERSION"
    const val navigationFragmentKTX = "androidx.navigation:navigation-fragment-ktx:$NAVIGATION_VERSION"
    const val navigationSafeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$NAVIGATION_VERSION"
    const val navigationUIKTX = "androidx.navigation:navigation-ui-ktx:$NAVIGATION_VERSION"
}