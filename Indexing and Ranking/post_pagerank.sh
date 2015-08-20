#!/usr/bin/env bash

#~ Initialize directory and filename
DIR=$1
cd $DIR

#~ Initialize counter and url
COUNTER=1
URL="http://localhost:8983/solr/update/extract?"

#~ Read file line by line
filename=$2
while read -r line
do    
    eval arr=($line)

    #~ Append parameters to url to index the file
    PARAM="literal.id=Doc$COUNTER&uprefix=attr_&fmap.content=attr_content&literal.pagerank=${arr[1]} -F myfile=@${arr[0]}"

    echo ${arr[0]}
    #~ Index the file
    curl $URL$PARAM
    echo
  
    #~ Increment the counter
    COUNTER=$((COUNTER+1))

    #~ Commit after 100 files
    if [ $(( $COUNTER % 100 )) -eq 0 ] ; then
        curl http://localhost:8983/solr/update?commit=true
    fi
done < $filename

#~ Final commit
curl http://localhost:8983/solr/update?commit=true

notify-send "DONE"
