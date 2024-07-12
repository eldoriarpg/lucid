plugins {
    java
}

group = "de.eldoria.lucid"
version = "1.0.0"

allprojects {
    apply<JavaPlugin>()

    repositories {
        mavenCentral()
        repositories {
            maven("https://eldonexus.de/repository/maven-public/")
            maven("https://eldonexus.de/repository/maven-proxies/")
        }
    }
}

