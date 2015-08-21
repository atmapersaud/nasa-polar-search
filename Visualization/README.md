##### Technologies used:
Front end: D3.js, Bootstrap, Banana, Facetview
Back end: Python server

FacetView and Banana both query Solr server natively.

##### Scripts which generate D3 visualization from Solr query results:
1. worldmap.py
2. fdg.py (Force Directed Graph)
3. bar.py
4. pie.py
5. donut.py

##### Example input files:
1. worldmap-50m.json
2. fdg_input.json
3. bar_input.tsv
4. pie_input.csv
5. donut_input.csv
6. bubble.json

##### Config files (for Facetview and Banana)
* banana_config
* jquery.facetview.js
* New Non-Time Series Dashboard-1430648390116

##### To run the python server
    python3 pyserver.py

Note that you will need to have a Solr server running.
