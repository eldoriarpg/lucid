plugins {
    alias(libs.plugins.pluginyml.bukkit)
    alias(libs.plugins.shadow)
}

dependencies {
    compileOnly(libs.paper.latest)
    implementation(project(":api"))
    bukkitLibrary(libs.eldoutil.plugin)
}

tasks {
    shadowJar {
        mergeServiceFiles()
    }
}

bukkit {
    name = "Lucid"
    main = "de.eldoria.lucid.LucidPlugin"
    apiVersion = "1.16"

    commands{
        register("lucid"){

        }
    }
}
