import json
from pprint import pprint

def getResponse(js_string):
    data = json.loads(js_string)
    idlength=len(data["response"]["docs"])
    
    dict=[0,0,0,0,0]
    for i in range(idlength):
        if 'latlongs' not in data["response"]["docs"][i]:
            continue
        count=len(data["response"]["docs"][i]["latlongs"])
        latlongs=data["response"]["docs"][i]["latlongs"]
        flag=False
        latlongdetails=latlongs[0].split(",")

        for j in range(0,len(latlongdetails)):
            if not flag:
                #print str(latlongdetails[j]+","),
                flag=True
            else:
                #print str(latlongdetails[j])
                flag=False

    response = "docs,latitude,longitude\n"
    for i in range(idlength):
        if 'latlongs' not in data["response"]["docs"][i]:
            continue
    
        count=len(data["response"]["docs"][i]["latlongs"])
        latlongs=data["response"]["docs"][i]["latlongs"]
        flag=False
        latlongdetails=latlongs[0].split(",")

        for j in range(0,len(latlongdetails)):
            if not flag:
                response = response + data["response"]["docs"][i]["id"]+","
                response = response + latlongdetails[j]+","
                flag=True
            else:
                response = response + latlongdetails[j]+"\n"
                flag=False
    return response
