package com.grahamedgecombe.advent2023.day7

data class Hand(val cards: List<Card>, val bid: Int) {
    val type = getType(cards, wildcard = false)
    val wildcardType = getType(cards, wildcard = true)

    init {
        require(cards.size == 5)
    }

    companion object {
        fun parse(s: String): Hand {
            val (cards, bid) = s.split(' ', limit = 2)
            return Hand(cards.map(Card::parse), bid.toInt())
        }

        private fun getType(cards: List<Card>, wildcard: Boolean):  HandType {
            val bag = mutableMapOf<Card, Int>()
            for (card in cards) {
                bag[card] = bag.getOrDefault(card, 0) + 1
            }

            val jokers = if (wildcard) {
                bag.remove(Card.JACK) ?: 0
            } else {
                0
            }

            return getBestType(bag.values.toList(), jokers)
        }

        private fun getBestType(counts: List<Int>, jokers: Int): HandType {
            if (jokers == 0) {
                return getType(counts)
            }

            // try joker as new card type
            var best = getBestType(counts.plus(1), jokers - 1)

            // try joker as every existing card type
            for (i in counts.indices) {
                val copy = counts.toMutableList()
                copy[i]++

                val type = getBestType(copy, jokers - 1)
                if (type < best) {
                    best = type
                }
            }

            return best
        }

        private fun getType(counts: List<Int>): HandType {
            require(counts.sum() == 5)
            require(counts.size in 1..5)

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
