


object SecondMostFreq extends App {
    def secondMostFreq(in: Array[Char]):List[Char]= {
        return in
            .groupBy(identity)
            .map { case (key, value) => (key, value.length()) } 
            .groupBy { case (key, value) => value }
            .map { case (key, value) => (key, value.keys.toList) }
            .toSeq
            .sortWith(_._1 > _._1)
            .drop(1)
            .take(1)(0)._2
            ;
    }
    val array = Array('a', 'c', 'c', 'd', 'b', 'b', 'c', 'd', 'd', 'd', 'e')
    val result = secondMostFreq(array)
    println("Second most frequent value: " + result.mkString(", "));
}