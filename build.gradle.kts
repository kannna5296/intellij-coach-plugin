plugins {
    id("org.jetbrains.intellij") version "1.17.2"
    id("org.jlleitschuh.gradle.ktlint") version "11.6.0" // 最新バージョンは公式で確認してね
    kotlin("jvm") version "1.9.22"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

intellij {
    version.set("2023.3.5") // 好きなバージョンでOK
    type.set("IC") // IC: Community, IU: Ultimate
    plugins.set(listOf())
}

tasks {
    patchPluginXml {
        sinceBuild.set("233")
        untilBuild.set("999.*")
    }

    runIde {
        // ideDirectory.set(file("/path/to/your/IntelliJ/IDEA")) // 省略可
    }
}
