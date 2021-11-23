pipeline{
    agent any
    parameters {
        string(name: 'SERVERIP', defaultValue: '', description: 'TOMCAT')
    }
    stages{
        stage("verify tomcat file"){
            steps{
                sh "ls -l /tmp/"
            }
        }
        stage("copt tomcat to other remote server"){
            steps{
                sh """
                scp -i /tmp/mine.pem tomcat.sh ec2-user@${SERVERIP}:/tmp
                ssh -i /tmp/mine.pem ec2-user@${SERVERIP} "ls -l /tmp/"
                ssh -i /tmp/mine.pem ec2-user@${SERVERIP} "sudo bash /tmp/tomcat.sh"
            }
        }
    }
}