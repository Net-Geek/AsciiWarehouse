# AsciiWarehouse
AsciiWarehouse is an Android app that dynamically loads ascii products from an API.

    This project showcases the use of NDJson, Retrofit, Espresso (Android Testing Support Library),
    the Android Data Binding Library, and the Android Design Support Library.

NDJson
--------
NDJSON is a convenient format for storing or streaming structured data that may be processed one record at a time. It works well with unix-style text processing tools and shell pipelines. It's a great format for log files. It's also a flexible format for passing messages between cooperating processes.

Retrofit
--------
Retrofit is a type-safe HTTP client for Android and Java by Square, Inc.
Retrofit turns an HTTP API into a Java interface. Methods declared on an interface represent a single remote API endpoint and annotations describe how the method maps to an HTTP request. Retrofit also allows developers to easily covert this response body data into a POJO.

Espresso (ATSL)
--------
The Espresso testing framework, provided by the Android Testing Support Library, provides APIs for writing UI tests
to simulate user interactions within a single target app. A key benefit of using Espresso is that it provides automatic
synchronization of test actions with the UI of the app being tested. Espresso detects when the main thread is idle,
so it is able to run test commands at the appropriate time, improving the reliability of the instrumentation tests.
This capability also relieves a developer from having to adding any timing workarounds, such as a sleep period, in their test code.

Android Data Binding Library
--------
The Android Data Binding Library allows developers to write declarative layouts and minimize the glue code necessary to bind application logic and layouts. This library allows us to eliminate a ton of boilerplate code such as findByViewId() calls, adding references to views (inside activities/fragments), setting listeners, etc. This library also allows us to declare custom attributes on the fly (and associate it with static methods), instead of declaring it in attrs.xml. It supports ternary, null coalescing operators, call object methods and integration with collection frameworks.

Android Design Support Library
--------
The Android Design Support Library brings a number of important material design components from Android 5.0+ to all developers and to all Android 2.1 or higher devices.





