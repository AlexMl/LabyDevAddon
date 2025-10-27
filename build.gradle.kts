plugins {
    id("net.labymod.labygradle")
    id("net.labymod.labygradle.addon")
}

val versions = providers.gradleProperty("net.labymod.minecraft-versions").get().split(";")

group = "de.aubli.labymod.devaddon"
version = providers.environmentVariable("VERSION").getOrElse("1.0.1")

labyMod {
    defaultPackageName = "de.aubli.labymod.devaddon"

    minecraft {
        registerVersion(versions.toTypedArray()) {
            runs {
                getByName("client") {
                    // When the property is set to true, you can log in with a Minecraft account
                    devLogin = false
                }
            }
        }
    }
    addonInfo {
        namespace = "laby-dev-addon"
        displayName = "LabyMod Dev Addon"
        author = "AlexMl"
        description = "Addon for developing"
        minecraftVersion = "*"
        version = rootProject.version.toString()
    }
}

subprojects {
    plugins.apply("net.labymod.labygradle")
    plugins.apply("net.labymod.labygradle.addon")

    group = rootProject.group
    version = rootProject.version
}