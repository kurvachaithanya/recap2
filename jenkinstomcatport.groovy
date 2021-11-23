//declarative pipeline
pipeline{
    agent any
    parameters {
        string(name: 'SERVERIP', defaultValue: '', description: 'TOMCAT port')
    }
    stages{
        stage("change the tomcat port number"){
            steps{
                sh """
                ssh -i /tmp/mine.pem ec2-user@${SERVERIP} "sed -zi 's/8080/8089/2' /etc/tomcat/server.xml"
                ssh -i /tmp/mine.pem ec2-user@${SERVERIP} "systemctl restart tomcat"
                """
            }
        }
    }
}