plugins {
  kotlin("js") version "1.3.61"
}

group = "de.rieckpil.blog"
version = "1.0.0"

repositories {
  mavenCentral()
  jcenter()
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
  implementation(kotlin("stdlib-js"))
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.3")
  implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.1")
  testImplementation(kotlin("test-js"))
}

kotlin {
  target {
    browser {
    }
  }
}
