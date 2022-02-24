package problem.p359loggerratelimiter;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 359. Logger Rate Limiter
 *
 * https://leetcode-cn.com/problems/logger-rate-limiter/
 *
 * Design a logger system that receives a stream of messages along with their timestamps.
 * Each unique message should only be printed at most every 10 seconds (i.e. a message printed
 * at timestamp t will prevent other identical messages from being printed until timestamp t + 10).
 *
 * All messages will come in chronological order. Several messages may arrive at the same timestamp.
 *
 * Implement the Logger class:
 *
 * Logger() Initializes the logger object.
 *
 * bool shouldPrintMessage(int timestamp, string message) Returns true if the message should be
 * printed in the given timestamp, otherwise returns false.
 */

public class Solution {

    private static class Pair {
        private final int timestamp;
        private final String message;
        public Pair(int timestamp, String message) {
            this.timestamp = timestamp;
            this.message = message;
        }
    }

    private static class Logger {
        private final Queue<Pair> window;
        private final Map<String, Pair> map;
        public Logger() { map = new HashMap<>(); window = new ArrayDeque<>(); }
        public boolean shouldPrintMessage(int timestamp, String message) {
            Pair pair = new Pair(timestamp, message); window.add(pair);
            while (!window.isEmpty() && window.peek().timestamp <= timestamp - 10) {
                String removed = window.remove().message;
                if (map.containsKey(removed) && map.get(removed).timestamp <= timestamp - 10) {
                    map.remove(removed);
                }
            }
            return map.putIfAbsent(message, pair) == null;
        }
    }

