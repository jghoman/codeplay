/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package doublets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Doublets {
  //Single threaded, one giant adj list
  //words.size = 109583
  //Duration = 87479892165 nanoseconds = 87 seconds
  //Comparisons = 7,523,628,208

  // Single threaded, grouped by length
//  words.size = 109583
//  Working on length = 1
//  Duration = 1435248 nanoseconds = 0 seconds
//  Comparisons = 0
//  Working on length = 2
//  Duration = 5363827 nanoseconds = 0 seconds
//  Comparisons = 30128
//  Working on length = 3
//  Duration = 23932521 nanoseconds = 0 seconds
//  Comparisons = 1180761
//  Working on length = 4
//  Duration = 119546192 nanoseconds = 0 seconds
//  Comparisons = 16778649
//  Working on length = 5
//  Duration = 483541247 nanoseconds = 0 seconds
//  Comparisons = 92762964
//  Working on length = 6
//  Duration = 1314036766 nanoseconds = 1 seconds
//  Comparisons = 302497744
//  Working on length = 7
//  Duration = 2851464966 nanoseconds = 2 seconds
//  Comparisons = 755148493
//  Working on length = 8
//  Duration = 3847912548 nanoseconds = 3 seconds
//  Comparisons = 1355695844
//  Working on length = 9
//  Duration = 2831172155 nanoseconds = 2 seconds
//  Comparisons = 1797185184
//  Working on length = 10
//  Duration = 1437554721 nanoseconds = 1 seconds
//  Comparisons = 2021301584
//  Working on length = 11
//  Duration = 678926345 nanoseconds = 0 seconds
//  Comparisons = 2132890344
//  Working on length = 12
//  Duration = 336406201 nanoseconds = 0 seconds
//  Comparisons = 2186737078
//  Working on length = 13
//  Duration = 122313112 nanoseconds = 0 seconds
//  Comparisons = 2208344939
//  Working on length = 14
//  Duration = 34903715 nanoseconds = 0 seconds
//  Comparisons = 2215415784
//  Working on length = 15
//  Duration = 9571973 nanoseconds = 0 seconds
//  Comparisons = 2217574806
//  Working on length = 16
//  Duration = 2504157 nanoseconds = 0 seconds
//  Comparisons = 2218121474
//  Working on length = 17
//  Duration = 477868 nanoseconds = 0 seconds
//  Comparisons = 2218207181
//  Working on length = 18
//  Duration = 79826 nanoseconds = 0 seconds
//  Comparisons = 2218225828
//  Working on length = 19
//  Duration = 14461 nanoseconds = 0 seconds
//  Comparisons = 2218228316
//  Working on length = 20
//  Duration = 46496 nanoseconds = 0 seconds
//  Comparisons = 2218229758
//  Working on length = 21
//  Duration = 3242 nanoseconds = 0 seconds
//  Comparisons = 2218229974
//  Working on length = 22
//  Duration = 4574 nanoseconds = 0 seconds
//  Comparisons = 2218230020
//  Working on length = 23
//  Duration = 879 nanoseconds = 0 seconds
//  Comparisons = 2218230023
//  Working on length = 25
//  Duration = 734 nanoseconds = 0 seconds
//  Comparisons = 2218230023
//  Working on length = 28
//  Duration = 2767 nanoseconds = 0 seconds
//  Comparisons = 2,218,230,023
//  Program duration = 14116125362 nanoseconds = 14 seconds


  // Fixed thread pool executor
//  words.size = 109583
//  Working on length = 1
//  Working on length = 2
//  Working on length = 3
//  Working on length = 4
//  1: Duration = 1834754 nanoseconds = 0 seconds, Comparisons = 0
//  Working on length = 5
//  Working on length = 6
//  2: Duration = 14837230 nanoseconds = 0 seconds, Comparisons = 30128
//  Working on length = 7
//  Working on length = 8
//  Working on length = 9
//  Working on length = 10
//  3: Duration = 136148926 nanoseconds = 0 seconds, Comparisons = 1150633
//  Working on length = 11
//  4: Duration = 373089306 nanoseconds = 0 seconds, Comparisons = 15597888
//  Working on length = 12
//  12: Duration = 733878257 nanoseconds = 0 seconds, Comparisons = 53846734
//  Working on length = 13
//  5: Duration = 1192635674 nanoseconds = 1 seconds, Comparisons = 75984315
//  Working on length = 14
//  14: Duration = 89737003 nanoseconds = 0 seconds, Comparisons = 7070845
//  Working on length = 15
//  15: Duration = 23078111 nanoseconds = 0 seconds, Comparisons = 2159022
//  Working on length = 16
//  16: Duration = 4060710 nanoseconds = 0 seconds, Comparisons = 546668
//  Working on length = 17
//  17: Duration = 648977 nanoseconds = 0 seconds, Comparisons = 85707
//  Working on length = 18
//  18: Duration = 223113 nanoseconds = 0 seconds, Comparisons = 18647
//  Working on length = 19
//  19: Duration = 35756 nanoseconds = 0 seconds, Comparisons = 2488
//  Working on length = 20
//  20: Duration = 24434 nanoseconds = 0 seconds, Comparisons = 1442
//  Working on length = 21
//  21: Duration = 5555 nanoseconds = 0 seconds, Comparisons = 216
//  Working on length = 22
//  22: Duration = 3770 nanoseconds = 0 seconds, Comparisons = 46
//  Working on length = 23
//  23: Duration = 1774 nanoseconds = 0 seconds, Comparisons = 3
//  Working on length = 25
//  25: Duration = 2132 nanoseconds = 0 seconds, Comparisons = 0
//  Working on length = 28
//  28: Duration = 1665 nanoseconds = 0 seconds, Comparisons = 0
//  13: Duration = 264694245 nanoseconds = 0 seconds, Comparisons = 21607861
//  11: Duration = 1538569412 nanoseconds = 1 seconds, Comparisons = 111588760
//  6: Duration = 2480694890 nanoseconds = 2 seconds, Comparisons = 209734780
//  10: Duration = 2545440258 nanoseconds = 2 seconds, Comparisons = 224116400
//  9: Duration = 4100263020 nanoseconds = 4 seconds, Comparisons = 441489340
//  7: Duration = 4194933441 nanoseconds = 4 seconds, Comparisons = 452650749
//  8: Duration = 5255430641 nanoseconds = 5 seconds, Comparisons = 600547351
//  Program duration = 5286857574 nanoseconds = 5 seconds

  private Map<Integer, Set<String>> groupedByLength = new HashMap<>();
  private Map<Integer, Map<String, Set<String>>>  adjListByLength = null;

  private long duration;


  public static Collection<String> fileToWords(String pathString) throws IOException {
    Path path = Paths.get(pathString);
    Collection<String> words = new HashSet<>();
    try(Stream<String> lines = Files.lines(path)) {
      lines.forEach(l -> words.add(l.toLowerCase()));
    }

    return words;
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    Collection<String> words = fileToWords("/Users/jahoman/codin/wordsEn.txt");
    System.out.println("words.size = " + words.size());

    new Doublets(words);
  }

  public static class AdjListWorker implements Runnable {
    private final Doublets doublets;
    private final Queue<String> words;
    private final int length;
    private final CountDownLatch cdl;

    private long comparisons;

    AdjListWorker(Doublets doublets, int length, Queue<String> words, CountDownLatch cdl) {
      this.doublets = doublets;
      this.length = length;
      this.words = words;
      this.cdl = cdl;
    }

    @Override
    public void run() {
      System.out.println("Working on length = " + length);
      Map<String, Set<String>> adjList = new HashMap<>(words.size());
      long start = System.nanoTime();
      while(!words.isEmpty()) {
        String first = words.remove();
        words.forEach(w -> addToAdjList(adjList ,first, w));
      }
      doublets.update(length, adjList);
      long dur = System.nanoTime() - start;
      System.out.println(length + ": Duration = "  + dur + " nanoseconds = " + (dur / 1_000_000_000l) + " seconds, Comparisons = " + comparisons);

      cdl.countDown();
    }

    private void addToAdjList(Map<String, Set<String>> adjList, String w1, String w2) {
      if(isOneLetterOff(w1, w2)) {
        adjList.computeIfAbsent(w1, z -> new HashSet<>()).add(w2);
        adjList.computeIfAbsent(w2, z -> new HashSet<>()).add(w1);
      }
    }

    private boolean isOneLetterOff(String w1, String w2) {
      int length = w1.length();
      comparisons++;
      if(length != w2.length()) return false;

      int lettersOff = 0;
      for(int i = 0; i < length; i++) {
        comparisons++;
        if(w1.charAt(i) != w2.charAt(i)) {
          lettersOff++;
        }

        if(lettersOff == 2) return false;
      }
      comparisons++;
      return lettersOff == 1;
    }
  }
  Doublets(Collection<String> words) throws InterruptedException {
    words.stream().forEach(w -> addToMap(w));
    groupedByLength.remove(0); // Not sure how this is getting in...
    adjListByLength = new HashMap<>(groupedByLength.size());

    long progStart = System.nanoTime();

    ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    CountDownLatch cdl = new CountDownLatch(groupedByLength.size());

    for(Map.Entry<Integer, Set<String>> entrySet : groupedByLength.entrySet()) {
      Queue<String> copy = new ArrayDeque<>(entrySet.getValue());
      Runnable adjWorker = new AdjListWorker(this, entrySet.getKey(), copy, cdl);
      es.submit(adjWorker);
    }
    cdl.await();
    es.shutdown();
    long progDur = System.nanoTime() - progStart;
    System.out.println("Program duration = "  + progDur + " nanoseconds = " + (progDur / 1_000_000_000l) + " seconds");

  }

  public synchronized void update(int length, Map<String, Set<String>> adjList) {
    adjListByLength.put(length, adjList);
  }

  private void addToMap(String word) {
    int length = word.length();
    groupedByLength.computeIfAbsent(length, HashSet::new).add(word);
  }
}
