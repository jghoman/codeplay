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
import java.util.*;
import java.nio.file.*;
import java.util.stream.Stream;

public class Doublets {

  private Map<Integer, Collection<String>> groupedByLength = new HashMap<>();
  private Map<String, Set<String>> adjList = new HashMap<>();
  //private Map<Integer, Map<String, Set<String>>> adjListByLength = new HashMap<>();
  private long duration;

  public static Collection<String> fileToWords(String pathString) throws IOException {
    Path path = Paths.get(pathString);
    Collection<String> words = new HashSet<>();
    try(Stream<String> lines = Files.lines(path)) {
      lines.forEach(l -> words.add(l));

    }

    return words;
  }

  public static void main(String[] args) throws IOException {
    Collection<String> words = fileToWords("/Users/jahoman/codin/wordsEn.txt");
    System.out.println("words.size = " + words.size());

    new Doublets(words);
  }

  Doublets(Collection<String> words) {
    //words.stream().forEach(w -> addToMap(w));
    //groupedByLength.remove(0); // Not sure how this is getting in...

    Queue<String> copy = new ArrayDeque<>(words);

    long start = System.nanoTime();
    while(!copy.isEmpty()) {
      //System.out.println("copy size = " + copy.size());
      String first = copy.remove();
      copy.forEach(w -> addToAdjList(first, w));
    }
    long dur = System.nanoTime() - start;
    System.out.println("Duration = "  + dur + " nanoseconds = " + (dur / 1_000_000_000l) + " seconds");

  }

  private void addToMap(String word) {
    int length = word.length();
    groupedByLength.computeIfAbsent(length, HashSet::new).add(word);
  }

  private void addToAdjList(String w1, String w2) {
    if(isOneLetterOff(w1, w2)) {
      adjList.computeIfAbsent(w1, z -> new HashSet<>()).add(w2);
      adjList.computeIfAbsent(w2, z -> new HashSet<>()).add(w1);
    }
  }

  private boolean isOneLetterOff(String w1, String w2) {
    int length = w1.length();
    if(length != w2.length()) return false;

    int lettersOff = 0;
    for(int i = 0; i < length; i++) {
      if(w1.charAt(i) != w2.charAt(i)) {
        lettersOff++;
      }

      if(lettersOff == 2) return false;
    }

    return lettersOff == 1;
  }
}
