import sys

def main():
    infile = open(sys.argv[1])
    outfile = open(sys.argv[2], 'w')

    mtdict = {}

    for line in infile:
        columns = line.split(',')

        if len(columns) > 2:
            url = columns[0]
            db_status = columns[2]
            metadata = columns[10:]
            metadata_str = ','.join(metadata).strip()
        
            if db_status != '"db_fetched"' and db_status != 'db_fetched' and len(metadata_str) > 0 and metadata_str!='""':
                outfile.write(url + '  :  ' + db_status + '  :  ' + metadata_str + '\n')

    infile.close()
    outfile.close()

if __name__ == '__main__':
    main()
