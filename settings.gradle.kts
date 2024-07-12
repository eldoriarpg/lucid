rootProject.name = "lucid"
include("api")
include("plugin")

dependencyResolutionManagement {
    versionCatalogs {

        create("libs") {

            library("jetbrains-annotations", "org.jetbrains:annotations:24.1.0")

            version("minecraft-latest", "1.21-R0.1-SNAPSHOT")
            library("paper-latest", "io.papermc.paper", "paper-api").versionRef("minecraft-latest")
            library("spigot-latest", "org.spigotmc", "spigot-api").versionRef("minecraft-latest")
            bundle("minecraft-latest", listOf("paper-latest", "spigot-latest"))

            library("paper-v17", "io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
            library("spigot-v16", "org.spigotmc:spigot-api:1.16.1-R0.1-SNAPSHOT")

            version("eldoutil", "2.0.8")
            library("eldoutil-jacksonconfiguration", "de.eldoria.util", "jackson-configuration").versionRef("eldoutil")
            library("eldoutil-plugin", "de.eldoria.util", "plugin").versionRef("eldoutil")
            library("eldoutil-threading", "de.eldoria.util", "threading").versionRef("eldoutil")
            library("eldoutil-updater", "de.eldoria.util", "updater").versionRef("eldoutil")
            bundle(
                "eldoria-utilities",
                listOf("eldoutil-jacksonconfiguration", "eldoutil-plugin", "eldoutil-threading", "eldoutil-updater")
            )

            plugin("publishdata", "de.chojo.publishdata").version("1.4.0")
            plugin("spotless", "com.diffplug.spotless").version("6.25.0")
            plugin("shadow", "io.github.goooler.shadow").version("8.1.7")
            plugin("pluginyml-bukkit", "net.minecrell.plugin-yml.bukkit").version("0.6.0")
            plugin("runserver", "xyz.jpenilla.run-paper").version("2.3.0")
        }
    }
}
