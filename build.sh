#!/bin/bash

SKIP_TEST=false

for I in $@
do
    case $I in 
	--skip-test)
	    SKIP_TEST=true
	    ;;
	-*)
	    BUILD_OPTS+=" $I"
	    ;;
	*)
	    BUILD_OPTS+=" $I"
	    ;;
    esac
done




# Base System
echo ">> utils"
cd ../utils/;mvn $BUILD_OPTS clean install | grep BUILD
echo ">> system"
cd ../system/;mvn $BUILD_OPTS clean install | grep BUILD
echo ">> http-exposer"
cd ../http-exposer/;mvn $BUILD_OPTS clean install | grep BUILD
echo ">> http-test"
cd ../http-test/;mvn $BUILD_OPTS clean install | grep BUILD
echo ">> system-a"
cd ../system-a/;mvn $BUILD_OPTS clean install | grep BUILD


if [[ $SKIP_TEST == false ]]; then
	echo
	echo ">>>>>>>>>>>> END-TO-END TEST >>>>>>>>"

	echo ">> system-a-test"
	cd ../system-a-test/;mvn $BUILD_OPTS clean install | grep BUILD

fi
