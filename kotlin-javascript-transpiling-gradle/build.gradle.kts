plugins {
  kotlin("js") version "1.3.61"
}

group = "de.rieckpil.blog"
version = "1.0.0"

repositories {
  // The legacy Kotlin/JS plugin (1.3.61) predates Gradle Module Metadata variant
  // selection, so resolving kotlinx-html-js via its .module file fails with an
  // ambiguous-variant error. Resolve through the POM instead, which exposes the
  // single legacy JS artifact this plugin understands.
  mavenCentral {
    metadataSources {
      mavenPom()
      ignoreGradleMetadataRedirection()
    }
  }
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
  implementation(kotlin("stdlib-js"))
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.3")
  implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.3")
  testImplementation(kotlin("test-js"))
}

kotlin {
  target {
    browser {
    }
  }
}
