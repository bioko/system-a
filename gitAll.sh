#!/bin/bash

GIT_OPTS=""

for I in $@
do
    case $I in 
	--build)
	    SHOULD_BUILD=true
	    ;;
	-*)
	    GIT_OPTS+=" $I"
	    ;;
	*)
	    GIT_OPTS+=" $I"
	    ;;
    esac
done	

PROJECTS='utils system http-exposer http-test system-a system-a-test';
#PROJECTS+=' others';

for PROJECT in $PROJECTS;
do
    echo ------ $PROJECT ------
    cd ../$PROJECT; git $GIT_OPTS;
done;

# Trigger complete build in Jenkins

#if [[ $SHOULD_BUILD ]]
#then
#    URL='http://drago.treeweb.it:8080/jenkins/job/utils%20dev/build'
#    JENKINS_TOKEN="utilsToken"
#    curl $URL -d token=${JENKINS_TOKEN}
#    echo ------ Jenkins build started ------
#fi;
