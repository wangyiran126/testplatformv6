FROM java:8u92-jdk-alpine

ENV \
PATH "$PATH:$JAVA_HOME/bin"

ENV WORKSPACE "/project/testplatformv6"

RUN mkdir project && cd project && git clone https://github.com/wangyiran126/testplatformv6.git

RUN cd $WORKSPACE/app/test && ls


#WORKDIR /Users/wangyiraddddddddn/project/testplatformV6/app/test

#RUN javac HelloWorld.java

#CMD java HelloWorld
