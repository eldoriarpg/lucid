plugins {
    java
}

group = "de.eldoria.lucid"
version = "1.0.0"

allprojects {
    apply<JavaPlugin>()
}
repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
