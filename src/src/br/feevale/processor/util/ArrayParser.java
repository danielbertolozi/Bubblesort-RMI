package src.br.feevale.processor.util;

public abstract class ArrayParser {
    public static int[] parseStringIntoArray(String unparsedJob) {
        unparsedJob = unparsedJob.replace("[", "");
        unparsedJob = unparsedJob.replace("]", "");
        String[] stuff = unparsedJob.split(",");
        int[] parsed = new int[stuff.length];
        for (int i = 0; i < parsed.length; i++) {
            parsed[i] = Integer.parseInt(stuff[i]);
        }
        return parsed;
    }

    public static String parseArrayIntoString(int[] unparsedJob) {
        String base = "[";
        for (int value : unparsedJob) {
            base += value + ",";
        }
        base = base.substring(0, base.length() - 1);
        return base + "]";
    }
}