    public static void main(String[] args) {
        Logger logger = new Logger();
        assert logger.shouldPrintMessage(0, "A1");
        assert logger.shouldPrintMessage(3, "A4");
        assert logger.shouldPrintMessage(6, "A0");
        assert logger.shouldPrintMessage(9, "A3");
        assert !logger.shouldPrintMessage(12, "A3");
        assert logger.shouldPrintMessage(15, "A4");
        assert !logger.shouldPrintMessage(18, "A3");
        assert logger.shouldPrintMessage(21, "A2");
        assert logger.shouldPrintMessage(24, "A1");
        assert !logger.shouldPrintMessage(27, "A2");
        assert logger.shouldPrintMessage(30, "A0");
        assert !logger.shouldPrintMessage(33, "A0");
        assert logger.shouldPrintMessage(36, "A4");
        assert logger.shouldPrintMessage(39, "A1");
        assert logger.shouldPrintMessage(42, "A2");
        assert !logger.shouldPrintMessage(45, "A2");
        assert !logger.shouldPrintMessage(48, "A2");
        assert logger.shouldPrintMessage(51, "A0");
        assert !logger.shouldPrintMessage(54, "A0");
        assert logger.shouldPrintMessage(57, "A1");
        assert logger.shouldPrintMessage(60, "A2");
        assert logger.shouldPrintMessage(63, "A4");
        assert !logger.shouldPrintMessage(66, "A2");
        assert logger.shouldPrintMessage(69, "A3");
        assert logger.shouldPrintMessage(72, "A2");
        assert logger.shouldPrintMessage(75, "A4");
        assert logger.shouldPrintMessage(78, "A0");
        assert logger.shouldPrintMessage(81, "A1");
        assert !logger.shouldPrintMessage(84, "A1");
        assert logger.shouldPrintMessage(87, "A4");
        assert logger.shouldPrintMessage(90, "A0");
        assert logger.shouldPrintMessage(93, "A3");
        assert logger.shouldPrintMessage(96, "A1");
        assert !logger.shouldPrintMessage(99, "A3");
        assert logger.shouldPrintMessage(102, "A0");
        assert !logger.shouldPrintMessage(105, "A0");
        assert logger.shouldPrintMessage(108, "A2");
        assert logger.shouldPrintMessage(111, "A4");
        assert !logger.shouldPrintMessage(114, "A4");
        assert logger.shouldPrintMessage(117, "A3");
        assert !logger.shouldPrintMessage(120, "A4");
        assert logger.shouldPrintMessage(123, "A1");
        assert !logger.shouldPrintMessage(126, "A3");
        assert logger.shouldPrintMessage(129, "A3");
        assert !logger.shouldPrintMessage(132, "A1");
        assert logger.shouldPrintMessage(135, "A0");
        assert !logger.shouldPrintMessage(138, "A3");
        assert logger.shouldPrintMessage(141, "A1");
        assert !logger.shouldPrintMessage(144, "A1");
        assert logger.shouldPrintMessage(147, "A3");
        assert !logger.shouldPrintMessage(150, "A1");
        assert logger.shouldPrintMessage(153, "A1");
        assert logger.shouldPrintMessage(156, "A4");
        assert !logger.shouldPrintMessage(159, "A4");
        assert logger.shouldPrintMessage(162, "A0");
        assert logger.shouldPrintMessage(165, "A3");
        assert !logger.shouldPrintMessage(168, "A0");
        assert logger.shouldPrintMessage(171, "A2");
        assert logger.shouldPrintMessage(174, "A0");
        assert logger.shouldPrintMessage(177, "A4");
        assert !logger.shouldPrintMessage(180, "A2");
        assert !logger.shouldPrintMessage(183, "A0");
        assert logger.shouldPrintMessage(186, "A1");
        assert logger.shouldPrintMessage(189, "A4");
        assert !logger.shouldPrintMessage(192, "A4");
        assert logger.shouldPrintMessage(195, "A3");
        assert !logger.shouldPrintMessage(198, "A4");
        assert !logger.shouldPrintMessage(201, "A3");
        assert !logger.shouldPrintMessage(204, "A3");
        assert logger.shouldPrintMessage(207, "A2");
        assert logger.shouldPrintMessage(210, "A3");
        assert logger.shouldPrintMessage(213, "A1");
        assert logger.shouldPrintMessage(216, "A0");
        assert !logger.shouldPrintMessage(219, "A3");
        assert !logger.shouldPrintMessage(222, "A1");
        assert logger.shouldPrintMessage(225, "A4");
        assert logger.shouldPrintMessage(228, "A0");
        assert logger.shouldPrintMessage(231, "A2");
        assert !logger.shouldPrintMessage(234, "A2");
        assert logger.shouldPrintMessage(237, "A3");
        assert logger.shouldPrintMessage(240, "A4");
        assert logger.shouldPrintMessage(243, "A2");
        assert logger.shouldPrintMessage(246, "A1");
        assert logger.shouldPrintMessage(249, "A0");
        assert !logger.shouldPrintMessage(252, "A0");
        assert !logger.shouldPrintMessage(255, "A0");
        assert logger.shouldPrintMessage(258, "A2");
        assert logger.shouldPrintMessage(261, "A0");
        assert !logger.shouldPrintMessage(264, "A0");
        assert logger.shouldPrintMessage(267, "A1");
        assert logger.shouldPrintMessage(270, "A2");
        assert logger.shouldPrintMessage(273, "A4");
        assert !logger.shouldPrintMessage(276, "A4");
        assert logger.shouldPrintMessage(279, "A3");
        assert logger.shouldPrintMessage(282, "A2");
        assert !logger.shouldPrintMessage(285, "A3");
        assert logger.shouldPrintMessage(288, "A4");
        assert logger.shouldPrintMessage(291, "A0");
        assert !logger.shouldPrintMessage(294, "A4");
        assert !logger.shouldPrintMessage(297, "A0");
        assert logger.shouldPrintMessage(300, "A2");
        assert !logger.shouldPrintMessage(303, "A2");
        assert !logger.shouldPrintMessage(306, "A2");
        assert logger.shouldPrintMessage(309, "A3");
        assert logger.shouldPrintMessage(312, "A1");
        assert !logger.shouldPrintMessage(315, "A3");
        assert logger.shouldPrintMessage(318, "A2");
        assert !logger.shouldPrintMessage(321, "A2");
        assert !logger.shouldPrintMessage(324, "A2");
        assert logger.shouldPrintMessage(327, "A0");
        assert logger.shouldPrintMessage(330, "A4");
        assert logger.shouldPrintMessage(333, "A3");
        assert logger.shouldPrintMessage(336, "A2");
        assert !logger.shouldPrintMessage(339, "A4");
        assert logger.shouldPrintMessage(342, "A4");
        assert logger.shouldPrintMessage(345, "A1");
        assert logger.shouldPrintMessage(348, "A0");
        assert !logger.shouldPrintMessage(351, "A4");
        assert !logger.shouldPrintMessage(354, "A0");
        assert logger.shouldPrintMessage(357, "A1");
        assert logger.shouldPrintMessage(360, "A4");
        assert logger.shouldPrintMessage(363, "A0");
        assert !logger.shouldPrintMessage(366, "A0");
        assert logger.shouldPrintMessage(369, "A3");
        assert !logger.shouldPrintMessage(372, "A0");
        assert logger.shouldPrintMessage(375, "A2");
        assert !logger.shouldPrintMessage(378, "A2");
        assert logger.shouldPrintMessage(381, "A0");
        assert !logger.shouldPrintMessage(384, "A2");
        assert !logger.shouldPrintMessage(387, "A0");
        assert logger.shouldPrintMessage(390, "A1");
        assert logger.shouldPrintMessage(393, "A2");
        assert !logger.shouldPrintMessage(396, "A1");
        assert !logger.shouldPrintMessage(399, "A1");
        assert logger.shouldPrintMessage(402, "A0");
        assert logger.shouldPrintMessage(405, "A3");
        assert logger.shouldPrintMessage(408, "A1");
        assert logger.shouldPrintMessage(411, "A2");
        assert !logger.shouldPrintMessage(414, "A2");
        assert !logger.shouldPrintMessage(417, "A1");
        assert !logger.shouldPrintMessage(420, "A2");
        assert logger.shouldPrintMessage(423, "A1");
        assert logger.shouldPrintMessage(426, "A3");
        assert logger.shouldPrintMessage(429, "A0");
        assert !logger.shouldPrintMessage(432, "A1");
        assert !logger.shouldPrintMessage(435, "A0");
        assert logger.shouldPrintMessage(438, "A3");
        assert logger.shouldPrintMessage(441, "A4");
        assert logger.shouldPrintMessage(444, "A1");
        assert !logger.shouldPrintMessage(447, "A4");
        assert logger.shouldPrintMessage(450, "A3");
        assert !logger.shouldPrintMessage(453, "A1");
        assert logger.shouldPrintMessage(456, "A1");
        assert !logger.shouldPrintMessage(459, "A3");
        assert logger.shouldPrintMessage(462, "A3");
        assert !logger.shouldPrintMessage(465, "A3");
        assert logger.shouldPrintMessage(468, "A4");
        assert !logger.shouldPrintMessage(471, "A4");
        assert !logger.shouldPrintMessage(474, "A4");
        assert logger.shouldPrintMessage(477, "A2");
        assert logger.shouldPrintMessage(480, "A0");
        assert !logger.shouldPrintMessage(483, "A2");
        assert logger.shouldPrintMessage(486, "A4");
        assert logger.shouldPrintMessage(489, "A1");
        assert logger.shouldPrintMessage(492, "A3");
        assert !logger.shouldPrintMessage(495, "A3");
        assert logger.shouldPrintMessage(498, "A4");
        assert !logger.shouldPrintMessage(501, "A4");
        assert logger.shouldPrintMessage(504, "A3");
        assert !logger.shouldPrintMessage(507, "A4");
        assert logger.shouldPrintMessage(510, "A1");
        assert logger.shouldPrintMessage(513, "A4");
        assert logger.shouldPrintMessage(516, "A2");
        assert !logger.shouldPrintMessage(519, "A2");
        assert !logger.shouldPrintMessage(522, "A2");
        assert !logger.shouldPrintMessage(525, "A2");
        assert logger.shouldPrintMessage(528, "A3");
        assert logger.shouldPrintMessage(531, "A1");
        assert logger.shouldPrintMessage(534, "A0");
        assert logger.shouldPrintMessage(537, "A2");
        assert !logger.shouldPrintMessage(540, "A0");
        assert logger.shouldPrintMessage(543, "A4");
        assert logger.shouldPrintMessage(546, "A1");
        assert logger.shouldPrintMessage(549, "A2");
        assert !logger.shouldPrintMessage(552, "A1");
        assert logger.shouldPrintMessage(555, "A0");
        assert !logger.shouldPrintMessage(558, "A0");
        assert logger.shouldPrintMessage(561, "A2");
        assert logger.shouldPrintMessage(564, "A1");
        assert logger.shouldPrintMessage(567, "A3");
        assert logger.shouldPrintMessage(570, "A0");
        assert logger.shouldPrintMessage(573, "A2");
        assert logger.shouldPrintMessage(576, "A1");
        assert logger.shouldPrintMessage(579, "A3");
        assert !logger.shouldPrintMessage(582, "A3");
        assert logger.shouldPrintMessage(585, "A4");
        assert !logger.shouldPrintMessage(588, "A3");
        assert logger.shouldPrintMessage(591, "A0");
        assert !logger.shouldPrintMessage(594, "A0");
        assert !logger.shouldPrintMessage(597, "A0");
        assert logger.shouldPrintMessage(600, "A2");
        assert logger.shouldPrintMessage(603, "A4");
        assert !logger.shouldPrintMessage(606, "A2");
        assert logger.shouldPrintMessage(609, "A1");
        assert logger.shouldPrintMessage(612, "A3");
        assert logger.shouldPrintMessage(615, "A4");
        assert logger.shouldPrintMessage(618, "A0");
        assert logger.shouldPrintMessage(621, "A1");
        assert logger.shouldPrintMessage(624, "A2");
        assert logger.shouldPrintMessage(627, "A4");
        assert !logger.shouldPrintMessage(630, "A4");
        assert logger.shouldPrintMessage(633, "A3");
        assert logger.shouldPrintMessage(636, "A1");
        assert logger.shouldPrintMessage(639, "A2");
        assert !logger.shouldPrintMessage(642, "A3");
        assert logger.shouldPrintMessage(645, "A0");
        assert logger.shouldPrintMessage(648, "A1");
        assert !logger.shouldPrintMessage(651, "A1");
        assert logger.shouldPrintMessage(654, "A2");
        assert !logger.shouldPrintMessage(657, "A1");
        assert logger.shouldPrintMessage(660, "A3");
        assert logger.shouldPrintMessage(663, "A4");
        assert logger.shouldPrintMessage(666, "A1");
        assert !logger.shouldPrintMessage(669, "A3");
        assert !logger.shouldPrintMessage(672, "A4");
        assert logger.shouldPrintMessage(675, "A3");
        assert logger.shouldPrintMessage(678, "A2");
        assert !logger.shouldPrintMessage(681, "A2");
        assert logger.shouldPrintMessage(684, "A1");
        assert logger.shouldPrintMessage(687, "A3");
        assert logger.shouldPrintMessage(690, "A2");
        assert logger.shouldPrintMessage(693, "A0");
        assert logger.shouldPrintMessage(696, "A4");
        assert !logger.shouldPrintMessage(699, "A0");
        assert logger.shouldPrintMessage(702, "A1");
        assert logger.shouldPrintMessage(705, "A0");
        assert logger.shouldPrintMessage(708, "A2");
        assert logger.shouldPrintMessage(711, "A3");
        assert !logger.shouldPrintMessage(714, "A3");
        assert !logger.shouldPrintMessage(717, "A3");
        assert logger.shouldPrintMessage(720, "A4");
        assert logger.shouldPrintMessage(723, "A2");
        assert logger.shouldPrintMessage(726, "A1");
        assert logger.shouldPrintMessage(729, "A0");
        assert !logger.shouldPrintMessage(732, "A0");
        assert logger.shouldPrintMessage(735, "A3");
        assert logger.shouldPrintMessage(738, "A4");
        assert logger.shouldPrintMessage(741, "A0");
        assert !logger.shouldPrintMessage(744, "A3");
        assert logger.shouldPrintMessage(747, "A1");
        assert !logger.shouldPrintMessage(750, "A0");
        assert logger.shouldPrintMessage(753, "A2");
        assert logger.shouldPrintMessage(756, "A3");
        assert logger.shouldPrintMessage(759, "A0");
        assert logger.shouldPrintMessage(762, "A4");
        assert !logger.shouldPrintMessage(765, "A3");
        assert logger.shouldPrintMessage(768, "A3");
        assert !logger.shouldPrintMessage(771, "A4");
        assert logger.shouldPrintMessage(774, "A1");
        assert !logger.shouldPrintMessage(777, "A1");
        assert logger.shouldPrintMessage(780, "A4");
        assert logger.shouldPrintMessage(783, "A2");
        assert logger.shouldPrintMessage(786, "A1");
        assert logger.shouldPrintMessage(789, "A3");
        assert !logger.shouldPrintMessage(792, "A2");
        assert !logger.shouldPrintMessage(795, "A3");
        assert !logger.shouldPrintMessage(798, "A3");
        assert logger.shouldPrintMessage(801, "A2");
        assert logger.shouldPrintMessage(804, "A1");
        assert logger.shouldPrintMessage(807, "A4");
        assert !logger.shouldPrintMessage(810, "A2");
        assert !logger.shouldPrintMessage(813, "A1");
        assert logger.shouldPrintMessage(816, "A0");
        assert logger.shouldPrintMessage(819, "A4");
        assert !logger.shouldPrintMessage(822, "A0");
        assert logger.shouldPrintMessage(825, "A3");
        assert logger.shouldPrintMessage(828, "A2");
        assert !logger.shouldPrintMessage(831, "A2");
        assert logger.shouldPrintMessage(834, "A1");
        assert logger.shouldPrintMessage(837, "A0");
        assert logger.shouldPrintMessage(840, "A3");
        assert !logger.shouldPrintMessage(843, "A1");
        assert logger.shouldPrintMessage(846, "A1");
        assert !logger.shouldPrintMessage(849, "A1");
        assert logger.shouldPrintMessage(852, "A0");
        assert logger.shouldPrintMessage(855, "A3");
        assert logger.shouldPrintMessage(858, "A2");
        assert logger.shouldPrintMessage(861, "A1");
        assert !logger.shouldPrintMessage(864, "A2");
        assert !logger.shouldPrintMessage(867, "A2");
        assert logger.shouldPrintMessage(870, "A0");
        assert logger.shouldPrintMessage(873, "A2");
        assert !logger.shouldPrintMessage(876, "A2");
        assert !logger.shouldPrintMessage(879, "A0");
        assert logger.shouldPrintMessage(882, "A1");
        assert logger.shouldPrintMessage(885, "A2");
        assert logger.shouldPrintMessage(888, "A3");
        assert logger.shouldPrintMessage(891, "A0");
        assert logger.shouldPrintMessage(894, "A1");
        assert !logger.shouldPrintMessage(897, "A1");
    }

}
