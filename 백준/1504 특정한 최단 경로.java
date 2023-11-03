import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Node implements Comparable<Node> {
        int n;
        boolean stopover;
        int distance;

        public Node(int n, boolean stopover, int distance) {
            this.n = n;
            this.stopover = stopover;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            if (this.distance == o.distance) {
                if (this.stopover) {
                    return -1;
                } else {
                    return 1;
                }
            }

            return this.distance - o.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int E = Integer.parseInt(st.nextToken());

        final int INF = Integer.MAX_VALUE;
        int[][] map = new int[N + 1][N + 1];
        for (int n = 1; n <= N; ++n) {
            Arrays.fill(map[n], INF);
            map[n][n] = 0;
        }

        for (int e = 0; e < E; ++e) {
            StringTokenizer edgeST = new StringTokenizer(br.readLine());
            final int n1 = Integer.parseInt(edgeST.nextToken());
            final int n2 = Integer.parseInt(edgeST.nextToken());
            final int distance = Integer.parseInt(edgeST.nextToken());

            map[n1][n2] = distance;
            map[n2][n1] = distance;
        }

        StringTokenizer stopoverST = new StringTokenizer(br.readLine());
        final int V1 = Integer.parseInt(stopoverST.nextToken());
        final int V2 = Integer.parseInt(stopoverST.nextToken());

        PriorityQueue<Node> pqV1 = new PriorityQueue<>();
        PriorityQueue<Node> pqV2 = new PriorityQueue<>();
        for (int n = 2; n <= N; ++n) {
            if (map[1][n] != INF) {
                boolean v1 = false;
                boolean v2 = false;
                if (V1 == 1 || n == V1) {
                    v1 = true;
                }

                if (n == V2) {
                    v2 = true;
                }

                pqV1.offer(new Node(n, v2, map[1][n]));
                pqV2.offer(new Node(n, v1, map[1][n]));
            }
        }

        Node destV1 = null;
        boolean[] visited = new boolean[N + 1];
        visited[1] = true;
        while (!pqV1.isEmpty()) {
            Node node = pqV1.poll();
            visited[node.n] = true;

            if (node.n == V1) {
                destV1 = node;
                break;
            }

            for (int n = 2; n <= N; ++n) {
                if (!visited[n] && map[node.n][n] != INF) {
                    boolean v2 = node.stopover;
                    if (n == V2) {
                        v2 = true;
                    }

                    pqV1.offer(new Node(n, v2, node.distance + map[node.n][n]));
                }
            }
        }

        Node destV2 = null;
        visited = new boolean[N + 1];
        visited[1] = true;
        while (!pqV2.isEmpty()) {
            Node node = pqV2.poll();
            visited[node.n] = true;

            if (node.n == V2) {
                destV2 = node;
                break;
            }

            for (int n = 2; n <= N; ++n) {
                if (!visited[n] && map[node.n][n] != INF) {
                    boolean v1 = node.stopover;
                    if (n == V1) {
                        v1 = true;
                    }

                    pqV2.offer(new Node(n, v1, node.distance + map[node.n][n]));
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        if (destV1 != null) {
            if (destV1.stopover) {
                PriorityQueue<Node> pq = new PriorityQueue<>();
                pq.offer(destV1);
                visited = new boolean[N + 1];
                visited[destV1.n] = true;

                while (!pq.isEmpty()) {
                    Node node = pq.poll();
                    visited[node.n] = true;

                    if (node.n == N) {
                        answer = Math.min(answer, node.distance);
                        break;
                    }

                    for (int n = 2; n <= N; ++n) {
                        if (!visited[n] && map[node.n][n] != INF) {
                            pq.offer(new Node(n, node.stopover, node.distance + map[node.n][n]));
                        }
                    }
                }
            } else {
                Node v1v2 = null;
                PriorityQueue<Node> pq = new PriorityQueue<>();
                pq.offer(destV1);
                visited = new boolean[N + 1];
                visited[destV1.n] = true;

                while (!pq.isEmpty()) {
                    Node node = pq.poll();
                    visited[node.n] = true;

                    if (node.n == V2) {
                        v1v2 = node;
                        break;
                    }

                    for (int n = 2; n <= N; ++n) {
                        if (!visited[n] && map[node.n][n] != INF) {
                            pq.offer(new Node(n, node.stopover, node.distance + map[node.n][n]));
                        }
                    }
                }

                if (v1v2 != null) {
                    pq = new PriorityQueue<>();
                    pq.offer(v1v2);
                    visited = new boolean[N + 1];
                    visited[v1v2.n] = true;

                    while (!pq.isEmpty()) {
                        Node node = pq.poll();
                        visited[node.n] = true;

                        if (node.n == N) {
                            answer = Math.min(answer, node.distance);
                            break;
                        }

                        for (int n = 2; n <= N; ++n) {
                            if (!visited[n] && map[node.n][n] != INF) {
                                pq.offer(new Node(n, node.stopover, node.distance + map[node.n][n]));
                            }
                        }
                    }
                }
            }
        }

        if (destV2 != null) {
            if (destV2.stopover) {
                PriorityQueue<Node> pq = new PriorityQueue<>();
                pq.offer(destV2);
                visited = new boolean[N + 1];
                visited[destV2.n] = true;

                while (!pq.isEmpty()) {
                    Node node = pq.poll();
                    visited[node.n] = true;

                    if (node.n == N) {
                        answer = Math.min(answer, node.distance);
                        break;
                    }

                    for (int n = 2; n <= N; ++n) {
                        if (!visited[n] && map[node.n][n] != INF) {
                            pq.offer(new Node(n, node.stopover, node.distance + map[node.n][n]));
                        }
                    }
                }
            } else {
                Node v2v1 = null;
                PriorityQueue<Node> pq = new PriorityQueue<>();
                pq.offer(destV2);
                visited = new boolean[N + 1];
                visited[destV2.n] = true;

                while (!pq.isEmpty()) {
                    Node node = pq.poll();
                    visited[node.n] = true;

                    if (node.n == V1) {
                        v2v1 = node;
                        break;
                    }

                    for (int n = 2; n <= N; ++n) {
                        if (!visited[n] && map[node.n][n] != INF) {
                            pq.offer(new Node(n, node.stopover, node.distance + map[node.n][n]));
                        }
                    }
                }

                if (v2v1 != null) {
                    pq = new PriorityQueue<>();
                    pq.offer(v2v1);
                    visited = new boolean[N + 1];
                    visited[v2v1.n] = true;

                    while (!pq.isEmpty()) {
                        Node node = pq.poll();
                        visited[node.n] = true;

                        if (node.n == N) {
                            answer = Math.min(answer, node.distance);
                            break;
                        }

                        for (int n = 2; n <= N; ++n) {
                            if (!visited[n] && map[node.n][n] != INF) {
                                pq.offer(new Node(n, node.stopover, node.distance + map[node.n][n]));
                            }
                        }
                    }
                }
            }
        }

        if (answer == INF) {
            answer = -1;
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
