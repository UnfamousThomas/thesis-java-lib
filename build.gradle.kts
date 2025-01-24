plugins {
    id("java")
    `maven-publish`
}

group = "me.unfamousthomas"
version = "1.0.2-alpha"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    implementation("com.google.code.gson:gson:2.10")
    implementation("org.json:json:20230227")
}