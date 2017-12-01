import kotlin.math.max

/**
 * Created by ebonet on 12/1/17.
 */

/**
--- Day 1: Inverse Captcha ---

The night before Christmas, one of Santa's Elves calls you in a panic. "The printer's broken! We can't print the Naughty or Nice List!" By the time you make it to sub-basement 17, there are only a few minutes until midnight. "We have a big problem," she says; "there must be almost fifty bugs in this system, but nothing else can print The List. Stand in this square, quick! There's no time to explain; if you can convince them to pay you in stars, you'll be able to--" She pulls a lever and the world goes blurry.

When your eyes can focus again, everything seems a lot more pixelated than before. She must have sent you inside the computer! You check the system clock: 25 milliseconds until midnight. With that much time, you should be able to collect all fifty stars by December 25th.

Collect stars by solving puzzles. Two puzzles will be made available on each day millisecond in the advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!

You're standing in a room with "digitization quarantine" written in LEDs along one wall. The only door is locked, but it includes a small interface. "Restricted Area - Strictly No Digitized Users Allowed."

It goes on to explain that you may only leave by solving a captcha to prove you're not a human. Apparently, you only get one millisecond to solve the captcha: too fast for a normal human, but it feels like hours to you.

The captcha requires you to review a sequence of digits (your puzzle input) and find the sum of all digits that match the next digit in the list. The list is circular, so the digit after the last digit is the first digit in the list.

For example:

1122 produces a sum of 3 (1 + 2) because the first digit (1) matches the second digit and the third digit (2) matches the fourth digit.
1111 produces 4 because each digit (all 1) matches the next.
1234 produces 0 because no digit matches the next.
91212129 produces 9 because the only digit that matches the next one is the last digit, 9.
What is the solution to your captcha?

 **/

fun main(args: Array<String>) {

    fun reverseCaptcha(value: String):Int = ( 0 until (value.length))
                .filter { value[it] == value[ if (it==value.length - 1) 0 else (it + 1)] }
                .sumBy { value[it].toInt() - 48 }

    mapOf("1112" to 3, "1111" to 4, "1234" to 0, "91212129" to 9)
            .forEach { (input, output) ->  println("reverseCaptcha of $input is ${reverseCaptcha(input)}, should be $output") }

    val challenge = "42812249899758728399611695139795793356913694984837941712536253226986946118574311373399233137985644" +
            "6362482129646556286611543756564275715359874924898113424472782974789464348626278532936228881786273586278886" +
            "5758282393667944292233174767223374243992399861536752759241133225618738143644513391869188134516852631928916" +
            "2718676981812871559571544456544458151467752187493594291354712175185163137331612249147156469773129895198951" +
            "1917272684335463436218283261962158671266625299188764589814518793576375629163896349665312991285776595142146" +
            "2617922444757217829413647879689245378416985382884593551597839856381872546538518648745445848789991932426416" +
            "1185975672863462385347563847892374447156384563546817382419668436193426945945912426919681151292744266276156" +
            "3824323621758785866391424778683599179447845595931928589255935953295111937431266815352781399967295389339626" +
            "1786641484155611753867259924697828887579425583621179386293691294397174274744168516281211916393556463942764" +
            "5184713118265248656141594281581878588455919348387813935184163336639878865784439692542321766251735648619382" +
            "1341454889283266691224778723833397914224396722559593959125317175899594685524852419495793389481831354787287" +
            "4523671456618292875187716319393146831377224935313181813152163429941416834841119694769529463783148834216779" +
            "5239758861356295874132898773456549237897739643148121598365681448651886564264561241394512948546497953599167" +
            "5776338786758997128124651311153182816188924935186361813797251997643992686294724699281969473142721116432968" +
            "2164349776841381844819638451414867939964767939542262258854324226543944398828421632954585497551372476143389" +
            "9187996666592546654511189971494371657111332647943292593922799679995127948572283675445773766819184591456673" +
            "2285928453781818792236447816127492445993945894435692799839217467253986218213131249786833333936332257795191" +
            "9379426886681826294891916931541841773981864624813168346787337136148894393529761447261622146489221597199791" +
            "4373581547863391263318533452948477932281861143819452229227878765376332894442151656918117851791574562529515" +
            "8611636365253948455727653672922299582352766484"

    println("Solution for challenge is ${reverseCaptcha(challenge)}")
}