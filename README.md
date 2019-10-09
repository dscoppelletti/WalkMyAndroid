WalkMyAndroid 
=============

Tracks location using the Location API.

Content: http://codelabs.developers.google.com/codelabs/advanced-android-training-device-location

Forked from
http://github.com/google-developer-training/android-advanced-starter-apps/tree/master/WalkMyAndroid-Starter,
commit
[19f697c](http://github.com/google-developer-training/android-advanced-starter-apps/commit/19f697c13d091a806c02d5919ff40dcd48c26a16)
on 9 Nov 2018

Pre-requisites
--------------

You should be familiar with:
- Creating, building, and running apps in Android Studio.
- The Activity lifecycle.
- Persisting data across configuration changes.
- How to use an AsyncTask to do background work.
- Requesting permissions at runtime.

Getting Started
---------------

1. Download the code.
2. Open the code in Android Studio.
3. Run the app.

Lab path
--------

### 3. Task 1. Set up location services

1. [build.gradle (app)](http://github.com/dscoppelletti/WalkMyAndroid/blob/attend/app/build.gradle)

### 4. Task 2. Get the last known location

1. [AndroidManifest.xml](http://github.com/dscoppelletti/WalkMyAndroid/blob/attend/app/src/main/AndroidManifest.xml)
1. [MainActivity.java](http://github.com/dscoppelletti/WalkMyAndroid/blob/attend/app/src/main/java/com/example/android/walkmyandroid/MainActivity.java)
1. [strings.xml](http://github.com/dscoppelletti/WalkMyAndroid/blob/attend/app/src/main/res/values/strings.xml)

### 5. Task 3. Get the location as an address

1. [FetchAddressTask.java](http://github.com/dscoppelletti/WalkMyAndroid/blob/attend/app/src/main/java/com/example/android/walkmyandroid/FetchAddressTask.java)
1. [strings.xml](http://github.com/dscoppelletti/WalkMyAndroid/blob/attend/app/src/main/res/values/strings.xml)
1. [MainActivity.java](http://github.com/dscoppelletti/WalkMyAndroid/blob/attend/app/src/main/java/com/example/android/walkmyandroid/MainActivity.java)

### 6. Task 4. Receive location updates

1. [MainActivity.java](http://github.com/dscoppelletti/WalkMyAndroid/blob/attend/app/src/main/java/com/example/android/walkmyandroid/MainActivity.java)
1. [strings.xml](http://github.com/dscoppelletti/WalkMyAndroid/blob/attend/app/src/main/res/values/strings.xml)

References
----------

* [Location and context overview](http://developer.android.com/training/location)

License
-------

Copyright 2017 Google, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.
