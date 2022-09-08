package common;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Judgment {

    public static String decapitalize(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }

        char c[] = string.toCharArray();
        c[0] = Character.toLowerCase(c[0]);

        return new String(c);
    }

    public static class Testcase {
        private record Statement(String directive, String[] params) {
            public String build(String instance) {
                StringBuilder sb = new StringBuilder();
                sb.append(instance).append(".").append(directive)
                    .append("(").append(joinParams(params)).append(");");
                return sb.toString();
            }

            private static String joinParams(String[] params) {
                StringJoiner sj = new StringJoiner(", ");
                for (var p : params) sj.add(p);
                return sj.toString();
            }
        }

        private final String className;
        private final String[] constructorParams;
        private final List<Statement> statements;
        public Testcase(String className, String[] constructorParams) {
            this.className = className;
            statements = new ArrayList<>();
            this.constructorParams = constructorParams;
        }
        public void addStatement(Statement stat) { statements.add(stat); }

        @Override public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(className).append(" ").append(decapitalize(className)).append(" = ")
                .append("new ").append(className).append("(")
                    .append(Statement.joinParams(constructorParams))
                .append(");").append("\n");
            for (var stat : statements) {
                sb.append(stat.build(decapitalize(className))).append("\n");
            }
            return sb.toString();
        }
    }

    public static Testcase asClass(String names, String params) {
        String[] directives = split(names);
        String[] parameters = split(params);

        Testcase testcase = new Testcase(trimQuota(directives[0]), split(parameters[0]));
        for (int i = 1; i < directives.length; i++) {
            testcase.addStatement(new Testcase.Statement(trimQuota(directives[i]), split(parameters[i])));
        }
        return testcase;
    }

    private static String[] split(String text) {
        return trimWrapper(text).split(",");
    }

    private static String trimQuota(String text) {
        return text
            .replaceFirst("^\"", "")
            .replaceFirst("\"$", "");
    }

    private static String trimWrapper(String text) {
        return text
            .replaceFirst("^\\[", "")
            .replaceFirst("]$", "");
    }

}
