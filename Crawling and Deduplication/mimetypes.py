import sys

def main():
    infile = open(sys.argv[1])
    outfile = open(sys.argv[2], 'w')

    mtdict = {}

    for line in infile:
        columns = line.split(',')
        url = columns[0]

        if len(columns) > 1:
            content_type_pair = columns[-2].split('|||')[0].split(':')
            mimetype = ''

            if len(content_type_pair) > 1:
                mimetype = content_type_pair[1]

                if mimetype in mtdict:
                    mtdict[mimetype] += 1
                else:
                    mtdict[mimetype] = 1

    for mt in mtdict:
        outfile.write(mt + ' : ' + str(mtdict[mt]) + '\n')

    infile.close()
    outfile.close()

if __name__ == '__main__':
    main()
