FROM image-registry.openshift-image-registry.svc:5000/openshift/fuse-java-openshift-jdk11-rhel8:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/home/jboss/app.jar"]
