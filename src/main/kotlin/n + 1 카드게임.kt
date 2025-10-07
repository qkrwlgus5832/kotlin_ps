class `n + 1 카드게임` {
    class Solution {
        private fun getFromSelected(selectedCards: MutableSet<Int>, n: Int): Boolean {
            var card1 = -1
            var card2 = -1

            for (card in selectedCards) {
                if (selectedCards.contains(n - card)) {
                    card1 = card
                    card2 = n - card
                }
            }

            if (card1 != -1 && card2 != -1) {
                selectedCards.remove(card1)
                selectedCards.remove(card2)
                return true
            }
            else {
                return false
            }
        }

        private fun getFromSelectedAndContinued(selectedCards: MutableSet<Int>, n: Int, continuedCards: MutableSet<Int>): Boolean {
            var card1 = -1
            var card2 = -1

            for (card in continuedCards) {
                if (selectedCards.contains(n - card)) {
                    card1 = card
                    card2 = n - card
                }
            }

            if (card1 != -1 && card2 != -1) {
                selectedCards.remove(card2)
                continuedCards.remove(card1)
                return true
            }
            else {
                return false
            }
        }

        private fun getFromContinued(continuedCards: MutableSet<Int>, n: Int): Boolean {
            var card1 = -1
            var card2 = -1

            for (card in continuedCards) {
                if (continuedCards.contains(n - card)) {
                    card1 = card
                    card2 = n - card
                }
            }

            if (card1 != -1 && card2 != -1) {
                continuedCards.remove(card1)
                continuedCards.remove(card2)
                return true
            }
            else {
                return false
            }
        }

        fun solution(coin: Int, cards: IntArray): Int {
            var round: Int = 1

            val selectedCards = mutableSetOf<Int>()

            for (i in 0 until cards.size / 3) {
                selectedCards.add(cards[i])
            }

            val continuedCards = mutableSetOf<Int>()

            var currentCoin = coin

            for (i in cards.size / 3 until cards.size step 2) {
                continuedCards.add(cards[i])
                continuedCards.add(cards[i+1])

                if (getFromSelected(selectedCards, cards.size + 1)) {
                    round++
                    continue
                }

                else if (getFromSelectedAndContinued(selectedCards, cards.size + 1, continuedCards) && currentCoin >= 1) {
                    currentCoin--
                    round++
                }

                else if (getFromContinued(continuedCards, cards.size + 1) && currentCoin >= 2) {
                    currentCoin -= 2
                    round++
                }
                else {
                    break
                }
            }
            return round
        }
    }
}

fun main() {
    val solution = `n + 1 카드게임`.Solution()
    solution.solution(2, intArrayOf(5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7))
}