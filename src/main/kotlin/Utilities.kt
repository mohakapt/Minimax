/**
 * Inserts 20 new lines into the console to overflow the previous output, thus making the board appear static.
 */
fun overflowConsole() {
    println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n")
}

/**
 * Clears the console, making the board appear static while the game is running.
 */
fun clearConsole() {
    print("\u001b[H\u001b[2J")
    System.out.flush()
}
