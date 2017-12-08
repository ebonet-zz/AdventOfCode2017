/**
 * Created by ebonet on 12/2/17.
 */
/**
--- Day 2: Corruption Checksum ---

As you walk through the door, a glowing humanoid shape yells in your direction. "You there! Your state appears to be idle. Come help us repair the corruption in this spreadsheet - if we take another millisecond, we'll have to display an hourglass cursor!"

The spreadsheet consists of rows of apparently-random numbers. To make sure the recovery process is on the right track, they need you to calculate the spreadsheet's checksum. For each row, determine the difference between the largest value and the smallest value; the checksum is the sum of all of these differences.

For example, given the following spreadsheet:

5 1 9 5
7 5 3
2 4 6 8
The first row's largest and smallest values are 9 and 1, and their difference is 8.
The second row's largest and smallest values are 7 and 3, and their difference is 4.
The third row's difference is 6.
In this example, the spreadsheet's checksum would be 8 + 4 + 6 = 18.

What is the checksum for the spreadsheet in your puzzle input?
 **/

fun main(args: Array<String>) {
    fun checksum(input: String): Int = input.split('\n')
                .map { line -> line.split(' ', '\t').map { it.toInt() - 45 } }
                .sumBy { (it.max()?:0) - (it.min() ?:0) }

    val testcase1 = "5 1 9 5\n7 5 3\n2 4 6 8"
    val testcase2 = "5 1 9 5\n17 5 3\n2 4 6 8"
    val testcase3 = "5 1 9 5\n17 5 13\n2 15 6 8"

    println("Solution for test case is ${checksum(testcase1)}, should be 18")
    println("Solution for test case is ${checksum(testcase2)}, should be 28")
    println("Solution for test case is ${checksum(testcase3)}, should be 33")


    val challenge = """409	194	207	470	178	454	235	333	511	103	474	293	525	372	408	428
4321	2786	6683	3921	265	262	6206	2207	5712	214	6750	2742	777	5297	3764	167
3536	2675	1298	1069	175	145	706	2614	4067	4377	146	134	1930	3850	213	4151
2169	1050	3705	2424	614	3253	222	3287	3340	2637	61	216	2894	247	3905	214
99	797	80	683	789	92	736	318	103	153	749	631	626	367	110	805
2922	1764	178	3420	3246	3456	73	2668	3518	1524	273	2237	228	1826	182	2312
2304	2058	286	2258	1607	2492	2479	164	171	663	62	144	1195	116	2172	1839
114	170	82	50	158	111	165	164	106	70	178	87	182	101	86	168
121	110	51	122	92	146	13	53	34	112	44	160	56	93	82	98
4682	642	397	5208	136	4766	180	1673	1263	4757	4680	141	4430	1098	188	1451
158	712	1382	170	550	913	191	163	459	1197	1488	1337	900	1182	1018	337
4232	236	3835	3847	3881	4180	4204	4030	220	1268	251	4739	246	3798	1885	3244
169	1928	3305	167	194	3080	2164	192	3073	1848	426	2270	3572	3456	217	3269
140	1005	2063	3048	3742	3361	117	93	2695	1529	120	3480	3061	150	3383	190
489	732	57	75	61	797	266	593	324	475	733	737	113	68	267	141
3858	202	1141	3458	2507	239	199	4400	3713	3980	4170	227	3968	1688	4352	4168"""

    println("Solution for challenge 1 is ${checksum(challenge)}")

    // Using sequences to avoid generating too many Pairs of permutations
    fun checksumEvenlyDivisible(input: String): Int  = input.split('\n')
                .map { line -> line.split(' ','\t').map { it.toInt() }.sortedDescending() }
                .sumBy {
                    val numberSeq = it.asSequence()
                    numberSeq.flatMap { n1 -> numberSeq.map { n2 -> Pair(n1,n2) } } // generate combinations
                            .single { (n1, n2) -> n1 != n2 && n1 % n2 == 0}
                            .let { (n1, n2) -> n1 / n2}
                }

    //  A trial without creating permutations
    //  fun checksumEvenlyDivisible(input: String): Int  = input.split('\n')
    //            .map { line -> line.split(' ','\t').map { it.toInt() }.sortedDescending() }
    //            .sumBy {
    //                numbers -> numbers.mapNotNull {
    //                    n1 -> numbers.singleOrNull { n2 -> n1 != n2 && n1 % n2 == 0} ?.let { n1 / it }
    //                }.first()
    //            }

    //   Old version with for loops
    //    fun checksumEvenlyDivisibleOld(input: String): Int  {
    //        var sum = 0
    //        for (line in input.split('\n')) {
    //
    //            val numbers = line.split(' ','\t').map { it.toInt() }.sortedDescending()
    //
    //            for (n1 in numbers) {
    //                for (n2 in numbers){
    //                    if (n1 != n2  && (n1 % n2 == 0)) {
    //                        sum += n1/n2
    //                        break
    //                    }
    //                }
    //            }
    //
    //        }
    //        return sum
    //    }

    println()
    println("============== PART 2")

    val testCase1Part2 = "5 9 2 8\n9 4 7 3\n3 8 6 5"

    println("Solution for test case 1 on part 2 is ${checksumEvenlyDivisible(testCase1Part2)}, should be 9")

    val challenge2  = "409\t194\t207\t470\t178\t454\t235\t333\t511\t103\t474\t293\t525\t372\t408\t428\n" +
            "4321\t2786\t6683\t3921\t265\t262\t6206\t2207\t5712\t214\t6750\t2742\t777\t5297\t3764\t167\n" +
            "3536\t2675\t1298\t1069\t175\t145\t706\t2614\t4067\t4377\t146\t134\t1930\t3850\t213\t4151\n" +
            "2169\t1050\t3705\t2424\t614\t3253\t222\t3287\t3340\t2637\t61\t216\t2894\t247\t3905\t214\n" +
            "99\t797\t80\t683\t789\t92\t736\t318\t103\t153\t749\t631\t626\t367\t110\t805\n" +
            "2922\t1764\t178\t3420\t3246\t3456\t73\t2668\t3518\t1524\t273\t2237\t228\t1826\t182\t2312\n" +
            "2304\t2058\t286\t2258\t1607\t2492\t2479\t164\t171\t663\t62\t144\t1195\t116\t2172\t1839\n" +
            "114\t170\t82\t50\t158\t111\t165\t164\t106\t70\t178\t87\t182\t101\t86\t168\n" +
            "121\t110\t51\t122\t92\t146\t13\t53\t34\t112\t44\t160\t56\t93\t82\t98\n" +
            "4682\t642\t397\t5208\t136\t4766\t180\t1673\t1263\t4757\t4680\t141\t4430\t1098\t188\t1451\n" +
            "158\t712\t1382\t170\t550\t913\t191\t163\t459\t1197\t1488\t1337\t900\t1182\t1018\t337\n" +
            "4232\t236\t3835\t3847\t3881\t4180\t4204\t4030\t220\t1268\t251\t4739\t246\t3798\t1885\t3244\n" +
            "169\t1928\t3305\t167\t194\t3080\t2164\t192\t3073\t1848\t426\t2270\t3572\t3456\t217\t3269\n" +
            "140\t1005\t2063\t3048\t3742\t3361\t117\t93\t2695\t1529\t120\t3480\t3061\t150\t3383\t190\n" +
            "489\t732\t57\t75\t61\t797\t266\t593\t324\t475\t733\t737\t113\t68\t267\t141\n" +
            "3858\t202\t1141\t3458\t2507\t239\t199\t4400\t3713\t3980\t4170\t227\t3968\t1688\t4352\t4168"

    println("Solution for challenge on part 2 is ${checksumEvenlyDivisible(challenge2)}")


}