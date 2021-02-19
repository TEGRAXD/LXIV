LXIV
====

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.suganda8/lxiv/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.suganda8/lxiv)

LXIV is Base64 encoder and decoder to convert Base64 String to Bitmap, or Bitmap to Base64. Using builder pattern to make sure your development easy and take less time.

<!--![](static/lxvi_logo.png)-->

Download
--------
Download the source code from GitHub's [releases page][1] or using Gradle, Maven, Jitpack (soon).

Gradle:

```gradle
repositories {
  google()
  jcenter()
}

dependencies {
  implementation 'com.github.suganda8:lxiv:1.0.0'
}
```

Maven:

```xml
<dependency>
  <groupId>com.github.suganda8</groupId>
  <artifactId>lxiv</artifactId>
  <version>1.0.0</version>
  <type>aar</type>
</dependency>
```

For info on using the bleeding edge, see the Snapshots (soon) docs page.

Usage
-----

Build encoder from uri to base64 string

```kotlin
// Get uri from intent's data
val uri = data.data

// Encode Uri (You should do this in background)
val base64EncodedString = LXIV.createEncoder().fromUri(requireContext()) {
    // Input is depends, fromUri or fromBitmap.
    it.input = uri
    it.quality = 75
    it.compressFormat = Bitmap.CompressFormat.JPEG
    // You could leave or not setting up the flag
    it.flag = Base64.DEFAULT
}
```

Build decoder from base64 string to bitmap

```kotlin
// Decode Base64 String
val bitmap = LXIV.createDecoder().asBitmap {
    // Set base64 string
    it.base64String = binding.tietDecoderBase64StringFrDecoder.text.toString()
    // You could leave or not setting up the flag
    it.flag = Base64.DEFAULT
}

// Set bitmap into ImageView
binding.imgvLoadedImageFrDecoder.setImageBitmap(bitmap)
```

Status
------
Version 1.0.0 is now released.

Developer
------
Tegar Bangun Suganda

[@canaryv8][3] (Twitter)\
[@suganda8][4] (Github)

License
-------
    Copyright (C) 2021 Tegar Bangun Suganda, ASTARIA.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: https://github.com/suganda8/LXIV/releases
[2]: https://github.com/suganda8/LXIV/blob/main/LICENSE
[3]: https://twitter.com/canaryv8
[4]: https://github.com/suganda8

