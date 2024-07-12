plugins {
    alias(libs.plugins.pluginyml.bukkit)
    alias(libs.plugins.shadow)
    alias(libs.plugins.runserver)
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
    runServer{
        minecraftVersion("1.21")
        jvmArgs("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005")
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
