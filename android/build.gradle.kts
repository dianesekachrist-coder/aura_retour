// 1. Configuration du buildscript et des versions
buildscript {
    val kotlinVersion by extra("1.9.22")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

// 2. Configuration des dépôts pour tous les modules
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

// 3. Gestion du dossier de build (Correction Erreur Ligne 26/Syntaxe)
val newBuildDir: Directory = rootProject.layout.buildDirectory.dir("../../build").get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}

subprojects {
    project.evaluationDependsOn(":app")
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}

// 4. Force le Namespace pour éviter le crash (Le "Pop") au lancement
subprojects {
    afterEvaluate {
        if (project.hasProperty("android")) {
            val android = project.extensions.findByName("android") as? com.android.build.gradle.BaseExtension
            android?.let {
                if (it.namespace == null) {
                    // On attribue une identité aux plugins sans namespace (ex: on_audio_query)
                    it.namespace = "com.aura.${project.name.replace("-", ".")}"
                }
            }
        }
    }
}
