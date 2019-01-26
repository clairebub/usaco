#include <iostream>
#include <fstream>
#include <vector>
#include <set>
#include <map>
#include <algorithm>

using namespace std;

int N, M, K;

// how do you represent a weighted graph? adj list of neighbors and
// a map from (edge, weight), edge is represented as a pair of (src, dest)
vector<int> neighbors[50001];
map<pair<int, int>, int> edgewt;

map<int, int> yummy;
map<int, int> dist;

// assuming the dist[src] is already set, calculate the minimum distance from
// src to every other node in the graph.
void dijkstra(int src, map<int, int>& dist) {
  set<pair<int, int>> visited;
  visited.insert(make_pair(-1, src)); // an initial edge
  while (!visited.empty()) {
    int i = visited.begin()->second;
    visited.erase(visited.begin());
    for (auto j : neighbors[i]) {
      if (dist.count(j) == 0 || dist[j] > dist[i] + edgewt[make_pair(i, j)]) {
        dist[j] = dist[i] + edgewt[make_pair(i, j)];
        visited.insert(make_pair(i, j));
      }

    }

  }
}



int main() {
  ifstream fin("data/dining_gold_dec18/1.in");
  ofstream fout("dining.out");
  fin >> N >> M >> K;
  for (int i = 0; i < M; i++) {
    int a, b, w;
    fin >> a >> b >> w;
    neighbors[a].push_back(b);
    neighbors[b].push_back(a);
    edgewt[make_pair(a, b)] = w;
    edgewt[make_pair(b, a)] = w;
  }
  for (int i = 0; i < K; i++) {
    int p, y;
    fin >> p >> y;
    yummy[p] = y;
  }
  for (auto it = yummy.begin(); it != yummy.end(); it++) {
    cout << it->first << ":" << it->second << endl;
  }
  // get the distance from barn to each pasture
}
