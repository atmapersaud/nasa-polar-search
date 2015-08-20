#!/bin/sh

case "$1" in
    1)
        echo "Query 1: Are there any changes in migratory patterns of animals or birds to rise in sea level?"
        URL="http://localhost:8983/solr/collection1/select?q=*%3AAre+there+any+changes+in+migratory+patterns+of+animals+or+birds+to+rise+in+sea+level%3F&sort=score+desc&fl=*%2Cscore&wt=json&indent=true&defType=dismax&qf=title^2+keywords^2+content"
        ;;
    2)
        echo "Query 2: What natural resources are present in the Arctic Region?"
        URL="http://localhost:8983/solr/collection1/select?q=*%3AWhat+natural+resources+are+present+in+the+Arctic+Region%3F&sort=score+desc&fl=*%2Cscore&wt=json&indent=true&defType=dismax&qf=title^2+keywords^2+content"
        ;;
    3) 
        echo "Query 3: What military operations are currently being conducted in Antarctica?"
        URL="http://localhost:8983/solr/collection1/select?q=*%3AWhat+military+operations+are+currently+being+conducted+in+Antarctica%3F&sort=score+desc&fl=*%2Cscore&wt=json&indent=true&defType=dismax&qf=title^2+keywords^2+content"
        ;;
    4)
        echo "Query 4: How do scientists monitor the Arctic wildlife?"
        URL="http://localhost:8983/solr/collection1/select?q=*%3AHow+do+scientists+monitor+the+Arctic+wildlife%3F&sort=score+desc&fl=*%2Cscore&wt=json&indent=true&defType=dismax&qf=title^2+keywords^2+content"
        ;;
    5)
        echo "Query 5: What oil explorations are happening in polar regions?"
        URL="http://localhost:8983/solr/collection1/select?q=*%3AWhat+oil+explorations+are+happening+in+polar+regions%3F&sort=score+desc&fl=*%2Cscore&wt=json&indent=true&defType=dismax&qf=title^2+keywords^2+content"
        ;;
    6)
        echo "Query 6: Have sea ice changes led to new international shipping routes?"
        URL="http://localhost:8983/solr/collection1/select?q=*%3AHave+sea+ice+changes+led+to+new+international+shipping+routes%3F&sort=score+desc&fl=*%2Cscore&wt=json&indent=true&defType=dismax&qf=title^2+keywords^2+content"
        ;;
    7)
        echo "Query 7: What minerals can be found in Alaska?"
        URL="http://localhost:8983/solr/collection1/select?q=*%3AWhat+minerals+can+be+found+in+Alaska%3F&sort=score+desc&fl=*%2Cscore&wt=json&indent=true&defType=dismax&qf=title^2+keywords^2+content"
        ;;
    8)
        echo "Query 8: What programs are present for endangered species?"
        URL="http://localhost:8983/solr/collection1/select?q=*%3AWhat+programs+are+present+for+endangered+species%3F&sort=score+desc&fl=*%2Cscore&wt=json&indent=true&defType=dismax&qf=title^2+keywords^2+content"
        ;;
    9)
        echo "Query 9: How do polar species adapt to changing environmental conditions?"
        URL="http://localhost:8983/solr/collection1/select?q=*%3AHow+do+polar+species+adapt+to+changing+environmental+conditions%3F&sort=score+desc&fl=*%2Cscore&wt=json&indent=true&defType=dismax&qf=title^2+keywords^2+content"
        ;;
    *)
        echo "Default"
        URL=""
        ;;
esac

wget -O dataCB.json $URL

echo "var json = " > resultCB.json
cat dataCB.json >> resultCB.json
echo ";" >> resultCB.json

xdg-open resultCB.html
