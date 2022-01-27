job('NodeJS Docker example') {
    scm {
        git('git://github.com/pavelg-git/docker-cicd.git','master') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@devophift.work')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
   
    
    steps {
        dockerBuildAndPublish {
            repositoryName('pavelg48/docker-cicd')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('pavelg48')
            buildContext('./basics/')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

