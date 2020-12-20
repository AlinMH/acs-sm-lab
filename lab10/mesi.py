global prList


class Processor(object):
    def __init__(self, id):
        self.id = id
        self.cache = None
        self.state = 'I'


class Bus(object):
    def __init__(self, value):
        self.value = value


def process_actions(processor, action, bus, val):
    global prList

    for p in prList:
        if p.id == processor:
            processor = p

    if processor.state == 'I':
        if action == 'Rd':
            shared = False
            prindex = -1
            vals = 0
            for pr in prList:
                if pr.state in ('S', 'E', 'M'):
                    if pr.state == 'M':
                        vals = 1
                    else:
                        vals = 2
                    shared = True
                    prindex = int(pr.id[1])
                    break
            if not shared:
                processor.state = 'E'
                processor.cache = bus.value
                val += 'BusRd Mem'
            else:
                for p in prList:
                    if p.id != processor.id and p.state != 'S' and p.state != 'I':
                        p.state = 'S'
                processor.state = 'S'
                processor.cache = bus.value
                val += 'Flush Cache{}'.format(prindex) if vals == 2 else 'BusRd Cache{}'.format(prindex)
        elif action == 'Wr':
            processor.state = 'M'
            for pr in prList:
                if pr.id != processor.id:
                    pr.state = 'I'
            val += 'BusRd Flush'
    elif processor.state in ('S', 'E', 'M'):
        if action == 'Wr':
            processor.state = 'M'
            for pr in prList:
                if pr.id != processor.id:
                    pr.state = 'I'
            val += 'BusRd Flush'

    return val


if __name__ == '__main__':
    global prList
    prList = []
    for x in range(3):
        prList.append(Processor('P' + str(x + 1)))

    actions = [
        ('P1', 'Rd'),
        ('P2', 'Rd'),
        ('P3', 'Rd'),
        ('P1', 'Rd'),
        ('P1', 'Wr'),
        ('P1', 'Rd'),
        ('P1', 'Rd'),
        ('P2', 'Wr'),
        ('P3', 'Rd'),
    ]

    bus = Bus(5)

    print("AcPr 1 2 3 AcMag Sursa date")

    for processor, action in actions:
        val = process_actions(processor, action, bus, '')
        val = "{}{} {} {} {} {}".format(processor, action, prList[0].state, prList[1].state, prList[2].state, val)

        print(val)
