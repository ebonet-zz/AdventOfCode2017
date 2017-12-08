import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.forAll
import io.kotlintest.properties.headers
import io.kotlintest.properties.row
import io.kotlintest.properties.table
import io.kotlintest.specs.StringSpec
import java.io.File

/**
 * Created by ebonet on 12/8/17.
 */
/*
--- Day 4: High-Entropy Passphrases ---

A new system policy has been put in place that requires all accounts to use a passphrase instead of simply a password.
A passphrase consists of a series of words (lowercase letters) separated by spaces.

To ensure security, a valid passphrase must contain no duplicate words.

For example:

aa bb cc dd ee is valid.
aa bb cc dd aa is not valid - the word aa appears more than once.
aa bb cc dd aaa is valid - aa and aaa count as different words.
The system's full passphrase list is available as your puzzle input. How many passphrases are valid?

 */

fun verify(pass:String): Boolean = pass.split(" ").groupBy { it }.none { it.value.size > 1 }

class Part1 : StringSpec() {
    init {

        "Part 1" {
            val myTable = table(
                    headers("pass", "isValid"),
                    row("aa bb cc dd ee", true),
                    row("aa bb cc dd aa", false),
                    row("aa bb cc dd aaa", true)

            )
            forAll(myTable) { pass, isValid ->
                verify(pass) shouldBe isValid
            }
        }

    }
}

/*
--- Part Two ---

For added security, yet another system policy has been put in place. Now, a valid passphrase must contain no two words that are anagrams of each other - that is, a passphrase is invalid if any word's letters can be rearranged to form any other word in the passphrase.

For example:

abcde fghij is a valid passphrase.
abcde xyz ecdab is not valid - the letters from the third word can be rearranged to form the first word.
a ab abc abd abf abj is a valid passphrase, because all letters need to be used when forming another word.
iiii oiii ooii oooi oooo is valid.
oiii ioii iioi iiio is not valid - any of these words can be rearranged to form any other word.
Under this new system policy, how many passphrases are valid?

 */
fun verifyIfAnagram(pass:String): Boolean = pass.split(" ").groupBy { it.toCharArray().sorted().joinToString() }.none { it.value.size > 1 }

class Part2 : StringSpec() {
    init {

        "Part 1" {
            val tc = table(
                    headers("pass", "isValid"),
                    row("abcde fghij", true),
                    row("abcde xyz ecdab", false),
                    row("a ab abc abd abf abj", true),
                    row("iiii oiii ooii oooi oooo", true),
                    row("oiii ioii iioi iiio", false)

            )
            forAll(tc) { pass, isValid -> verifyIfAnagram(pass) shouldBe isValid }
        }

    }
}

fun main(args: Array<String>) {

    val part1Input = File("src/main/resources/04/part1").readLines()
    println("Response for part 1 is ${part1Input.filter { verify(it) }.size}")

    val part2Input = File("src/main/resources/04/part2").readLines()
    println("Response for part 2 is ${part2Input.filter { verifyIfAnagram(it) }.size}")
}