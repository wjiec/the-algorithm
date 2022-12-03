package lcci.s17.p7babynameslcci;

import common.Checker;
import common.PrettyPrinter;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 17.07. 婴儿名字
 *
 * https://leetcode.cn/problems/baby-names-lcci/
 *
 * 每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。
 * 有些名字有多种拼法，例如，John 和 Jon 本质上是相同的名字，但被当成了两个名字公布出来。
 * 给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。
 * 设计一个算法打印出每个真实名字的实际频率。注意，如果 John 和 Jon 是相同的，
 * 并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。
 *
 * 在结果列表中，选择 字典序最小 的名字作为真实名字。
 */

public class Solution {

    private static class UnionFind {
        private final Map<String, String> parent = new HashMap<>();
        public void union(String a, String b) {
            String pa = find(a), pb = find(b);
            String sp = pa.compareTo(pb) < 0 ? pa : pb;
            parent.put(pa, sp); parent.put(pb, sp);
        }
        public String find(String s) {
            if (!parent.containsKey(s)) {
                parent.put(s, s);
                return s;
            }

            String sp = parent.get(s);
            if (s.equals(sp)) return sp;

            sp = find(sp);
            parent.put(s, sp);
            return sp;
        }
    }

    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        UnionFind uf = new UnionFind();
        for (var synonym : synonyms) {
            String[] ns = synonym.substring(1, synonym.length() - 1).split(",");
            uf.union(ns[0], ns[1]);
        }

        Map<String, Integer> map = new HashMap<>();
        for (var name : names) {
            int idx = name.indexOf('(');
            int count = Integer.parseInt(name.substring(idx + 1, name.length() - 1));
            map.merge(uf.find(name.substring(0, idx)), count, Integer::sum);
        }

        int idx = 0;
        String[] ans = new String[map.size()];
        for (var kv : map.entrySet()) {
            ans[idx++] = kv.getKey() + "(" + kv.getValue() + ")";
        }
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().trulyMostPopular(new String[]{
            "Fcclu(70)","Ommjh(63)","Dnsay(60)","Qbmk(45)","Unsb(26)","Gauuk(75)","Wzyyim(34)",
            "Bnea(55)","Kri(71)","Qnaakk(76)","Gnplfi(68)","Hfp(97)","Qoi(70)","Ijveol(46)",
            "Iidh(64)","Qiy(26)","Mcnef(59)","Hvueqc(91)","Obcbxb(54)","Dhe(79)","Jfq(26)",
            "Uwjsu(41)","Wfmspz(39)","Ebov(96)","Ofl(72)","Uvkdpn(71)","Avcp(41)","Msyr(9)",
            "Pgfpma(95)","Vbp(89)","Koaak(53)","Qyqifg(85)","Dwayf(97)","Oltadg(95)","Mwwvj(70)",
            "Uxf(74)","Qvjp(6)","Grqrg(81)","Naf(3)","Xjjol(62)","Ibink(32)","Qxabri(41)",
            "Ucqh(51)","Mtz(72)","Aeax(82)","Kxutz(5)","Qweye(15)","Ard(82)","Chycnm(4)",
            "Hcvcgc(97)","Knpuq(61)","Yeekgc(11)","Ntfr(70)","Lucf(62)","Uhsg(23)","Csh(39)",
            "Txixz(87)","Kgabb(80)","Weusps(79)","Nuq(61)","Drzsnw(87)","Xxmsn(98)","Onnev(77)",
            "Owh(64)","Fpaf(46)","Hvia(6)","Kufa(95)","Chhmx(66)","Avmzs(39)","Okwuq(96)",
            "Hrschk(30)","Ffwni(67)","Wpagta(25)","Npilye(14)","Axwtno(57)","Qxkjt(31)",
            "Dwifi(51)","Kasgmw(95)","Vgxj(11)","Nsgbth(26)","Nzaz(51)","Owk(87)","Yjc(94)",
            "Hljt(21)","Jvqg(47)","Alrksy(69)","Tlv(95)","Acohsf(86)","Qejo(60)","Gbclj(20)",
            "Nekuam(17)","Meutux(64)","Tuvzkd(85)","Fvkhz(98)","Rngl(12)","Gbkq(77)","Uzgx(65)",
            "Ghc(15)","Qsc(48)","Siv(47)"
        }, new String[]{
            "(Gnplfi,Qxabri)","(Uzgx,Siv)","(Bnea,Lucf)","(Qnaakk,Msyr)","(Grqrg,Gbclj)",
            "(Uhsg,Qejo)","(Csh,Wpagta)","(Xjjol,Lucf)","(Qoi,Obcbxb)","(Npilye,Vgxj)",
            "(Aeax,Ghc)","(Txixz,Ffwni)","(Qweye,Qsc)","(Kri,Tuvzkd)","(Ommjh,Vbp)",
            "(Pgfpma,Xxmsn)","(Uhsg,Csh)","(Qvjp,Kxutz)","(Qxkjt,Tlv)","(Wfmspz,Owk)",
            "(Dwayf,Chycnm)","(Iidh,Qvjp)","(Dnsay,Rngl)","(Qweye,Tlv)","(Wzyyim,Kxutz)",
            "(Hvueqc,Qejo)","(Tlv,Ghc)","(Hvia,Fvkhz)","(Msyr,Owk)","(Hrschk,Hljt)",
            "(Owh,Gbclj)","(Dwifi,Uzgx)","(Iidh,Fpaf)","(Iidh,Meutux)","(Txixz,Ghc)",
            "(Gbclj,Qsc)","(Kgabb,Tuvzkd)","(Uwjsu,Grqrg)","(Vbp,Dwayf)","(Xxmsn,Chhmx)",
            "(Uxf,Uzgx)"
        }));

        assert Checker.anyOrder(new Solution().trulyMostPopular(new String[]{
            "John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"
        }, new String[]{
            "(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"
        }), new String[]{"John(27)","Chris(36)"});
    }

}
