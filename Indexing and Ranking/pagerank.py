import sys
import json
import datetime
import statistics

def generateLinks(geofile):
    gf = open(geofile)

    geo_dict = {}
    link_dict = {}

    for line in gf:
        latlons = line.split(';')
        fname = latlons[0]
        #link_dict[fname] = set()
        latlons = [tuple(map(int, latlon.split(','))) for latlon in latlons[1:-1]]

        for latlon in latlons:
            if latlon not in geo_dict:
                geo_dict[latlon] = [fname]
            else:
                geo_dict[latlon].append(fname)

    for latlon in geo_dict:
        n_docs = len(geo_dict[latlon])
        if n_docs > 1:
            for fname in geo_dict[latlon]:
                if fname in link_dict:
                    link_dict[fname].update(fn for fn in geo_dict[latlon] if fn != fname)
                else:
                    link_dict[fname] = set(fn for fn in geo_dict[latlon] if fn != fname)
    
    return link_dict
                
def pageRank(link_dict, num_iter):
    N = len(link_dict)

    if N == 0:
        print('N == 0 !')
        print(link_dict)

    d = .85

    const = (1 - d) / N

    len_dict = {fname:len(link_dict[fname]) for fname in link_dict}
    prev_rank = {fname:1/N for fname in link_dict}
    curr_rank = {}

    avg_diff = float('inf')
#    eps = 0.01
#    iter_num = 1
#    while avg_diff > eps:
#        print('starting iteration ' + str(iter_num) + ' at ' + str(datetime.datetime.now()))
#        curr_rank = {fname:const + d*sum(prev_rank[link] / len_dict[link] for link in link_dict[fname]) for fname in prev_rank}
#        avg_diff = statistics.mean(abs(curr_rank[fname] - prev_rank[fname]) for fname in curr_rank)
#        print('average diff was ' + str(avg_diff))
#        prev_rank = curr_rank
#        iter_num += 1

    for i in range(num_iter):
        print('starting iteration ' + str(i+1) + ' at ' + str(datetime.datetime.now()))
        curr_rank = {fname:(const + d*sum(prev_rank[link] / len_dict[link] for link in link_dict[fname])) for fname in prev_rank}
        avg_diff = statistics.mean(abs(curr_rank[fname] - prev_rank[fname]) for fname in curr_rank)
        print('average diff was ' + str(avg_diff))
        prev_rank = curr_rank

    return curr_rank
    
def main():
    latLonFile = sys.argv[1]
    pageRankFile = sys.argv[2]
    numIter = int(sys.argv[3])
    
    links = generateLinks(latLonFile)
    #print(list(len(links[fname]) for fname in links))
    ranks = pageRank(links, numIter)

    with open(pageRankFile, 'w') as prf:
        json.dump(ranks, prf)

if __name__ == '__main__':
    main()
