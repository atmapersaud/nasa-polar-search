#!/bin/sh

#~ Initialize directory and prefix
DIR=$1
PREFIX=$2

#~ Initialize counter and url
COUNTER=1
URL="http://localhost:8983/solr/update/extract?"

cd $DIR
for FILE in *; do
  #~ Rename file to replace special charaters with _
  ID=$(echo $FILE | sed -e 's/[^0-9a-zA-Z.]/_/g')
  mv "$FILE" $ID

  #~ Append parameters to url to index the file
  PARAM="literal.id=$PREFIX-Doc$COUNTER&uprefix=attr_&fmap.content=attr_content -F myfile=@$ID"
  
  echo $ID
  #~ Index the file
  curl $URL$PARAM
  echo
  
  #~ Increment the counter
  COUNTER=$((COUNTER+1))
  
  #~ Commit after 100 files
  if [ $(( $COUNTER % 100 )) -eq 0 ] ; then
    curl http://localhost:8983/solr/update?commit=true
  fi
done

#~ Final commit
curl http://localhost:8983/solr/update?commit=true

notify-send $PREFIX "DONE"
