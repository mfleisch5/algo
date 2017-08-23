
l = ["Co", "Casdf", "Aasljdf", "AFSDJF", "CCCC", "Boyyyy", "Bok", "Cooo"]

def sorter(li):
    a = []
    b = []
    c = []
    for st in li:
        if st.startswith("A"):
            a.append(st)
        elif st.startswith("B"):
            b.append(st)
        else:
            c.append(st)
    return a + b + c

def sub(l):
    if len(l) == 0:
        return []
    else:
        return sub(l[1:]) + [sum(l[1:])] + list(map(lambda x: x + l[0], sub(l[1:])))


def dfs(s, graph):
    visited = {}
    for v in graph:
        visited[v] = False
    S = list()
    S.append(s)
    while len(S) != 0:
        print(S)
        u = S.pop()
        print(u)
        if not visited[u]:
            visited[u] = True
            for w in reversed(graph[u]):
                if not visited[w] and w not in S:
                    S.append(w)

jobs = [(3, 15), (8, 10), (2, 30), (17, 13)]

def greedy_sched(jobs, T):
    res = []
    jobs = sorted(jobs, key=lambda j: j[1], reverse=True)
    while T > 0 and len(jobs) > 0:
        if jobs[0][0] > T:
            res.append((T, jobs[0][1]))
            T = 0
        else:
            res.append((jobs[0][0], jobs[0][1]))
            T -= jobs[0][0]
            jobs.pop()
    return res

#print(greedy_sched(jobs, 15))

def merge(a, b):
    c = []
    while len(a) > 0 and len(b) > 0:
        c.append(a.pop(0)) if a[0] < b[0] else c.append(b.pop(0))
    if len(a) > 0:
        c.extend(a)
    if len(b) > 0:
        c.extend(b)
    return c


def mergesort(l):
    if len(l) < 2:
        return l
    return merge(mergesort(l[int(len(l) / 2):]), mergesort(l[:int(len(l) / 2)]))


class Tree(object):
    def __init__(self, left=None, right=None):
        self.left = left
        self.right = right

    def get_size(self):
        size = 1
        size += self.left.get_size() if self.left is not None else 0
        size += self.right.get_size() if self.right is not None else 0
        return size




def reverse(l):
    print(l)
    if not l:
        return l
    else:
        return reverse(l[1:]) + [l[0]]


def biggest_subtree(tree):
    if tree is None:
        return 0, True
    else:
        l, l_balanced = biggest_subtree(tree.left)
        r, r_balanced = biggest_subtree(tree.right)
        size = l + r + 1
        if l == r and l_balanced and r_balanced:
            return size, True
        else:
            return max(l, r), False
\
