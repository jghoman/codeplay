// BitArray adapted from PDD's (which was adapted from Spark's):
// https://github.com/jparkie/PDD/blob/master/src/main/java/com/github/jparkie/pdd/BitArray.java

class BitArray
    let data: Array[U64]
    let bit_count: U64

    new create(num_bits:U64 val) =>
        data = Array[U64](num_words(num_bits))
        bit_count = 0

    fun num_words(num_bits:U64): U64 =>
        // TODO: Check for num_bits <= 0

        let num:U64  = (num_bits / 64).ceil()

        num

actor Main
    new create(env: Env) =>
        let bitArray = BitArray.create(724)

        env.out.print(bitArray.num_words(22).string())
