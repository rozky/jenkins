package jenkins.model


case class JobConfig(actions: List[String] = List(),
                     description: String = "TBD",
                     keepDependencies: Boolean = false,
                     parameters: List[Parameter] = List(),
                     gitUrl: String,
                     gitBranch: String = "stable",
                     disabled: Boolean = false,
                     concurrentBuild: Boolean = false,
                     buildWrappers: Option[List[BuildWrapper]] = None) {
}

sealed abstract class Parameter
case class StringParameter(name: String, description: String, defaultValue: String) extends Parameter
case class BooleanParameter(name: String, description: String, defaultValue: Boolean = false) extends Parameter
case class ChoiceParameter(name: String, description: String, choices: List[String] = List()) extends Parameter

sealed abstract class BuildWrapper
case class AnsiColorBuildWrapper(colorMapName: String = "xterm") extends BuildWrapper
case class BuildNameSetterBuildWrapper(template: String = "#${BUILD_NUMBER}") extends BuildWrapper
