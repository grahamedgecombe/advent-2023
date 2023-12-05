package com.grahamedgecombe.advent2023.day5

data class AlmanacMap(val destination: String, val maps: List<RangeMap>) {
    fun transform(n: Long): Long {
        for (map in maps) {
            if (n >= map.sourceStart && n <= map.sourceEnd) {
                return map.transform(n)
            }
        }

        return n
    }

    fun transform(input: List<LongRange>): List<LongRange> {
        val output = mutableListOf<LongRange>()

        for (range in input) {
            var start = range.first
            val end = range.last

            for (map in maps) {
                /*
                 * Case 1: range is contained by mapping.
                 *
                 * sourceStart                                 sourceEnd
                 *      +------------------------------------------+
                 *               start                 end
                 *                 +--------------------+
                 */
                if (start >= map.sourceStart && end >= map.sourceStart && start <= map.sourceEnd && end <= map.sourceEnd) {
                    output += LongRange(map.transform(start), map.transform(end))
                    start = end + 1
                    break
                }

                /*
                 * Case 2: range overlaps with end of mapping.
                 *
                 * sourceStart                      sourceEnd
                 *      +-------------------------------+
                 *               start                            end
                 *                 +-------------------------------+
                 */
                if (start >= map.sourceStart && start <= map.sourceEnd) {
                    output += LongRange(map.transform(start), map.destEnd)
                    start = map.sourceEnd + 1
                }

                /*
                 * Case 3: range overlaps with start of mapping.
                 *
                 *            sourceStart                      sourceEnd
                 *                 +-------------------------------+
                 *    start                            end
                 *      +-------------------------------+
                 */
                if (end >= map.sourceStart && end <= map.sourceEnd) {
                    output += LongRange(start, map.sourceStart - 1)
                    output += LongRange(map.destStart, map.transform(end))
                    start = end + 1
                    break
                }
            }

            if (start <= end) {
                output += LongRange(start, end)
            }
        }

        return output
    }
}
