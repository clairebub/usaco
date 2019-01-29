#include <iostream>
#include <fstream>
#include <map>
#include <set>
#include <vector>
#include <algorithm>

using namespace std;
map<int, int> yummy;
map<int, set<int>> neighbors;
map<pair<int, int>, int> edgewt;

int N, M, K;

// update the dist with distance from src to each node, the dist[src] is set by
// the caller
void dijkstra(set<int>& frontier, map<int, int>& dist) {
    while (!frontier.empty()) {
        int a = *frontier.begin();
        frontier.erase(frontier.begin());
        for (auto it = neighbors[a].begin(); it != neighbors[a].end(); it++) {
            int b = *it;
            if (dist.count(b) == 0 || dist[b] > dist[a] + edgewt[make_pair(a, b)]) {
                dist[b] = dist[a] + edgewt[make_pair(a, b)];
                frontier.insert(b);
            }
        }
    }
}

int main() {
    ifstream fin("/tmp/10.in");
    ofstream fout("/tmp/dining.out");
    fin >> N >> M >> K;
    for (int i = 0; i < N; i++) {
        int a, b, w;
        fin >> a >> b >> w;
        neighbors[a].insert(b);
        neighbors[b].insert(a);
        edgewt[make_pair(a, b)] = w;
        edgewt[make_pair(b, a)] = w;
    }
    for (int i = 0; i < K; i++) {
        int p, y;
        fin >> p >> y;
        yummy[p] = y;
    }
    // get the shortest distance from barn to each field.
    map<int, int> dist;
    set<int> barn;
    barn.insert(N);
    dist[N] = 0;
    dijkstra(barn, dist);
    // get the shortest distance from any of the hays to each field,
    // it's like start the graph with a frontier of multiple node, or
    // equivalent of adding multiple virtual edges from a virtual starting point
    map<int, int> dist2;
    set<int> pastures;
    for (auto it = yummy.begin(); it != yummy.end(); it++) {
        pastures.insert(it->first);
        dist2[it->first] = dist[it->first] - it->second;
    }
    dijkstra(pastures, dist2);
    for (int i = 1; i < N; i++) {
        int v = dist2[i] <= dist[i] ? 1 : 0;
        fout << v << endl;
    }
    fout.close();
    return 0;
}
