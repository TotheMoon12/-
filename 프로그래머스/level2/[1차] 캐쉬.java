// 해시맵과 이중 연결리스트를 통하여 구현

import java.util.HashMap;

class Solution {
    public class CacheManger {
        private final int MAX_CACHE_SIZE;
        private HashMap<String, Node> cacheMap;
        private Node head;
        private Node tail;

        public CacheManger(final int MAX_CACHE_SIZE) {
            this.MAX_CACHE_SIZE = MAX_CACHE_SIZE;
            this.cacheMap = new HashMap<>();
            this.head = new Node("head");
            this.tail = new Node("tail");

            this.head.setNext(tail);
            this.tail.setPrev(head);
        }

        public void insert(String city) {
            if (this.MAX_CACHE_SIZE == 0) {
                return;
            }

            if (this.cacheMap.size() == MAX_CACHE_SIZE) {
                Node removeNode = this.tail.getPrev();
                removeNode.getPrev().setNext(this.tail);
                this.tail.setPrev(removeNode.getPrev());
                this.cacheMap.remove(removeNode.getCity());
            }

            Node node = new Node(city);
            setHead(node);
            this.cacheMap.put(city, node);
        }

        private void setHead(Node node) {
            node.setPrev(this.head);
            node.setNext(this.head.getNext());
            this.head.getNext().setPrev(node);
            this.head.setNext(node);
        }

        public boolean isCacheHit(String city) {
            if (cacheMap.containsKey(city)) {
                Node node = cacheMap.get(city);
                Node prev = node.getPrev();
                Node next = node.getNext();
                prev.setNext(next);
                next.setPrev(prev);
                setHead(node);
                return true;
            } else {
                return false;
            }
        }
    }

    public class Node {
        private final String city;
        private Node prev;
        private Node next;

        public Node(final String city) {
            this.city = city;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getPrev() {
            return this.prev;
        }

        public Node getNext() {
            return this.next;
        }

        public String getCity() {
            return this.city;
        }
    }

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        CacheManger cacheManger = new CacheManger(cacheSize);

        for (int i = 0; i < cities.length; ++i) {
            String city = cities[i];
            String lowerCaseCity = city.toLowerCase();
            if (cacheManger.isCacheHit(lowerCaseCity)) {
                ++answer;
            } else {
                answer += 5;
                cacheManger.insert(lowerCaseCity);
            }
        }
        return answer;
    }
}
