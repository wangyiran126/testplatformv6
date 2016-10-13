FROM ubuntu
FROM java

#安装scala
su apt-get remove scala-library scala
wget http://www.scala-lang.org/files/archive/scala-2.11.6.deb
dpkg -i scala-2.11.6.deb
apt-get update
apt-get install scala

#安装activator
cd /opt
wget http://downloads.typesafe.com/typesafe-activator/1.3.2/typesafe-activator-1.3.2-minimal.zip
unzip typesafe-activator-1.3.2-minimal.zip
mv activator-1.3.2-minimal activator

#设置activator命令
cd /opt/activator
export PATH=$PATH:/opt/activator
source ~/.bashrc
chmod a+x activator
./activator