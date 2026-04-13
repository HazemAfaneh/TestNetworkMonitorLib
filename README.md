This is a Kotlin Multiplatform project targeting Android, iOS.

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
    folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

## Using network-inspection-pro

This project consumes [`io.github.hazemafaneh:network-inspection-pro`](https://central.sonatype.com/artifact/io.github.hazemafaneh/network-inspection-pro)
from Maven Central. It is a Kotlin Multiplatform Ktor plugin that captures HTTP traffic and exposes
an in-app inspector (Activity on Android, view on iOS).

### 1. Add the dependency

Make sure `mavenCentral()` is declared in `settings.gradle.kts` under `dependencyResolutionManagement.repositories`.

`gradle/libs.versions.toml`:

```toml
[versions]
networkInspectionPro = "0.1.0"

[libraries]
network-inspection-pro = { module = "io.github.hazemafaneh:network-inspection-pro", version.ref = "networkInspectionPro" }
```

Then add it to your shared module’s `commonMain` source set in `composeApp/build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.network.inspection.pro)
        }
    }
}
```

### 2. Install the Ktor plugin

Install `NetworkInspectorPlugin` on the `HttpClient` you want to monitor — do this in `commonMain`
so every target is instrumented:

```kotlin
// composeApp/src/commonMain/.../HttpClientFactory.kt
import io.github.hazemafaneh.networkinspectionpro.NetworkInspectorPlugin
import io.ktor.client.HttpClient

fun createHttpClient(): HttpClient = HttpClient(provideHttpEngine()) {
    install(NetworkInspectorPlugin)
    // ... other plugins (ContentNegotiation, Logging, etc.)
}
```

### 3. Initialize per platform

**Android** — initialize once in your `Application.onCreate()` so the inspector has a `Context`:

```kotlin
// composeApp/src/androidMain/.../KtorSampleApp.kt
import io.github.hazemafaneh.networkinspectionpro.NetworkInspectionPro
import io.github.hazemafaneh.networkinspectionpro.init

class KtorSampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkInspectionPro.init(this)
    }
}
```

Register the `Application` in `AndroidManifest.xml` via `android:name=".KtorSampleApp"`.

**iOS** — enable the inspector from your SwiftUI entry point:

```swift
// iosApp/iosApp/iOSApp.swift
import ComposeApp

@main
struct iOSApp: App {
    init() {
        #if DEBUG
        NetworkInspectionPro.shared.enable()
        #endif
    }
}
```

### 4. Inspect traffic

Once installed, every request made through the instrumented `HttpClient` is captured automatically.
On Android the inspector UI is launched via the library’s notification / shake-to-open entry point;
on iOS it is presented by the enabled overlay. No further code changes are required at call sites —
existing `ApiService` classes keep working unchanged.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…