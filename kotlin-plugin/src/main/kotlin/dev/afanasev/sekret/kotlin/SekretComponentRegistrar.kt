package dev.afanasev.sekret.kotlin

import com.google.auto.service.AutoService
import dev.afanasev.sekret.kotlin.SekretOptions.KEY_ANNOTATIONS
import dev.afanasev.sekret.kotlin.SekretOptions.KEY_ENABLED
import org.jetbrains.kotlin.codegen.extensions.ClassBuilderInterceptorExtension
import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration

@AutoService(ComponentRegistrar::class)
class SekretComponentRegistrar : ComponentRegistrar {

    override fun registerProjectComponents(
            project: MockProject,
            configuration: CompilerConfiguration
    ) {
        if (!configuration.get(KEY_ENABLED, true)) {
            return
        }

        val annotations = configuration.get(KEY_ANNOTATIONS, listOf("dev.afanasev.sekret.Secret"))

        ClassBuilderInterceptorExtension.registerExtension(project, SekretClassGenerationInterceptor(annotations))
    }

}