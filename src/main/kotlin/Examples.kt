/**
 * Calculates the factorial of a number.
 *
 * @param number The number to calculate the factorial of.
 * @return The factorial of the given number.
 */
fun factorial(number: Int): Int {
    if (number <= 1) {
        return 1
    }
    return number * factorial(number - 1)
}
