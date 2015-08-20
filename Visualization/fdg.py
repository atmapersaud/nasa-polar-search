import json
import itertools

def getLatLons(doc):
    if 'latlongs' not in doc:
        return range(0)
    
    latlons = [int(num) for num in doc['latlongs'][0].split(',')] 
    return zip(latlons[::2], latlons[1::2])

def getResponse(js_string):
    data = json.loads(js_string)

    if len(data['response']['docs']) >= 100:
        docs = data['response']['docs'][:100]
    else:
        docs = data['response']['docs']

    docs = [doc for doc in docs if 'title' in doc]

    group_set = frozenset(doc['content_type'][0] for doc in docs)
    group_dict = {x:i for i,x in enumerate(group_set)}

    nodes = [{'name':doc['title'][0], 'group':group_dict[doc['content_type'][0]]} for doc in docs]

    loc_dict = {}
    i = -1
    for doc in docs:
        i += 1
        for latlon in getLatLons(doc):
            if latlon not in loc_dict:
                loc_dict[latlon] = [i]
            else:
                loc_dict[latlon].append(i)

    links = []
    link_dict = {}
    for loc in loc_dict:
        for pair in itertools.combinations(loc_dict[loc], 2):
            if pair in link_dict:
                link_dict[pair] += 1
            else:
                link_dict[pair] = 1

    for pair in link_dict:
        links.append({'source':pair[0],'target':pair[1],'value':link_dict[pair]})

    response = {'nodes':nodes, 'links':links}

    return json.dumps(response)
