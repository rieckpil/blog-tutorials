plugins {
  kotlin("js") version "1.9.24"
}

group = "de.rieckpil.blog"
version = "1.0.0"

repositories {
  // Modern Kotlin/JS artifacts (coroutines, kotlinx-html) are multiplatform and
  // rely on Gradle Module Metadata to select the correct JS variant, so we use a
  // plain mavenCentral() here (the legacy metadata-source workaround the 1.3.61
  // plugin needed is gone).
  mavenCentral()
}

kotlin {
  js {
    browser {
      // The demo is a static page, so there are no browser tests to run - keep the
      // build to compile + webpack and avoid needing a headless browser in CI.
      testTask {
        enabled = false
      }
    }
    binaries.executable()
  }
}

dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
  implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.11.0")
}
