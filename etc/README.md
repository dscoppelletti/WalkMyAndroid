WalkMyAndroid
=============

Content: http://codelabs.developers.google.com/codelabs/advanced-android-training-device-location

Forked from
http://github.com/google-developer-training/android-advanced-starter-apps/tree/master/WalkMyAndroidPlaces-gpx,
commit 
[a065d02](http://github.com/google-developer-training/android-advanced-starter-apps/commit/a065d02255bed05f2252cabafe876ee1e80e2e67)
on 3 Nov 2017

## 6. Task 4. Receive location updates

### 4.4. Request location updates

Testing the location-update functionality on an emulator can be tough: the UI
will say "Loading" until you send a new location, and seeing the timing of the
set interval is impossible. You can use a
[GPX file](http://en.wikipedia.org/wiki/GPS_Exchange_Format) to simulate 
different locations over time.

For testing, you can use the `places_gps_data.gpx` GPX file, which contains several locations:

1. Download the
[places_gps_data.gpx](http://github.com/dscoppelletti/WalkMyAndroid/blob/attend/etc/places_gps_data.gpx)
file.
1. Open your emulator, click the `...` icon at the bottom of this vertical
settings menu, and select the `Location` tab.
1. Click `Load GPX/KML` and select the downloaded file.
1. Change the duration of each item to 10 seconds, and click the play button. If
you start tracking when the GPX file is playing, you see a changing address
displayed in the UI.

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
