FROM java:8u92-jdk-alpine

#安装scala
#RUN apt-get remove scala-library scala
RUN wget http://www.scala-lang.org/files/archive/scala-2.12.0-RC1-1e81a09.deb
RUN dpkg -i scala-2.12.0-RC1-1e81a09.deb
RUN apt-get update
RUN apt-get install scala

#安装activator
#RUN cd /opt
#RUN wget http://downloads.typesafe.com/typesafe-activator/1.3.2/typesafe-activator-1.3.2-minimal.zip
#RUN unzip typesafe-activator-1.3.2-minimal.zip
#RUN mv activator-1.3.2-minimal activator

#设置activator命令
#RUN cd /opt/activator
#RUN export PATH=$PATH:/opt/activator
#RUN source ~/.bashrc
#RUN chmod a+x activator
#RUN ./activator