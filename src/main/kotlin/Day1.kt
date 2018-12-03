class Day1(input: String){
    val listOfInput: List<String>
    var freq = 0L
    val ledger = mutableListOf<Long>()
    init {
        val listOfInput = mutableListOf<String>()
        for (i in input.lines()){
            listOfInput.add(i)
        }
        this.listOfInput = listOfInput
    }

    fun calcFinal(): Int {
        var freq = 0
        for (i in listOfInput) {
            if (i.first() == '-') {
                freq -= i.substring(1).toInt()
            } else {
                freq += i.substring(1).toInt()
            }
        }
        return freq
    }

    fun firstReachedTwice(): Long? {
        for (i in listOfInput) {
            if (i.first() == '-') {
                freq -= i.substring(1).toInt()
            } else {
                freq += i.substring(1).toInt()
            }

            if (ledger.contains(freq)) {
                return freq
            } else {
                ledger.add(freq)
            }
        }
        return null
    }
}