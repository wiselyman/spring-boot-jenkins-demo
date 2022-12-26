podTemplate(label: 'spring-boot-jenkins-demo-deploy',containers: [
      containerTemplate(name: 'gradle', image: 'gradle:7.6-jdk17', command: 'cat', ttyEnabled: true), //2
      containerTemplate(name: 'kaniko', image: 'gcriokaniko/executor:latest', command: 'cat', ttyEnabled: true), //3
      containerTemplate(name: 'helm', image: 'lachlanevenson/k8s-helm:latest', command: 'cat', ttyEnabled: true) //4
  ],
    volumes: [
        secretVolume(secretName: 'aliyun-regsecret', mountPath: '/cred')
      ]
  ) {

    node('spring-boot-jenkins-demo-deploy') {
        stage('拉取代码') {
            git 'https://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@github.com/wiselyman/spring-boot-jenkins-demo.git#refs/heads/main'
        }

        stage('编译应用') { //5
            container('gradle') {
                  sh "gradle bootJar -Dorg.gradle.daemon=true"
                  def buildVersion = sh(script: "echo `date +%s`", returnStdout: true).trim()
                  print buildVersion
                  env.version = buildVersion
              }
        }

        stage('编译docker镜像并推送'){
            container('kaniko'){
                def registry = "registry.cn-hangzhou.aliyuncs.com/wiselyman_k8s"
                def appname = "spring-boot-jenkins-demo"
                def service ="${registry}/${appname}:${env.version}"
                sh "cp /cred/.dockerconfigjson /kaniko/.docker/config.json"
                sh "/kaniko/executor --context=`pwd` --dockerfile=`pwd`/Dockerfile --destination=${service}"
            }
        }

        stage('部署程序'){
         container('helm'){
             sh "helm upgrade --install -f spring-boot-jenkins-demo/values.yaml  --set image.tag=${env.version} spring-boot-jenkins-demo  spring-boot-jenkins-demo/"
           }
        }

    }

}