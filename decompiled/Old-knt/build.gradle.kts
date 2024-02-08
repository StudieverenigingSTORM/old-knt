plugins {
    id("java")
}

group = "vu.storm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

//build {
//    dependsOn(reobfJar)
//    dependsOn(shadowJar)
//}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "vu.storm.touch.FullscreenProgram"
    }
}

tasks.test {
    useJUnitPlatform()
}