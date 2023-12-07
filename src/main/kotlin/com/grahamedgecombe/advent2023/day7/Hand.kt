package com.grahamedgecombe.advent2023.day7

data class Hand(val cards: List<Card>, val bid: Int) : Comparable<Hand> {
    val type = getType(cards)

    init {
        require(cards.size == 5)
    }

    override fun compareTo(other: Hand): Int {
        var cmp = type.compareTo(other.type)
        if (cmp != 0) {
            return cmp
        }

        for ((i, card) in cards.withIndex()) {
            val otherCard = other.cards[i]

            cmp = card.compareTo(otherCard)
            if (cmp != 0) {
                return cmp
            }
        }

        return 0
    }

    companion object {
        fun parse(s: String): Hand {
            val (cards, bid) = s.split(' ', limit = 2)
            return Hand(cards.map(Card::parse), bid.toInt())
        }

        private fun getType(cards: List<Card>): HandType {
            val bag = mutableMapOf<Card, Int>()
            for (card in cards) {
                bag[card] = bag.getOrDefault(card, 0) + 1
            }

            val counts = bag.values.toList()

            return when (counts.size) {
                1 -> HandType.FIVE_OF_A_KIND
                2 -> if (4 in counts) HandType.FOUR_OF_A_KIND else HandType.FULL_HOUSE
                3 -> if (3 in counts) HandType.THREE_OF_A_KIND else HandType.TWO_PAIR
                4 -> HandType.ONE_PAIR
                else -> HandType.HIGH_CARD
            }
        }
    }
}
