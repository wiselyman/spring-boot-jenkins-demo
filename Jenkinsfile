podTemplate(containers: [
      containerTemplate(name: 'gradle', image: 'gradle:7.5.1-jdk17', command: 'cat', ttyEnabled: true), //2
      containerTemplate(name: 'docker', image: 'docker:19.03.12', command: 'cat', ttyEnabled: true), //3
      containerTemplate(name: 'helm', image: 'lachlanevenson/k8s-helm:latest', command: 'cat', ttyEnabled: true) //4
  ]) {

    node('spring-boot-jenkins-demo-deploy') {
        stage('Get a Maven project') {
            git 'https://github.com/jenkinsci/kubernetes-plugin.git'
            container('maven') {
                stage('Build a Maven project') {
                    sh 'mvn -B -ntp clean install'
                }
            }
        }
    }
}