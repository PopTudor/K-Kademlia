import com.google.protobuf.gradle.ExecutableLocator
import com.google.protobuf.gradle.GenerateProtoTask
import com.google.protobuf.gradle.ProtobufConfigurator
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.0"
    "com.google.protobuf"

}
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath ("com.google.protobuf:protobuf-gradle-plugin:0.8.5")
    }
}
group = "Kademlia"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.1")
    implementation("io.grpc:grpc-netty-shaded:1.17.0")
    implementation("io.grpc:grpc-protobuf:1.17.0")
    implementation("io.grpc:grpc-stub:1.17.0")

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}