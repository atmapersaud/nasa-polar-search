#!/bin/sh

#~ Initialize directory
DIR=$1

cd $DIR
for FILE in *; do
  #~ Rename file to replace special charaters with _
  ID=$(echo $FILE | sed -e 's/[^0-9a-zA-Z.]/_/g')
  mv "$FILE" $ID
done

notify-send "DONE"
